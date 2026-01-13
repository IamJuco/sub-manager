package com.juco.subscription_edit.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.juco.common.util.PaymentCycle
import com.juco.common.util.PaymentCycleType
import com.juco.designsystem.component.button.SubManagerButton
import com.juco.designsystem.theme.SubManagerTheme

@Composable
fun PaymentCycleSection(
    modifier: Modifier = Modifier,
    paymentCycle: String,
    onValueChange: (PaymentCycle) -> Unit
) {
    var showCycleDialog by remember { mutableStateOf(false) }
    var initialTabForDialog by remember { mutableStateOf(PaymentCycleType.DAY) }

    if (showCycleDialog) {
        PaymentCycleDialog(
            initialTab = initialTabForDialog,
            onDismissRequest = { showCycleDialog = false },
            onConfirm = { resultCycle ->
                onValueChange(resultCycle)
                showCycleDialog = false
            }
        )
    }

    Column(
        modifier = modifier
    ) {
        Text(
            text = "결제 주기 수정",
            style = SubManagerTheme.typography.h3SemiBold,
            color = SubManagerTheme.colors.primaryText
        )

        Spacer(Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SubManagerButton(
                modifier = Modifier.weight(1f),
                text = "일 마다",
                onClick = {
                    initialTabForDialog = PaymentCycleType.DAY
                    showCycleDialog = true
                }
            )
            SubManagerButton(
                modifier = Modifier.weight(1f),
                text = "매월/매년",
                onClick = {
                    initialTabForDialog = PaymentCycleType.MONTH
                    showCycleDialog = true
                }
            )
        }

        if (paymentCycle.isNotEmpty()) {
            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = paymentCycle,
                    style = SubManagerTheme.typography.b1SemiBold,
                    color = SubManagerTheme.colors.primaryText
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "결제 합니다.",
                    style = SubManagerTheme.typography.b1Regular,
                    color = SubManagerTheme.colors.primaryText
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PaymentCycleSectionPreview() {
    SubManagerTheme {
        PaymentCycleSection(
            paymentCycle = "",
            onValueChange = {}
        )
    }
}