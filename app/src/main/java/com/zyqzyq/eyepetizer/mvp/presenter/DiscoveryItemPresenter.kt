package com.zyqzyq.eyepetizer.mvp.presenter

import com.zyqzyq.eyepetizer.mvp.contract.DiscoveryContract
import com.zyqzyq.eyepetizer.mvp.model.DiscoveryModel

class DiscoveryItemPresenter(view: DiscoveryContract.ItemView): DiscoveryContract.ItemPresenter{
    private val hotView: DiscoveryContract.ItemView = view
    private val hotModel by lazy { DiscoveryModel() }
    var nextPageUrl: String? = null
    override fun requestTabItemData(url: String) {
        hotModel.loadTabItemData(url)
                .subscribe({ it ->
                    hotView.setTabItemData(it.itemList)
                    nextPageUrl = it.nextPageUrl
                })
    }

    override fun requestMoreTabItemData() {
        nextPageUrl?.let {
            hotModel.loadMoreTabItemData(nextPageUrl!!)
                    .subscribe({ it ->
                        hotView.addTabItemData(it.itemList)
                        nextPageUrl = it.nextPageUrl
                    })}

    }
}