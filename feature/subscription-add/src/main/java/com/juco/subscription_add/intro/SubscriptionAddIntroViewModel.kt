package com.juco.subscription_add.intro

import androidx.lifecycle.ViewModel
import com.juco.local.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SubscriptionAddIntroViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : ViewModel() {

}