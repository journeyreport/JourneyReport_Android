package com.edicasoft.journeyreport.data.feature.configs

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class PrefsConfigDataSource @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ConfigDataSource {

    override var showOnbordingWasShown: Boolean
        get() = sharedPreferences.getBoolean(ONBORDING, false)
        set(value) {
            sharedPreferences.edit { putBoolean(ONBORDING, value) }
        }

    private companion object {
        const val PREFIX = "PrefsConfigDataSource."
        const val ONBORDING = PREFIX + "ONBORDING"
    }
}