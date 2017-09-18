package com.zyqzyq.eyepetizer.ui.activities

import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.zyqzyq.eyepetizer.R
import android.os.Handler
import android.os.Message
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.startActivity


/**
简单的启动页面
*/
class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT = 3000L
    private var image_alpha = 0f
    private var isRunning = false
    private var mHandler: Handler? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        splashImage.alpha = image_alpha
        enTv.typeface = Typeface.createFromAsset(assets,"fonts/Lobster-1.4.otf")
        cnTv.alpha = image_alpha
        enTv.alpha = image_alpha
        isRunning = true
        // 开启一个线程来让Alpha值递减
        Thread(Runnable {
            while (isRunning) {
                try {
                    Thread.sleep(200)
                    // 更新Alpha值
                    updateAlpha()
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }).start()

        // 接受消息之后更新imageview视图
        mHandler = object : Handler() {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                splashImage.alpha = image_alpha
                cnTv.alpha = image_alpha
                enTv.alpha = image_alpha
                // 设置textview显示当前的Alpha值
                // 刷新视图
                splashImage.invalidate()
            }
        }
        Handler().postDelayed( {
            startActivity<MainActivity>()
            // close this activity
            finish()
        }, SPLASH_TIME_OUT)
    }

    // 更新Alpha
    private fun updateAlpha() {
        if (image_alpha + .1f <= 1) {
            image_alpha += .1f
        } else {
            image_alpha = 1f
            isRunning = false
        }
        // 发送需要更新imageview视图的消息-->这里是发给主线程
        mHandler?.sendMessage(mHandler?.obtainMessage())
    }

}
