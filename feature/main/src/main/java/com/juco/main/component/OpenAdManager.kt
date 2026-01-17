package com.juco.main.component

import android.app.Activity
import android.content.Context
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.appopen.AppOpenAd
import com.juco.submanager.feature.main.BuildConfig
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.random.Random
import androidx.core.content.edit

private const val ADMOB_OPEN_ID = BuildConfig.ADMOB_OPEN_ID
private const val ADMOB_OPEN_PERCENT = 20

@Singleton
class OpenAdManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private var appOpenAd: AppOpenAd? = null
    private var isLoadingAd = false
    var isShowingAd = false
    private var loadTime: Long = 0
    private val prefs: SharedPreferences = context.getSharedPreferences("open_ad_prefs", Context.MODE_PRIVATE)

    fun loadAd(context: Context, onAdLoaded: (() -> Unit)? = null) {
        if (isLoadingAd || isAdAvailable()) return
        isLoadingAd = true
        val request = AdRequest.Builder().build()

        AppOpenAd.load(context, ADMOB_OPEN_ID, request, object : AppOpenAd.AppOpenAdLoadCallback() {
            override fun onAdLoaded(ad: AppOpenAd) {
                appOpenAd = ad
                isLoadingAd = false
                loadTime = Date().time
                onAdLoaded?.invoke()
            }

            override fun onAdFailedToLoad(error: LoadAdError) {
                isLoadingAd = false
            }
        })
    }

    private fun showAdIfAvailable(activity: Activity): Boolean {
        if (isShowingAd) return false

        if (!isAdAvailable()) {
            loadAd(activity)
            return false
        }

        appOpenAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                appOpenAd = null
                isShowingAd = false
                loadAd(activity)
            }

            override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                appOpenAd = null
                isShowingAd = false
                loadAd(activity)
            }

            override fun onAdShowedFullScreenContent() {
                isShowingAd = true
            }
        }

        isShowingAd = true
        appOpenAd?.show(activity)
        return true
    }

    fun showDailyOpenAd(activity: Activity): Boolean {
        val todayDate = SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(Date())
        val lastShownDate = prefs.getString("last_ad_date", "")

        if (todayDate == lastShownDate) {
            return false
        }
        val isShown = showAdIfAvailable(activity)
        if (isShown) {
            prefs.edit { putString("last_ad_date", todayDate) }
        }
        return isShown
    }

    fun showRandomOpenAd(activity: Activity) {
        val randomValue = Random.nextInt(1, 101)
        if (randomValue <= ADMOB_OPEN_PERCENT) {
            showAdIfAvailable(activity)
        }
    }

    private fun isAdAvailable(): Boolean {
        return appOpenAd != null && wasLoadTimeLessThanNHoursAgo(4)
    }

    private fun wasLoadTimeLessThanNHoursAgo(numHours: Long): Boolean {
        val dateDifference = Date().time - loadTime
        val numMilliSecondsPerHour: Long = 3600000
        return dateDifference < (numMilliSecondsPerHour * numHours)
    }
}