package com.juco.main

import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.appupdate.AppUpdateOptions
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.juco.common.util.Logger
import com.juco.designsystem.theme.SubManagerTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.core.net.toUri
import com.juco.main.component.OpenAdManager
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var openAdManager: OpenAdManager
    private lateinit var appUpdateManager: AppUpdateManager
    private var isUpdateAvailable by mutableStateOf(false)

    private val updateResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        if (result.resultCode != RESULT_OK) {
            Logger.e("Update", "업데이트 실패 또는 사용자가 취소함 (Code: ${result.resultCode})")
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        appUpdateManager = AppUpdateManagerFactory.create(this)
        fetchRemoteConfigAndCheckUpdate()
        val appVersionName = getAppVersionName()
        openAdManager.loadAd(this) {
            runOnUiThread {
                openAdManager.showDailyOpenAd(this)
            }
        }

        setContent {
            SubManagerTheme {
                MainScreen(
                    appVersion = appVersionName,
                    isAppUpdateAvailable = isUpdateAvailable,
                    onUpdateClick = { movePlayStore() },
                    showRandomOpenAd = { openAdManager.showRandomOpenAd(this) }
                )
            }
        }
    }

    override fun onStart() {
        super.onStart()
        openAdManager.showDailyOpenAd(this)
    }

    override fun onResume() {
        super.onResume()
        appUpdateManager.appUpdateInfo.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
                val options = AppUpdateOptions.newBuilder(AppUpdateType.IMMEDIATE).build()
                appUpdateManager.startUpdateFlowForResult(
                    appUpdateInfo,
                    updateResultLauncher,
                    options
                )
            }
        }
    }

    private fun fetchRemoteConfigAndCheckUpdate() {
        val remoteConfig = Firebase.remoteConfig
        val isDebuggable = (applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE) != 0

        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = if (isDebuggable) 0 else 3600
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(
            mapOf(
                "min_force_version_code" to -1,
                "min_recommend_version_code" to -1
            )
        )

        remoteConfig.fetchAndActivate().addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                val forceVersion = remoteConfig.getLong("min_force_version_code")
                val recommendVersion = remoteConfig.getLong("min_recommend_version_code")
                val currentVersion = getAppVersionCode()
                Logger.d(
                    "0526Update",
                    "현재: $currentVersion | 강제: $forceVersion | 권장: $recommendVersion"
                )

                if (currentVersion < forceVersion) {
                    checkAppUpdate(AppUpdateType.IMMEDIATE)
                } else if (currentVersion < recommendVersion) {
                    isUpdateAvailable = true
                    checkAppUpdate(AppUpdateType.FLEXIBLE)
                }
            } else {
                Logger.e("0526Update", "Remote Config 실패 -> 기본 체크 진행")
                checkAppUpdate(AppUpdateType.FLEXIBLE)
            }
        }
    }

    private fun getAppVersionCode(): Long {
        return try {
            val projectInfo = packageManager.getPackageInfo(packageName, 0)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                projectInfo.longVersionCode
            } else {
                @Suppress("DEPRECATION")
                projectInfo.versionCode.toLong()
            }
        } catch (e: PackageManager.NameNotFoundException) {
            Logger.e("0526Update", "버전 정보 가져오기 실패: ${e.message}")
            0L
        }
    }

    private fun getAppVersionName(): String {
        return try {
            val packageInfo = packageManager.getPackageInfo(packageName, 0)
            packageInfo.versionName ?: "Unknown"
        } catch (e: PackageManager.NameNotFoundException) {
            Logger.e("0526Update", "버전 이름 가져오기 실패: ${e.message}")
            "Unknown"
        }
    }

    private fun checkAppUpdate(updateType: Int) {
        appUpdateManager.appUpdateInfo.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE &&
                appUpdateInfo.isUpdateTypeAllowed(updateType)
            ) {
                val options = AppUpdateOptions.newBuilder(updateType).build()

                appUpdateManager.startUpdateFlowForResult(
                    appUpdateInfo,
                    updateResultLauncher,
                    options
                )
            }
        }
    }

    private fun movePlayStore() {
        val appPackageName = packageName
        try {
            startActivity(
                Intent(Intent.ACTION_VIEW, "market://details?id=$appPackageName".toUri()).apply {
                    setPackage("com.android.vending")
                }
            )
        } catch (e: Exception) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    "https://play.google.com/store/apps/details?id=$appPackageName".toUri()
                )
            )
            Logger.e("0526MovePlayStore", "앱 플레이스토어 진입 실패 : $e")
        }
    }
}
