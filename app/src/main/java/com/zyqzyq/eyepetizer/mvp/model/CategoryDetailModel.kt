package com.zyqzyq.eyepetizer.mvp.model

import com.zyqzyq.eyepetizer.io_main
import com.zyqzyq.eyepetizer.mvp.model.bean.CategoryDetailTab
import com.zyqzyq.eyepetizer.mvp.model.bean.HomeBean
import com.zyqzyq.eyepetizer.network.Network
import io.reactivex.Observable

class CategoryDetailModel {

    fun loadData(id: Long): Observable<CategoryDetailTab> {
        return Network.service.getCategoryDetail(id).io_main()
    }

    fun loadMoreData(url: String): Observable<HomeBean> {
        return Network.service.getCategoryDetailTab(url).io_main()
    }
}