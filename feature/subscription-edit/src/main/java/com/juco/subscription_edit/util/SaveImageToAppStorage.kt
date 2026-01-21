package com.juco.subscription_edit.util

import android.content.Context
import android.net.Uri
import com.juco.common.util.Logger
import java.io.File
import java.io.FileOutputStream
import java.util.UUID

fun saveImageToAppStorage(context: Context, uri: Uri): File? {
    return try {
        val inputStream = context.contentResolver.openInputStream(uri) ?: return null
        val fileName = "profile_${UUID.randomUUID()}.jpg"
        Logger.d("0526fileName", fileName)
        val file = File(context.filesDir, fileName)
        Logger.d("0526file", file.toString())
        val outputStream = FileOutputStream(file)
        inputStream.copyTo(outputStream)

        inputStream.close()
        outputStream.close()

        file
    } catch (e: Exception) {
        Logger.e("0526SaveImageToAppStore", e.message.toString())
        null
    }
}