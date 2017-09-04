package com.zyqzyq.eyepetizer

import android.view.View
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

const val TAG = "HOME"

fun <T> Observable<T>.io_main(): Observable<T> {
    return subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}

fun View.durationFormat(duration: Long?): String {
    val minute = duration!! / 60
    val second = duration % 60
    if (minute <= 9) {
        if (second <= 9) {
            return "0${minute}' 0${second}''"
        } else {
            return "0${minute}' ${second}''"
        }
    } else {
        if (second <= 9) {
            return "${minute}' 0${second}''"
        } else {
            return "${minute}' ${second}''"
        }
    }
}