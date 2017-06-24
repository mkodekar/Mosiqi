package com.merkmod.mosiqi.utilities

import android.content.pm.PackageManager

/**
 * Created by rkodekar on 6/22/17.
 */
object PermissionUtil {

    fun verifyPermissions(grantResults: IntArray) : Boolean {
        if (grantResults.isEmpty()) {
            return false
        }

        grantResults.forEach {

            if (it !=  PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }
}