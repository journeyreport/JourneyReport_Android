package com.edicasoft.permissions

import android.app.Activity
import androidx.appcompat.app.AlertDialog

class DefaultShowRequestPermissionRationaleStrategy : ShowRequestPermissionRationaleStrategy {
    override fun showDescriptionForPermissionRequest(
        activity: Activity,
        message: String,
        cancel: () -> Unit,
        accept: () -> Unit
    ) {
        AlertDialog.Builder(activity)
            .setTitle(message)
            .setNegativeButton(android.R.string.cancel) { _, _ ->
                cancel.invoke()
            }
            .setPositiveButton(android.R.string.ok) { _, _ ->
                accept.invoke()
            }
            .show()
    }
}