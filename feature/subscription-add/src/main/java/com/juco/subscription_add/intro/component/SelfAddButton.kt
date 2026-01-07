package com.juco.subscription_add.intro.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.juco.designsystem.theme.SubManagerTheme
import com.juco.submanager.core.designsystem.R

@Composable
fun SelfAddButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
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
            .clickable {
                onClick()
            }
    ) {
        Column(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "여기를 클릭해서",
                style = SubManagerTheme.typography.b1SemiBold,
                color = SubManagerTheme.colors.primaryText
            )

            Text(
                text = "직접 추가할 수 있습니다!",
                style = SubManagerTheme.typography.b1SemiBold,
                color = SubManagerTheme.colors.primaryText
            )

            Spacer(modifier = Modifier.height(8.dp))

            Icon(
                modifier = Modifier.size(32.dp),
                painter = painterResource(R.drawable.ic_plus_2_fill_false),
                contentDescription = "Add OTT",
                tint = SubManagerTheme.colors.primaryText
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun SelfAddButtonPreview() {
    SubManagerTheme {
        SelfAddButton(
            onClick = {}
        )
    }
}