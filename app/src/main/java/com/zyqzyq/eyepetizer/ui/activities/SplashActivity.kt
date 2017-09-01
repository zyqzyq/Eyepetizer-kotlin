package com.zyqzyq.eyepetizer.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.zyqzyq.eyepetizer.R
import android.os.Handler
import org.jetbrains.anko.startActivity


class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT = 3000L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed( {
            startActivity<MainActivity>()
            // close this activity
            finish()
        }, SPLASH_TIME_OUT)
    }

}
