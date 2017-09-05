package com.zyqzyq.eyepetizer.mvp

import android.util.Log
import com.zyqzyq.eyepetizer.mvp.Model.HomeModel
import com.zyqzyq.eyepetizer.mvp.Model.bean.HomeItem
import com.zyqzyq.eyepetizer.TAG

class HomePresenter(view: HomeContract.View): HomeContract.Presenter{
    private val homeView: HomeContract.View = view
    private val homeModel: HomeModel by lazy {  HomeModel() }
    var nextPageUrl: String? = null

//    第一次加载或者刷新
    override fun requestFirstData() {
        homeModel.loadFirstData()
                .subscribe({ homeBean ->
                    nextPageUrl = homeBean.nextPageUrl
                    Log.d(TAG,nextPageUrl)
                    //
                    val newItemList = arrayListOf<HomeItem>()
                    var bannerSize = 0
                    homeBean.itemList.filter { (type) -> type == "banner2" }
                            .forEach { item -> homeBean.itemList.remove(item) }
                    homeBean.itemList.filter { (type) -> type == "video" }
                            .forEach { bannerSize += 1 }
                    homeBean.itemList.filter { (type) -> type == "videoCollectionWithCover" }
                            .forEach { newItemList.addAll(it.data!!.itemList) }
                    homeBean.itemList.filter { (type) -> type == "videoCollectionWithFollow" }
                            .forEach { newItemList.addAll(it.data!!.itemList) }
                    homeBean.itemList.addAll(newItemList)
                    homeView.setFirstData(homeBean,bannerSize)
                })

    }

    override fun requestMoreData() {
       nextPageUrl?.let {
           Log.d(TAG,nextPageUrl)
           homeModel.loadMoreData(it)
                   .subscribe({  homeBean ->
                       //过滤掉banner
                       val newItemList = arrayListOf<HomeItem>()
                       homeBean.itemList.filter { (type) -> type == "videoCollectionWithCover" }
                               .forEach { newItemList.addAll(it.data!!.itemList) }
                       homeBean.itemList.filter { (type) -> type == "videoCollectionWithFollow" }
                               .forEach { newItemList.addAll(it.data!!.itemList) }
                       homeBean.itemList.addAll(newItemList)
                       homeView.setMoreData(homeBean.itemList)
                       nextPageUrl = homeBean.nextPageUrl
                   })
       }
    }
}