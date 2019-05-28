package com.edicasoft.permissions

import android.os.Bundle
import androidx.fragment.app.Fragment

internal class PermissionFragment : Fragment() {

    internal lateinit var callbackPermission: PermissionRequestCallback
    internal lateinit var showRequestPermissionRationaleStrategy: ShowRequestPermissionRationaleStrategy

    private val permissions: ArrayList<PermissionItem> by lazy {
        checkNotNull(arguments).getParcelableArrayList<PermissionItem>(
            PERMISSIONS
        )
    }

    private val permissionNames: Array<String>
        get() = permissions.map { it.permission }.toTypedArray()

    private val rationaleAsked = mutableListOf<String>()

    private val rationalePermissions: List<PermissionItem>
        get() = permissions.filter { PermissionManager.isRationale(checkNotNull(activity), it.permission) }

    private val rationaleNotAsked: List<PermissionItem>
        get() = rationalePermissions.filterNot { rationaleAsked.contains(it.permission) }

    private val notGrantedPermissions: List<PermissionItem>
        get() = permissions.filterNot { PermissionManager.isGranted(checkNotNull(activity), it.permission) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        performPermissionsCheck()
    }

    private fun performPermissionsCheck() {
        if (notGrantedPermissions.isEmpty() || rationaleNotAsked.isEmpty()) {
            publishCallback()
        } else {
            showPermissionDescription()
        }
    }

    private fun publishCallback() {
        callbackPermission.permissionRequestResult(
            notGrantedPermissions.isEmpty(),
            rationaleAsked.toTypedArray()
        )
        activity?.supportFragmentManager?.popBackStack()
    }

    private fun showPermissionDescription() {
        val activity = activity ?: return
        val permission = rationaleNotAsked[0]

        showRequestPermissionRationaleStrategy.showDescriptionForPermissionRequest(
            activity,
            permission.description,
            {
                rationaleAsked.add(permission.permission)
                performPermissionsCheck()
            },
            {
                rationaleAsked.add(permission.permission)
                requestPermissions(
                    arrayOf(permission.permission),
                    PERMISSIONS_REQUEST
                )
            }
        )
    }

    internal fun runPermissionRequest() {
        requestPermissions(
            permissionNames,
            PERMISSIONS_REQUEST
        )
    }

    companion object {
        private const val PERMISSIONS_REQUEST = 100
        private const val PERMISSIONS = "permissions"
        fun newInstance(permissions: ArrayList<PermissionItem>): PermissionFragment {
            return PermissionFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(PERMISSIONS, permissions)
                }
            }
        }
    }
}
