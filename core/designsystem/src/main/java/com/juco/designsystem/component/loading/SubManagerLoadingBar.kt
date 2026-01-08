package com.juco.designsystem.component.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.juco.designsystem.theme.SubManagerTheme

@Composable
fun SubManagerLoadingBar(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.3f))
            .clickable(enabled = false) {}
        ,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = SubManagerTheme.colors.primaryText
        )
    }
}