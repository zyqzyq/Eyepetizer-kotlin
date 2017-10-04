package com.zyqzyq.eyepetizer.mvp.presenter


import com.zyqzyq.eyepetizer.mvp.contract.RankListContract
import com.zyqzyq.eyepetizer.mvp.model.RankListModel

class RankListPresenter(view: RankListContract.View): RankListContract.Presenter{
    private val rankListView: RankListContract.View = view
    private val rankListModel by lazy { RankListModel() }
    override fun requestTabInfoData() {
        rankListModel.loadTabInfoData()
                .subscribe({   it ->
                    rankListView.setTabAndFragment(it)
                })
    }
}