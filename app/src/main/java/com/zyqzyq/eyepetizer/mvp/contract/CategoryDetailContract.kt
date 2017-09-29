package com.zyqzyq.eyepetizer.mvp.contract

import com.zyqzyq.eyepetizer.mvp.base.BaseView
import com.zyqzyq.eyepetizer.mvp.model.bean.CategoryDetailTab


interface CategoryDetailContract {
    interface View: BaseView<Presenter> {
        fun setCategoryDetailTab(categoryDetailTab: CategoryDetailTab)
    }
    interface Presenter{
        fun requestCategoryDetailData(id: Long)
        fun requestCategoryDetailTabData()
    }
}