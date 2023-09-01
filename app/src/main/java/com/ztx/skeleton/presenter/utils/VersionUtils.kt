package com.ztx.skeleton.presenter.utils

import android.content.Context
import android.content.pm.PackageManager

object VersionUtils {
    fun getAppVersion(context: Context): String {
        return try {
            val manager = context.packageManager
            val info = manager.getPackageInfo(context.packageName, PackageManager.GET_ACTIVITIES)
            if (info != null) info.versionName else ""
        } catch (error: PackageManager.NameNotFoundException) {
            ""
        }
    }
}