package com.edicasoft.permissions

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PermissionItem(
    val permission: String,
    val description: String
) : Parcelable