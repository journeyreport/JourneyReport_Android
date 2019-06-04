package com.edicasoft.journeyreport.presentation.feature.splash

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.edicasoft.journeyreport.BuildConfig
import com.edicasoft.journeyreport.MainActivity
import com.edicasoft.journeyreport.dynamicFeature.DynamicFeatureController
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class SplashActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SplashViewModel::class.java)
        viewModel.dynamicFeature = DynamicFeatureController(this)

        viewModel.startOnbording.observe(this, Observer<Int> { t ->
            t?.let { obordingActivity ->
                val intent = Intent().setClassName(
                    BuildConfig.APPLICATION_ID,
                    getString(obordingActivity)
                )
                startActivityForResult(intent, ONBORDING_REQUEST_CODE)
            }
        })

        viewModel.startMain.observe(this, Observer {
            startActivity(
                Intent(
                    this,
                    MainActivity::class.java
                ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            )
        })

        viewModel.start()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ONBORDING_REQUEST_CODE) {
            viewModel.onOnbordingWatch()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.dynamicFeature = null
    }

    companion object {
        private const val ONBORDING_REQUEST_CODE = 1
    }
}
