package com.zyqzyq.eyepetizer.mvp.model


import com.zyqzyq.eyepetizer.io_main
import com.zyqzyq.eyepetizer.mvp.model.bean.HomeBean
import com.zyqzyq.eyepetizer.network.Network
import io.reactivex.Observable

class SearchModel{
    fun loadFirstData(data: String): Observable<HomeBean> {
        return Network.service.getSearchData(data).io_main()
    }

    fun loadMoreData(url: String): Observable<HomeBean> {
        return Network.service.getMoreSearchData(url).io_main()
    }
}