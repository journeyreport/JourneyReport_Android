package com.edicasoft.journeyreport

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.edicasoft.journeyreport.dynamicFeature.DynamicFeatureCallbackBuilder
import com.edicasoft.journeyreport.dynamicFeature.DynamicFeatureController

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val feature = DynamicFeatureController(this)
        feature.run(getString(R.string.dynamic_module_name), DynamicFeatureCallbackBuilder.build {
            Intent().setClassName(BuildConfig.APPLICATION_ID,
                getString(R.string.dynamic_module_activity))
                .also {
                    startActivity(it)
                }
        })
    }
}
