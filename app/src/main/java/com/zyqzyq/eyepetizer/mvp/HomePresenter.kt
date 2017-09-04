package com.zyqzyq.eyepetizer.mvp

import android.util.Log
import com.zyqzyq.eyepetizer.mvp.Model.HomeModel
import com.zyqzyq.eyepetizer.mvp.Model.bean.HomeBean
import com.zyqzyq.eyepetizer.mvp.Model.bean.HomeItem
import com.zyqzyq.eyepetizer.TAG

class HomePresenter(view: HomeContract.View): HomeContract.Presenter{
    private val homeView: HomeContract.View
    private val homeModel: HomeModel by lazy {  HomeModel() }
    var nextPageUrl: String? = null
    init {
        homeView = view
    }
    var mHomeBean: HomeBean? = null
    override fun requestFirstData() {
        homeModel.loadFirstData()
                .subscribe({ homeBean ->
                    nextPageUrl = homeBean.nextPageUrl
                    mHomeBean = homeBean
                    Log.d(TAG,nextPageUrl)
                    //
                    val newItemList = arrayListOf<HomeItem>()
                    homeBean.itemList.filter { item -> item.type == "videoCollectionWithCover" }
                            .forEach { newItemList.addAll(it.data!!.itemList) }
                    homeBean.itemList.filter { item -> item.type == "videoCollectionWithFollow" }
                            .forEach { newItemList.addAll(it.data!!.itemList) }
                    homeBean.itemList.addAll(newItemList)
                    homeView.setFirstData(homeBean)
                })

    }

    override fun requestMoreData() {
       nextPageUrl?.let {
           Log.d(TAG,nextPageUrl)
           homeModel.loadMoreData(it)
                   .subscribe({  homeBean ->
                       //过滤掉banner
                       val newItemList = arrayListOf<HomeItem>()

                       homeBean.itemList.filter { item -> item.type == "videoCollectionWithCover" }
                               .forEach { newItemList.addAll(it.data!!.itemList) }
                       homeBean.itemList.filter { item -> item.type == "videoCollectionWithFollow" }
                               .forEach { newItemList.addAll(it.data!!.itemList) }
                       homeBean.itemList.addAll(newItemList)
                       homeView.setMoreData(homeBean.itemList)
                       nextPageUrl = homeBean.nextPageUrl
                   })
       }
    }
}