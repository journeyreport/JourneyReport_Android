package com.edicasoft.journeyreport.dynamicFeature

import android.app.Activity
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import java.util.HashMap

class DynamicFeatureController(
    private val activity: Activity
) : IDynamicFeature {

    private val loadingModules = HashMap<String, DynamicFeatureCallback>()

    private val listener = SplitInstallStateUpdatedListener { state ->
        state.resolutionIntent()
        when (state.status()) {
            SplitInstallSessionStatus.DOWNLOADING -> {
                state.moduleNames()?.forEach {
                    loadingModules[it]?.downloadingCallback?.invoke()
                }
            }
            SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> {
                state.moduleNames()?.forEach { moduleName ->
                    loadingModules[moduleName]?.requestUserConfirmationCallback?.invoke(
                        activity,
                        manager,
                        state
                    )
                }
            }
            SplitInstallSessionStatus.INSTALLED -> {
                state.moduleNames()?.forEach {
                    loadingModules[it]?.installedCallback?.invoke()
                }
            }

            SplitInstallSessionStatus.INSTALLING -> {
                state.moduleNames()?.forEach {
                    loadingModules[it]?.installingCallback?.invoke()
                }
            }
            SplitInstallSessionStatus.FAILED -> {
                state.moduleNames()?.forEach {
                    loadingModules[it]?.failedCallback?.invoke(state.errorCode())
                }
            }
        }
    }

    private val manager: SplitInstallManager by lazy {
        SplitInstallManagerFactory.create(activity.application)
    }

    override fun run(name: Int, callback: DynamicFeatureCallback) = run(activity.getString(name),
        callback)

    override fun run(name: String, callback: DynamicFeatureCallback) {
        if (manager.installedModules.contains(name)) {
            callback.installedCallback.invoke()
        } else {
            loadingModules[name] = callback
            val request = SplitInstallRequest.newBuilder()
                .addModule(name)
                .build()

            // Load and install the requested feature module.
            manager.startInstall(request)
            manager.registerListener(listener)
        }
    }

    override fun cancelRun(name: String) {
        loadingModules.remove(name)
    }

    override fun uninstall(name: String) {
        manager.deferredUninstall(arrayListOf(name))
    }
}