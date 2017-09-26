package com.zyqzyq.eyepetizer.mvp.presenter


import com.zyqzyq.eyepetizer.mvp.contract.FollowContract
import com.zyqzyq.eyepetizer.mvp.model.FollowModel

class FollowPresenter(view: FollowContract.View): FollowContract.Presenter {
    private val followView: FollowContract.View = view
    private val followModel by lazy { FollowModel() }
    var nextPageUrl: String? = null
    override fun requestFirstData() {

        followModel.loadFollowData()
                .subscribe({ it ->
                    followView.setFirstData(it.itemList)
                    nextPageUrl = it.nextPageUrl
                })
    }

    override fun requestMoreData() {
        nextPageUrl?.let {
            followModel.loadMoreFollowData(nextPageUrl!!)
                    .subscribe({ it ->
                        followView.setMoreData(it.itemList)
                        nextPageUrl = it.nextPageUrl
                    })}

    }
}