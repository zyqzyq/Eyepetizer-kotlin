package com.zyqzyq.eyepetizer.mvp

import com.zyqzyq.eyepetizer.mvp.Model.HomeModel
import com.zyqzyq.eyepetizer.mvp.Model.bean.HomeBean

class HomePresenter(view: HomeContract.View): HomeContract.Presenter{
    private val homeView: HomeContract.View
    private val homeModel: HomeModel by lazy {  HomeModel() }
    var nextPageUrl: String? = null
    init {
        homeView = view
    }
    var bannerHomeBean: HomeBean? = null
    override fun requestFirstData() {
        homeModel.loadFirstData()
                .flatMap({ homeBean ->
                    bannerHomeBean = homeBean
                    homeModel.loadMoreData(homeBean.nextPageUrl)
                })
                .subscribe({ homeBean ->
                    nextPageUrl = homeBean.nextPageUrl
                    //过滤掉banner2item
                    homeView.setFirstData(bannerHomeBean!!)
                }, { t ->
                    t.printStackTrace()
                    homeView.onError()
                })

    }

    override fun requestMoreData() {
       nextPageUrl?.let {
           homeModel.loadMoreData(it)
                   .subscribe({  homeBean ->
                       //过滤掉banner2item
                       homeView.setMoreData(homeBean.itemList)
                       nextPageUrl = homeBean.nextPageUrl
                   })
       }
    }
}