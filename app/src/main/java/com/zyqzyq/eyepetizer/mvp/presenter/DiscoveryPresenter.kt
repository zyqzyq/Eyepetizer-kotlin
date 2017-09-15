package com.zyqzyq.eyepetizer.mvp.presenter

import com.zyqzyq.eyepetizer.mvp.contract.DiscoveryContract
import com.zyqzyq.eyepetizer.mvp.model.DiscoveryModel

class DiscoveryPresenter(view: DiscoveryContract.View): DiscoveryContract.Presenter{
    private val discoveryView: DiscoveryContract.View = view
    private val discoveryModel by lazy { DiscoveryModel() }
    override fun requestTabInfoData() {
        discoveryModel.loadTabInfoData()
                .subscribe({    it ->
                    discoveryView.setTabAndFragment(it)
                })
    }
}