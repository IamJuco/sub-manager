package com.juco.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.juco.designsystem.theme.SubManagerTheme
import com.juco.home.model.NextPaymentInfo

@Composable
fun NextPaymentSection(
    modifier: Modifier = Modifier,
    nextPaymentInfo: NextPaymentInfo? = null
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "다가오는 결제 서비스",
            style = SubManagerTheme.typography.h3SemiBold,
            color = SubManagerTheme.colors.primaryText
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (nextPaymentInfo != null) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .background(
                        color = SubManagerTheme.colors.secondaryBackground,
                        shape = RoundedCornerShape(24.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = SubManagerTheme.colors.primaryText,
                        shape = RoundedCornerShape(24.dp)
                    )
                    .padding(24.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = nextPaymentInfo.date ?: "",
                        style = SubManagerTheme.typography.b1SemiBold,
                        color = SubManagerTheme.colors.primaryText,
                    )

                    Box(
                        modifier = Modifier
                            .background(
                                color = SubManagerTheme.colors.primaryText,
                                shape = RoundedCornerShape(100.dp)
                            )
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = nextPaymentInfo.dDay ?: "",
                            style = SubManagerTheme.typography.c1SemiBold,
                            color = SubManagerTheme.colors.primaryBackground
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                nextPaymentInfo.items?.forEachIndexed { index, item ->
                    NextPaymentItem(subscription = item)

                    if (index < nextPaymentInfo.items.lastIndex) {
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                HorizontalDivider(
                    color = SubManagerTheme.colors.primaryText,
                    thickness = 1.dp
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "총 결제 예정 금액",
                        style = SubManagerTheme.typography.b2Regular,
                        color = SubManagerTheme.colors.secondaryText
                    )
                    Text(
                        text = "${java.text.NumberFormat.getIntegerInstance().format(nextPaymentInfo?.totalAmount ?: 0)}원",
                        style = SubManagerTheme.typography.h2SemiBold,
                        color = SubManagerTheme.colors.primaryText
                    )
                }
            }
        } else {
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "모든 구독 리스트가 일시 정지 상태입니다 :(",
                style = SubManagerTheme.typography.c1SemiBold,
                color = SubManagerTheme.colors.disabledText,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "다가오는 결제 서비스 활성화를 하려면 일지 정지를 풀어주세요!",
                style = SubManagerTheme.typography.c1SemiBold,
                color = SubManagerTheme.colors.disabledText,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun NextPaymentSectionPreview() {
    SubManagerTheme {
        NextPaymentSection(
            nextPaymentInfo = NextPaymentInfo(
                date = "26년 1월 7일 (수)",
                totalAmount = 43000
            )
        )
    }
}
