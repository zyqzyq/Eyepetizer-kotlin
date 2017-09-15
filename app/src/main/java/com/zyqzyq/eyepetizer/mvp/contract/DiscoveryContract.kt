package com.zyqzyq.eyepetizer.mvp.contract

import com.zyqzyq.eyepetizer.mvp.base.BasePresenter
import com.zyqzyq.eyepetizer.mvp.base.BaseView
import com.zyqzyq.eyepetizer.mvp.model.bean.DiscoveryTabInfo
import com.zyqzyq.eyepetizer.mvp.model.bean.HomeBean
import com.zyqzyq.eyepetizer.mvp.model.bean.HomeItem

interface DiscoveryContract{
    interface View: BaseView<Presenter>{
        fun setTabAndFragment(tabInfo: DiscoveryTabInfo)
    }
    interface ItemView: BaseView<ItemPresenter>{
        fun setTabItemData(itemList: ArrayList<HomeItem>)
    }
    interface Presenter: BasePresenter{
        /**
        请求tabInfo数据
        * */
        fun requestTabInfoData()
    }
    interface ItemPresenter: BasePresenter{
        fun requestTabItemData(url: String)
        fun requestMoreTabItemData()
    }
}