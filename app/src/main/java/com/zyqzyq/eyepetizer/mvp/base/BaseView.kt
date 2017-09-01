package com.zyqzyq.eyepetizer.mvp.base

interface BaseView<T>{
    fun setPresenter(presenter: T)
}