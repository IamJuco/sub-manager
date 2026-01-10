package com.juco.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.juco.designsystem.component.SubManagerMenuTopBar
import com.juco.designsystem.component.button.SubManagerFloatingButton
import com.juco.designsystem.component.loading.SubManagerLoadingBar
import com.juco.designsystem.theme.SubManagerTheme
import com.juco.home.component.NextPaymentSection
import com.juco.home.component.SubscriptionItem
import com.juco.home.model.NextPaymentInfo
import com.juco.home.model.SubscriptionInfo
import com.juco.home.state.HomeUiState

@Composable
fun HomeRoute(
    padding: PaddingValues,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToSubscriptionAddIntro: () -> Unit,
    navigateToSubscriptionDetail: (Long) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (val state = uiState) {
        is HomeUiState.Loading -> {
            SubManagerLoadingBar()
        }

        is HomeUiState.Empty -> {
            HomeEmptyDataScreen(
                padding = padding,
                navigateToSubscriptionAddIntro = navigateToSubscriptionAddIntro
            )
        }

        is HomeUiState.Success -> {
            HomeScreen(
                padding = padding,
                subscriptionList = state.subscriptionList,
                nextPaymentInfo = state.nextPaymentInfo,
                navigateToSubscriptionAddIntro = navigateToSubscriptionAddIntro,
                navigateToSubscriptionDetail = navigateToSubscriptionDetail
            )
        }
    }
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    padding: PaddingValues,
    navigateToSubscriptionAddIntro: () -> Unit,
    navigateToSubscriptionDetail: (Long) -> Unit,
    subscriptionList: List<SubscriptionInfo> = emptyList(),
    nextPaymentInfo: NextPaymentInfo? = null
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(SubManagerTheme.colors.primaryBackground)
            .padding(padding)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            SubManagerMenuTopBar(
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
                title = "구독 매니저"
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                item {

                    NextPaymentSection(
                        nextPaymentInfo = nextPaymentInfo
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Text(
                        text = "구독 리스트",
                        style = SubManagerTheme.typography.h3SemiBold,
                        color = SubManagerTheme.colors.primaryText
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                }

                items(
                    count = subscriptionList.size,
                    key = { index -> subscriptionList[index].subId ?: index }
                ) { index ->
                    val item = subscriptionList[index]

                    SubscriptionItem(
                        subscription = item,
                        onClick = {
                            navigateToSubscriptionDetail(
                                item.subId ?: 0
                            )
                        }
                    )

                    if (index < subscriptionList.lastIndex) {
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }

                item {
                    Spacer(Modifier.height(80.dp))
                }
            }
        }

        SubManagerFloatingButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            onClick = navigateToSubscriptionAddIntro
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun HomeScreenPreview() {
    SubManagerTheme {
        val dummyList = listOf(
            SubscriptionInfo(
                subId = 1L,
                name = "Netflix",
                price = 17000L,
                description = "프리미엄 4K",
                nextPaymentDate = "26년 1월 7일 (수)",
                dDay = "D-2"
            ),
            SubscriptionInfo(
                subId = 2L,
                name = "Youtube Premium",
                price = 14900L,
                description = "가족 요금제",
                nextPaymentDate = "26년 1월 7일 (수)",
                dDay = "D-2"
            ),
            SubscriptionInfo(
                subId = 3L,
                name = "Spotify",
                price = 11900L,
                description = "개인",
                nextPaymentDate = "26년 1월 15일 (목)",
                dDay = "D-10"
            ),
        )
        val dummyNextPayment = NextPaymentInfo(
            date = "26년 1월 7일 (수)",
            dDay = "D-2",
            totalAmount = 31900L,
            items = listOf(dummyList[0], dummyList[1])
        )

        HomeScreen(
            padding = PaddingValues(),
            subscriptionList = dummyList,
            nextPaymentInfo = dummyNextPayment,
            navigateToSubscriptionAddIntro = {},
            navigateToSubscriptionDetail = {}
        )
    }
}