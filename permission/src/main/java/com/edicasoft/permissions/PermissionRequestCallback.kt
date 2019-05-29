package com.edicasoft.permissions

interface PermissionRequestCallback {
    fun permissionRequestResult(granted: Boolean, notGranted: Array<String>)
}