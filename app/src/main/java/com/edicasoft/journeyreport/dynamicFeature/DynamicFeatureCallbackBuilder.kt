package com.edicasoft.journeyreport.dynamicFeature

import android.app.Activity
import android.util.Log
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallSessionState

object DynamicFeatureCallbackBuilder {
    fun build(
        downloadingCallback: () -> Unit = doNothing,
        installingCallback: () -> Unit = doNothing,
        requestUserConfirmationCallback: (Activity, SplitInstallManager, SplitInstallSessionState) -> Unit =
            defaultRequestConfirmation,
        failedCallback: (Int) -> Unit = doNothingOnError,
        installedCallback: () -> Unit
    ) = DynamicFeatureCallback(
        downloadingCallback,
        installingCallback,
        requestUserConfirmationCallback,
        failedCallback,
        installedCallback
    )

    private const val CONFIRMATION_REQUEST_CODE = 101
    val doNothing = {}
    val doNothingOnError: (e: Int) -> Unit = { e -> Log.d("FeatureCallbackBuilder", "error $e") }
    val defaultRequestConfirmation: (c: Activity, m: SplitInstallManager, s: SplitInstallSessionState) -> Unit =
        { c, m, s -> m.startConfirmationDialogForResult(s, c, CONFIRMATION_REQUEST_CODE) }
}