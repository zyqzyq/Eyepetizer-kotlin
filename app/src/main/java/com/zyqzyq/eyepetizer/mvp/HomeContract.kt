package com.zyqzyq.eyepetizer.mvp

import com.zyqzyq.eyepetizer.mvp.Model.bean.HomeBean
import com.zyqzyq.eyepetizer.mvp.Model.bean.HomeItem
import com.zyqzyq.eyepetizer.mvp.base.BaseView
/**
 * MVP接口
 *
* */
interface HomeContract{
    interface View: BaseView<Presenter> {
        fun setFirstData(homeBean: HomeBean, bannerSize: Int)
        fun setMoreData(itemList: ArrayList<HomeItem>)
        fun onError()
    }
    interface Presenter{
        /**
         * 刷新数据、第一次请求你数据
         */
        fun requestFirstData()

        /**
         * 底部加载更多
         */
        fun requestMoreData()
    }
}