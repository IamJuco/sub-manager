package com.juco.subscription_edit.component

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.juco.common.util.Logger
import com.juco.designsystem.util.QuickStartDefaultItem
import com.juco.designsystem.theme.SubManagerTheme
import com.juco.submanager.core.designsystem.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ProfileSection(
    modifier: Modifier = Modifier,
    thumbnail: String?,
    onClickThumbnailChange: (String) -> Unit,
    onShowSnackBar: (String) -> Unit
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        uri?.let {
            coroutineScope.launch(Dispatchers.IO) {
                runCatching {
                    val flag = Intent.FLAG_GRANT_READ_URI_PERMISSION
                    context.contentResolver.takePersistableUriPermission(it, flag)
                }.onFailure {
                    Logger.e("0526ProfileSection", "갤러리에서 사진 가져오기 실패")
                    onShowSnackBar("갤러리를 불러오는데 실패했습니다.")
                }
                onClickThumbnailChange(it.toString())
            }
        }
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "프로필 수정",
            style = SubManagerTheme.typography.h3SemiBold,
            color = SubManagerTheme.colors.primaryText
        )

        Spacer(Modifier.height(4.dp))

        Box(
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape)
                .background(SubManagerTheme.colors.primaryBackground)
                .border(1.dp, SubManagerTheme.colors.secondaryText.copy(alpha = 0.2f), CircleShape)
                .clickable {
                    photoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                },
            contentAlignment = Alignment.Center
        ) {
            if (QuickStartDefaultItem.isDefaultIcon(thumbnail ?: "")) {
                Image(
                    modifier = Modifier.padding(14.dp),
                    painter = painterResource(
                        id = QuickStartDefaultItem.getResIdByKey(
                            thumbnail ?: ""
                        )
                    ),
                    contentDescription = null
                )
            } else {
                AsyncImage(
                    model = thumbnail,
                    contentDescription = null,
                    placeholder = painterResource(R.drawable.ic_app_logo),
                    error = painterResource(R.drawable.ic_app_logo)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileSectionPreview() {
    SubManagerTheme {
        ProfileSection(
            thumbnail = "",
            onClickThumbnailChange= {},
            onShowSnackBar = {}
        )
    }
}