package com.edicasoft.onbordingFeature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_onbording.*

class OnbordingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onbording)
        setResult(RESULT_OK)

        onbordingViewpager.adapter = OnbordingAdapter(supportFragmentManager)

        onbordingClose.setOnClickListener { finish() }
    }
}
