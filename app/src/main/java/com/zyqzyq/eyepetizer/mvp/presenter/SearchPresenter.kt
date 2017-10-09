package com.zyqzyq.eyepetizer.mvp.presenter


import com.zyqzyq.eyepetizer.mvp.contract.SearchContract
import com.zyqzyq.eyepetizer.mvp.model.SearchModel

class SearchPresenter(view: SearchContract.View): SearchContract.Presenter{
    private val searchView: SearchContract.View = view
    private val searchModel by lazy { SearchModel() }

    var nextPageUrl: String? = null

    override fun requestFirstData(data: String) {
        searchModel.loadFirstData(data)
                .subscribe({
                    nextPageUrl = it.nextPageUrl
                    searchView.setFirstData(it.itemList)
                })
    }

    override fun requestMoreData() {
        nextPageUrl?.let {
            searchModel.loadMoreData(it)
                    .subscribe({
                        searchView.setMoreData(it.itemList)
                        nextPageUrl = it.nextPageUrl
                    })
        }
    }
}