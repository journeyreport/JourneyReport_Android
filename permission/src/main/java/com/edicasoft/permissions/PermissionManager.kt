package com.edicasoft.permissions

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

class PermissionManager private constructor(private val builder: Builder) {

    private fun request(permissions: Array<PermissionItem>, callback: PermissionRequestCallback) {
        if (permissions.map { it.permission }.all { isGranted(builder.activity, it) }) {
            callback.permissionRequestResult(true, emptyArray())
        } else {
            val fragment = PermissionFragment.newInstance(
                ArrayList(permissions.filterNot { isGranted(builder.activity, it.permission) })
            ).apply {
                showRequestPermissionRationaleStrategy = builder.rationaleStrategy
                callbackPermission = callback
            }
            builder.activity.supportFragmentManager.beginTransaction().add(fragment, null)
                .runOnCommit { fragment.runPermissionRequest() }
                .commit()
        }
    }

    class Builder(internal val activity: FragmentActivity) {
        internal var rationaleStrategy: ShowRequestPermissionRationaleStrategy =
            DefaultShowRequestPermissionRationaleStrategy()

        fun setShowRequestPermissionRationaleStrategy(rationaleStrategy: ShowRequestPermissionRationaleStrategy) =
            apply { this.rationaleStrategy = rationaleStrategy }

        fun request(permission: PermissionItem, callback: PermissionRequestCallback): PermissionManager =
            request(arrayOf(permission), callback)

        fun request(permissions: Array<PermissionItem>, callback: PermissionRequestCallback): PermissionManager =
            PermissionManager(this).apply {
                request(permissions, callback)
            }
    }

    companion object {
        fun isGranted(context: Context, permission: String) =
            ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED

        fun isRationale(activity: FragmentActivity, permission: String) =
            ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)
    }
}