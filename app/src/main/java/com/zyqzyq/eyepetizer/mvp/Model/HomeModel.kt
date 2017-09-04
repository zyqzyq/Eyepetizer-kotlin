package com.zyqzyq.eyepetizer.mvp.Model

import android.util.Log
import com.zyqzyq.eyepetizer.io_main
import com.zyqzyq.eyepetizer.mvp.Model.bean.HomeBean
import com.zyqzyq.eyepetizer.network.Network
import io.reactivex.Observable

class HomeModel{
    fun loadFirstData(): Observable<HomeBean> {
        Log.d("HOME","load first data")
        return Network.service.getFirstHomeData(System.currentTimeMillis()).io_main()
    }

    fun loadMoreData(url: String): Observable<HomeBean> {
        Log.d("HOME","load mort data")
        return Network.service.getMoreHomeData(url).io_main()
    }
}