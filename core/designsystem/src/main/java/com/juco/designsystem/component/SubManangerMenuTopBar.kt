package com.juco.designsystem.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.juco.designsystem.theme.SubManagerTheme

@Composable
fun SubManagerMenuTopBar(
    modifier: Modifier = Modifier,
    title: String? = null,
    textColor: Color = SubManagerTheme.colors.primaryText,
    textStyle: TextStyle = SubManagerTheme.typography.h2SemiBold,
    @DrawableRes iconRes: Int? = null,
    iconSize: Dp = 24.dp,
    iconTint: Color = SubManagerTheme.colors.primaryText,
    onClickIcon: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ) {
        if (!title.isNullOrBlank()) {
            Text(
                text = title,
                color = textColor,
                style = textStyle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        if (iconRes != null) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = "icon",
                tint = iconTint,
                modifier = Modifier
                    .size(iconSize)
                    .align(Alignment.CenterEnd)
                    .clickable(onClick = onClickIcon)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SubManagerMenuTopBarPreview() {
    SubManagerTheme {
        Column {
            SubManagerMenuTopBar(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .heightIn(min = 32.dp),
                title = "Home"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SubManagerMenuTopBarPreview2() {
    SubManagerTheme {
        Column {
            SubManagerMenuTopBar(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .heightIn(min = 32.dp),
                title = "Home",
                iconRes = android.R.drawable.ic_dialog_info,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SubManagerMenuTopBarPreview3() {
    SubManagerTheme {
        Column {
            SubManagerMenuTopBar(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .heightIn(min = 32.dp),
                iconRes = android.R.drawable.ic_dialog_info,
            )
        }
    }
}