package com.juco.subscription_add.add.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.juco.common.util.PaymentCycle
import com.juco.common.util.PaymentCycleType
import com.juco.designsystem.component.button.SubManagerButton
import com.juco.designsystem.component.wheelpicker.WheelPicker
import com.juco.designsystem.theme.SubManagerTheme

@Composable
fun PaymentCycleDialog(
    initialTab: PaymentCycleType = PaymentCycleType.DAY,
    onDismissRequest: () -> Unit,
    onConfirm: (PaymentCycle) -> Unit
) {
    var selectedTab by remember { mutableStateOf(initialTab) }

    val days = remember { (1..31).map { "${it}일 마다" } }
    val months = remember { (1..12).map { "${it}개월 마다" } }
    val currentItems = if (selectedTab == PaymentCycleType.DAY) days else months
    var currentSelectionString by remember(selectedTab) {
        mutableStateOf(currentItems[0])
    }
    var currentValue by remember(selectedTab) {
        mutableIntStateOf(1)
    }

    Dialog(onDismissRequest = onDismissRequest) {
        androidx.compose.material3.Surface(
            shape = RoundedCornerShape(16.dp),
            color = SubManagerTheme.colors.secondaryBackground,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "반복 주기 선택",
                    style = SubManagerTheme.typography.h3SemiBold,
                    color = SubManagerTheme.colors.primaryText
                )

                Spacer(Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CycleTabButton(
                        text = "일 간격",
                        isSelected = selectedTab == PaymentCycleType.DAY,
                        onClick = { selectedTab = PaymentCycleType.DAY },
                        modifier = Modifier.weight(1f)
                    )
                    CycleTabButton(
                        text = "월 간격",
                        isSelected = selectedTab == PaymentCycleType.MONTH,
                        onClick = { selectedTab = PaymentCycleType.MONTH },
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(Modifier.height(24.dp))

                WheelPicker(
                    items = currentItems,
                    initialItem = currentSelectionString,
                    onItemSelected = { index, item ->
                        currentSelectionString = item
                        currentValue = index + 1
                    },
                    modifier = Modifier.height(180.dp)
                ) { item, isSelected ->
                    Text(
                        text = item,
                        style = if (isSelected) SubManagerTheme.typography.h2SemiBold
                        else SubManagerTheme.typography.b1Regular,
                        color = if (isSelected) SubManagerTheme.colors.primaryText
                        else SubManagerTheme.colors.secondaryText.copy(alpha = 0.5f),
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(Modifier.height(32.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    SubManagerButton(
                        modifier = Modifier.weight(1f),
                        text = "취소",
                        enabledBackgroundColor = SubManagerTheme.colors.secondaryBackground,
                        enabledTextColor = SubManagerTheme.colors.secondaryText,
                        onClick = onDismissRequest
                    )

                    SubManagerButton(
                        modifier = Modifier.weight(1f),
                        text = "확인",
                        onClick = {
                            val resultCycle = PaymentCycle(selectedTab, currentValue)
                            onConfirm(resultCycle)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun CycleTabButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(40.dp)
            .background(
                if (isSelected) SubManagerTheme.colors.primaryText
                else SubManagerTheme.colors.outline.copy(0.2f),
                RoundedCornerShape(8.dp)
            )
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = SubManagerTheme.typography.b1SemiBold,
            color = if (isSelected) SubManagerTheme.colors.primaryBackground
            else SubManagerTheme.colors.secondaryText
        )
    }
}