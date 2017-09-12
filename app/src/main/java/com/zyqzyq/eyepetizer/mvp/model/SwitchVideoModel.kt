package com.zyqzyq.eyepetizer.mvp.model

class SwitchVideoModel(var name: String?, var url: String?) {

    override fun toString(): String {
        return this.name!!
    }
}