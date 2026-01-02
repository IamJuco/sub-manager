package com.juco.common.util

import timber.log.Timber

object Logger {
    fun d(message: String) = Timber.d(message)
    fun i(message: String) = Timber.i(message)
    fun w(message: String) = Timber.w(message)
    fun e(message: String) = Timber.e(message)

    fun d(tag: String, message: String) = Timber.tag(tag).d(message)
    fun i(tag: String, message: String) = Timber.tag(tag).i(message)
    fun w(tag: String, message: String) = Timber.tag(tag).w(message)
    fun e(tag: String, message: String) = Timber.tag(tag).e(message)
}