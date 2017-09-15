package com.zyqzyq.eyepetizer.mvp.model

import com.zyqzyq.eyepetizer.io_main
import com.zyqzyq.eyepetizer.mvp.model.bean.DiscoveryTabInfo
import com.zyqzyq.eyepetizer.mvp.model.bean.HomeBean
import com.zyqzyq.eyepetizer.network.Network
import io.reactivex.Observable

class DiscoveryModel{
    fun loadTabInfoData(): Observable<DiscoveryTabInfo>{
        return Network.service.getDiscovery().io_main()
    }
    fun loadTabItemData(url: String): Observable<HomeBean>{
        return Network.service.getDiscoveryItemData(url).io_main()
    }
    fun loadMoreTabItemData(url: String): Observable<HomeBean>{
        return Network.service.getMoreDiscoveryItemData(url).io_main()
    }
}