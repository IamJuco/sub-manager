package com.juco.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.juco.designsystem.theme.SubManagerTheme

@Composable
fun HomeRoute(
    padding: PaddingValues
) {
    HomeScreen(
        padding = padding
    )

}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    padding: PaddingValues
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(SubManagerTheme.colors.primaryBackground)
            .padding(padding)
    ) {
        Text(
            text = "홈 화면"
        )
    }

}

@Composable
@Preview(showBackground = true)
private fun HomeScreenPreview() {
    SubManagerTheme {
        HomeScreen(
            padding = PaddingValues()
        )
    }
}