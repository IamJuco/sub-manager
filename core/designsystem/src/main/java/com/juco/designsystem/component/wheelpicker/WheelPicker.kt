package com.juco.designsystem.component.wheelpicker

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.unit.Constraints
import com.juco.designsystem.theme.SubManagerTheme
import kotlinx.coroutines.launch
import kotlin.math.abs

/**
 * iOS 스타일의 드르륵 거리는 휠 피커 컴포넌트
 * @param items
 * - 휠에 표시될 전체 데이터 리스트
 *
 * @param initialItem
 * - 화면이 처음 떴을 때 가운데에 선택되어 있을 초기값
 *
 * @param onItemSelected
 * - 사용자가 스크롤을 멈추고 특정 아이템이 가운데에 안착(Snap)했을 때 호출되는 콜백
 *
 * @param content
 * - 리스트의 각 아이템을 어떻게 그릴지 정의하는 UI 템플릿(Slot API)
 * - isSelected: 현재 이 아이템이 가운데(선택된 상태)에 있는지 여부
 * - 용도: isSelected가 true일 때 글씨를 진하게 하거나 색상을 바꾸는 등의 스타일링
 */

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WheelPicker(
    modifier: Modifier = Modifier,
    items: List<String>,
    initialItem: String,
    onItemSelected: (Int, String) -> Unit = { _, _ -> },
    content: @Composable ((String, Boolean) -> Unit)
) {
    val density = LocalDensity.current
    val scrollState = rememberLazyListState(0)
    val coroutineScope = rememberCoroutineScope()
    var lastSelectedIndex by remember { mutableIntStateOf(-1) }
    val itemHeight = 40.dp
    val itemHeightPx = with(density) { itemHeight.toPx() }
    val backgroundColor = SubManagerTheme.colors.secondaryBackground
    val highlightColor = SubManagerTheme.colors.primaryText.copy(alpha = 0.05f)

    Column(modifier = modifier) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .drawWithContent {
                    drawContent()
                    val centerY = size.height / 2f
                    val rectTop = centerY - (itemHeightPx / 2f)
                    val rectHeight = itemHeightPx
                    drawRoundRect(
                        color = highlightColor,
                        cornerRadius = CornerRadius(8.dp.toPx()),
                        topLeft = Offset(16.dp.toPx(), rectTop),
                        size = Size(size.width - 32.dp.toPx(), rectHeight)
                    )
                },
            contentAlignment = Alignment.Center
        ) {
            val availableHeight = this.constraints.maxHeight.toFloat()
            val currentPickerHeightPx = if (availableHeight == Constraints.Infinity.toFloat()) {
                with(density) { 200.dp.toPx() }
            } else {
                availableHeight
            }

            LaunchedEffect(items, initialItem) {
                val targetIndex = items.indexOf(initialItem)
                val safeTargetIndex = if (targetIndex >= 0) targetIndex else 0

                lastSelectedIndex = safeTargetIndex
                scrollState.scrollToItem(safeTargetIndex)
            }

            val pickerHeightDp = with(density) { currentPickerHeightPx.toDp() }
            val fadeHeightDp =
                with(density) { ((currentPickerHeightPx - itemHeightPx) / 2f).toDp() }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(pickerHeightDp),
                state = scrollState,
                flingBehavior = rememberSnapFlingBehavior(scrollState),
                contentPadding = PaddingValues(vertical = fadeHeightDp)
            ) {
                items(
                    count = items.size,
                    key = { index -> items[index] },
                    itemContent = { i ->
                        val item = items[i]

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(itemHeight)
                                .pointerInput(i) {
                                    detectTapGestures(
                                        onTap = {
                                            coroutineScope.launch {
                                                scrollState.animateScrollToItem(i)
                                            }
                                        }
                                    )
                                }
                                .onGloballyPositioned { coordinates ->
                                    val y = (coordinates.positionInParent().y) + (itemHeightPx / 2f)
                                    val parentHalfHeight = (currentPickerHeightPx / 2f)
                                    val isCurrentlySelected =
                                        abs(parentHalfHeight - y) <= (itemHeightPx / 2f)

                                    if (isCurrentlySelected && lastSelectedIndex != i) {
                                        onItemSelected(i, item)
                                        lastSelectedIndex = i
                                    }
                                },
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            content(item, lastSelectedIndex == i)
                        }
                    }
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(fadeHeightDp)
                    .align(Alignment.TopCenter)
                    .drawWithContent {
                        drawRect(
                            brush = Brush.verticalGradient(
                                colors = listOf(backgroundColor, backgroundColor.copy(alpha = 0f))
                            )
                        )
                    }
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(fadeHeightDp)
                    .align(Alignment.BottomCenter)
                    .drawWithContent {
                        drawRect(
                            brush = Brush.verticalGradient(
                                colors = listOf(backgroundColor.copy(alpha = 0f), backgroundColor)
                            )
                        )
                    }
            )
        }
    }
}