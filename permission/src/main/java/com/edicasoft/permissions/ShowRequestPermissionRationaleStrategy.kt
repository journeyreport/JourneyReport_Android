package com.edicasoft.permissions

import android.app.Activity

interface ShowRequestPermissionRationaleStrategy {
    fun showDescriptionForPermissionRequest(
        activity: Activity,
        message: String,
        cancel: () -> Unit,
        accept: () -> Unit
    )
}