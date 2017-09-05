package com.zyqzyq.eyepetizer.ui

import android.app.Application
import com.zyqzyq.eyepetizer.util.DisplayManager
import kotlin.properties.Delegates

class App: Application(){
    companion object {
        var instance : App by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        /*
        * 初始化
        * */
        DisplayManager.init(this)
    }
}