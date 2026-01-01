package com.juco.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    currentMenu: MainMenu?,
    onMenuSelected: (MainMenu) -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().background(Color(0xffFFFFFF))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().height(48.dp)
            ) {
                MainMenu.entries.forEach { menu ->
                    val selected = menu == currentMenu
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                            ) {
                                onMenuSelected(menu)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(
                                if (selected) menu.iconResIdTrue
                                else menu.iconResIdFalse
                            ),
                            contentDescription = menu.contentDescription,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            }
        }
    }
}