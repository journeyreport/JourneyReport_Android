package com.edicasoft.journeyreport.dynamicFeature

import android.app.Activity
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallSessionState

data class DynamicFeatureCallback(
    val downloadingCallback: () -> Unit,
    val installingCallback: () -> Unit,
    val requestUserConfirmationCallback: (
        activity: Activity,
        manager: SplitInstallManager,
        state: SplitInstallSessionState
    ) -> Unit,
    val failedCallback: (Int) -> Unit,
    val installedCallback: () -> Unit
)
