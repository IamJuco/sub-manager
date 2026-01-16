package com.juco.main.component

import android.app.Activity
import android.content.Context
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.appopen.AppOpenAd
import com.juco.common.util.Logger
import com.juco.submanager.feature.main.BuildConfig
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OpenAdManager @Inject constructor() {
    private var appOpenAd: AppOpenAd? = null
    private var isLoadingAd = false
    var isShowingAd = false
    private var loadTime: Long = 0
    private val ADMOB_OPEN_ID = BuildConfig.ADMOB_OPEN_ID

    fun loadAd(context: Context, onAdLoaded: (() -> Unit)? = null) {
        if (isLoadingAd || isAdAvailable()) return
        isLoadingAd = true
        val request = AdRequest.Builder().build()

        AppOpenAd.load(context, ADMOB_OPEN_ID, request, object : AppOpenAd.AppOpenAdLoadCallback() {
            override fun onAdLoaded(ad: AppOpenAd) {
                Logger.d("AdMob", "광고 로드 성공")
                appOpenAd = ad
                isLoadingAd = false
                loadTime = Date().time
                onAdLoaded?.invoke()
            }

            override fun onAdFailedToLoad(error: LoadAdError) {
                Logger.e("AdMob", "광고 로드 실패: ${error.message}")
                isLoadingAd = false
            }
        })
    }

    fun showAdIfAvailable(activity: Activity): Boolean {
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

    private fun isAdAvailable(): Boolean {
        return appOpenAd != null && wasLoadTimeLessThanNHoursAgo(4)
    }

    private fun wasLoadTimeLessThanNHoursAgo(numHours: Long): Boolean {
        val dateDifference = Date().time - loadTime
        val numMilliSecondsPerHour: Long = 3600000
        return dateDifference < (numMilliSecondsPerHour * numHours)
    }
}