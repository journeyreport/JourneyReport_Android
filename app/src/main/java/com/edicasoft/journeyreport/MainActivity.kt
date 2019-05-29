package com.edicasoft.journeyreport

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.edicasoft.permissions.PermissionItem
import com.edicasoft.permissions.PermissionManager
import com.edicasoft.permissions.PermissionRequestCallback

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PermissionManager.Builder(this)
            .request(
                arrayOf(
                    PermissionItem(Manifest.permission.CAMERA, "camera is required"),
                    PermissionItem(Manifest.permission.RECORD_AUDIO, "audio recording is required")
                ),
                object : PermissionRequestCallback {
                    override fun permissionRequestResult(granted: Boolean, notGranted: Array<String>) {
                        Log.d("MainActivity", "grated - " + granted + ", not grated - " + notGranted.joinToString(","))
                    }
                }
            )
    }
}
