package com.zyqzyq.eyepetizer.mvp.model

import com.zyqzyq.eyepetizer.io_main
import com.zyqzyq.eyepetizer.mvp.model.bean.DiscoveryTabInfo
import com.zyqzyq.eyepetizer.mvp.model.bean.HomeBean
import com.zyqzyq.eyepetizer.network.Network
import io.reactivex.Observable

class RankListModel{
    fun loadTabInfoData(): Observable<DiscoveryTabInfo>{
        return Network.service.getRankList().io_main()
    }
    fun loadTabItemData(url: String): Observable<HomeBean>{
        return Network.service.getRankListItemData(url).io_main()
    }
}