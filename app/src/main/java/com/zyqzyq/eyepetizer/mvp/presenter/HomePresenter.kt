package com.zyqzyq.eyepetizer.mvp.presenter

import android.util.Log

import com.zyqzyq.eyepetizer.mvp.model.bean.HomeItem
import com.zyqzyq.eyepetizer.TAG
import com.zyqzyq.eyepetizer.mvp.contract.HomeContract
import com.zyqzyq.eyepetizer.mvp.model.HomeModel

class HomePresenter(view: HomeContract.View): HomeContract.Presenter {
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

                    homeBean.itemList.filter { (type) -> type == "video" }
                            .forEach { bannerSize += 1
                                        newItemList.add(it)}
                    homeBean.itemList.filter { (type) -> type == "textFooter" }
                            .forEach { newItemList.add(it) }
                    homeBean.itemList.filter { (type) -> type == "videoCollectionWithCover" }
                            .forEach { newItemList.addAll(it.data!!.itemList) }
                    homeBean.itemList.filter { (type) -> type == "videoCollectionWithFollow" }
                            .forEach { newItemList.addAll(it.data!!.itemList) }
                    homeBean.itemList=(newItemList)
                    homeView.setFirstData(homeBean,bannerSize)
                })

    }

    override fun requestMoreData() {
       nextPageUrl?.let {
           Log.d(TAG,nextPageUrl)
           Log.d(TAG,"最后一个"+nextPageUrl!![it.length-1].toString())
           if (it[it.length-1].toString() == "5"){
               Log.d(TAG,"555555555555555555")
               nextPageUrl= nextPageUrl?.substring(0,it.length-2)+6
               Log.d(TAG,nextPageUrl)
           }

           homeModel.loadMoreData(nextPageUrl!!)
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