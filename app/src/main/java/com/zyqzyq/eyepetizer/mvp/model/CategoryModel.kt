package com.zyqzyq.eyepetizer.mvp.model

import com.zyqzyq.eyepetizer.io_main
import com.zyqzyq.eyepetizer.mvp.model.bean.CategoryBean
import com.zyqzyq.eyepetizer.network.Network
import io.reactivex.Observable

class CategoryModel {

    fun loadData(): Observable<ArrayList<CategoryBean>> {
        return Network.service.getCategoryData().io_main()
    }
}