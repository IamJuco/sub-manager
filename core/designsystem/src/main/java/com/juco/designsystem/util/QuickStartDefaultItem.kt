package com.juco.designsystem.util

import com.juco.submanager.core.designsystem.R

enum class QuickStartDefaultItem(val key: String, val resId: Int) {
    NETFLIX("NETFLIX", R.drawable.ic_netflix),
    YOUTUBE("YOUTUBE", R.drawable.ic_youtube),
    DISNEY("DISNEY", R.drawable.ic_disney),
    APPLE("APPLE", R.drawable.ic_apple),
    OPENAI("OPENAI", R.drawable.ic_chatgpt),
    NOTION("NOTION", R.drawable.ic_notion),
    ADOBE("ADOBE", R.drawable.ic_adobe),
    MICROSOFT("MICROSOFT", R.drawable.ic_microsoft),
    FIGMA("FIGMA", R.drawable.ic_figma),
    DEFAULT("DEFAULT", R.drawable.ic_app_logo);

    companion object {
        fun getResIdByKey(key: String): Int {
            return entries.find { it.key == key }?.resId ?: DEFAULT.resId
        }

        fun isDefaultIcon(key: String): Boolean {
            return entries.any { it.key == key }
        }
    }
}