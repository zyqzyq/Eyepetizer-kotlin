package com.zyqzyq.eyepetizer.mvp.model


import com.zyqzyq.eyepetizer.io_main
import com.zyqzyq.eyepetizer.mvp.model.bean.HomeBean
import io.reactivex.Observable
import com.zyqzyq.eyepetizer.network.Network

class PlayModel{
    fun loadRelatedData(id: Long): Observable<HomeBean>{
        return Network.service.getRelatedData(id).io_main()
    }
}