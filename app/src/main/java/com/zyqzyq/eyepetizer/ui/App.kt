package com.zyqzyq.eyepetizer.ui

import android.app.Application
import kotlin.properties.Delegates

class App: Application(){
    companion object {
        var instance : App by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}