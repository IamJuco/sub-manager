package com.juco.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.juco.designsystem.component.SubManagerMenuTopBar
import com.juco.designsystem.theme.SubManagerTheme
import com.juco.home.component.NextPaymentEmptyDataButton

@Composable
fun HomeEmptyDataScreen(
    modifier: Modifier = Modifier,
    padding: PaddingValues,
    navigateToSubscriptionAddIntro: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(SubManagerTheme.colors.primaryBackground)
            .padding(padding)
            .padding(horizontal = 16.dp)
    ) {
        SubManagerMenuTopBar(
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
            title = "구독 매니저"
        )
        NextPaymentEmptyDataButton(
            onClick = { navigateToSubscriptionAddIntro() }
        )

        Spacer(Modifier.height(32.dp))

        Text(
            text = "다가오는 결제 서비스",
            style = SubManagerTheme.typography.h3SemiBold,
            color = SubManagerTheme.colors.primaryText
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "표시할 정보가 없습니다 :(",
            style = SubManagerTheme.typography.c1SemiBold,
            color = SubManagerTheme.colors.disabledText,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "구독 리스트",
            style = SubManagerTheme.typography.h3SemiBold,
            color = SubManagerTheme.colors.primaryText
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "표시할 구독 리스트가 없습니다 :(",
            style = SubManagerTheme.typography.c1SemiBold,
            color = SubManagerTheme.colors.disabledText,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

    }
}

@Composable
@Preview(showBackground = true)
private fun HomeEmptyDataScreenPreview() {
    SubManagerTheme {
        HomeEmptyDataScreen(
            padding = PaddingValues(),
            navigateToSubscriptionAddIntro = {}
        )
    }
}