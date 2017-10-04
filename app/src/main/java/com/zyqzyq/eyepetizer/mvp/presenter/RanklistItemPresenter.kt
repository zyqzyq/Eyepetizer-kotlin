package com.zyqzyq.eyepetizer.mvp.presenter

import com.zyqzyq.eyepetizer.mvp.contract.DiscoveryContract
import com.zyqzyq.eyepetizer.mvp.contract.RankListContract
import com.zyqzyq.eyepetizer.mvp.model.DiscoveryModel

class RanklistItemPresenter(view: RankListContract.ItemView): RankListContract.ItemPresenter{
    private val rankListView: RankListContract.ItemView = view
    private val rankListModel by lazy { DiscoveryModel() }
    var nextPageUrl: String? = null
    override fun requestTabItemData(url: String) {
        rankListModel.loadTabItemData(url)
                .subscribe({ it ->
                    rankListView.setTabItemData(it.itemList)
                    nextPageUrl = it.nextPageUrl
                })
    }

}