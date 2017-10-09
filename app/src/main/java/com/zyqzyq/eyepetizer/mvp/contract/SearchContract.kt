package com.zyqzyq.eyepetizer.mvp.contract

import com.zyqzyq.eyepetizer.mvp.base.BaseView
import com.zyqzyq.eyepetizer.mvp.model.bean.HomeBean
import com.zyqzyq.eyepetizer.mvp.model.bean.HomeItem

/**
 * MVP接口
 *
* */
interface SearchContract {
    interface View: BaseView<Presenter> {
        fun setFirstData(itemList: ArrayList<HomeItem>)
        fun setMoreData(itemList: ArrayList<HomeItem>)
        fun onError()
    }
    interface Presenter{
        /**
         * 刷新数据、第一次请求数据
         */
        fun requestFirstData(data: String)

        /**
         * 底部加载更多
         */
        fun requestMoreData()
    }
}