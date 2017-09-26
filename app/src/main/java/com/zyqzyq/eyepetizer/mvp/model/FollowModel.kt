package com.zyqzyq.eyepetizer.mvp.model

import com.zyqzyq.eyepetizer.io_main
import com.zyqzyq.eyepetizer.mvp.model.bean.HomeBean
import com.zyqzyq.eyepetizer.network.Network
import io.reactivex.Observable

class FollowModel {
    fun loadFollowData(): Observable<HomeBean> {
        return Network.service.getFollowData().io_main()
    }

    fun loadMoreFollowData(url: String): Observable<HomeBean> {
        return Network.service.getMoreFollowData(url).io_main()
    }
}