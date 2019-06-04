package com.edicasoft.journeyreport.presentation.feature.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.edicasoft.journeyreport.R
import com.edicasoft.journeyreport.data.feature.configs.ConfigDataSource
import com.edicasoft.journeyreport.dynamicFeature.DynamicFeatureCallbackBuilder
import com.edicasoft.journeyreport.dynamicFeature.IDynamicFeature
import com.edicasoft.journeyreport.presentation.base.pm.BaseViewModel
import com.edicasoft.journeyreport.presentation.base.pm.SingleLiveEvent
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val configDataSource: ConfigDataSource
) : BaseViewModel() {

    var dynamicFeature: IDynamicFeature? = null
    val loading: LiveData<Boolean> = MutableLiveData()

    val startOnbording = SingleLiveEvent<Int>()
    val startMain = SingleLiveEvent<Unit>()

    fun start() {
        if (!configDataSource.showOnbordingWasShown) {
            dynamicFeature?.run(MODULE_NAME, DynamicFeatureCallbackBuilder.build {
                startOnbording.postValue(MODULE_LINK)
            })
        } else {
            onOnbordingWatch()
        }
    }

    fun onOnbordingWatch() {
        configDataSource.showOnbordingWasShown = true
        startMain.postValue(Unit)
    }

    companion object {
        const val MODULE_NAME = R.string.dynamic_module_name
        const val MODULE_LINK = R.string.dynamic_module_activity
    }
}
