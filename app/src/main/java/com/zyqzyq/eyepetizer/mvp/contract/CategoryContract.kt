package com.zyqzyq.eyepetizer.mvp.contract

import com.zyqzyq.eyepetizer.mvp.base.BaseView
import com.zyqzyq.eyepetizer.mvp.model.bean.CategoryBean

interface CategoryContract {
    interface View: BaseView<Presenter> {
        fun setCategory(categories: ArrayList<CategoryBean>)
    }
    interface Presenter{
        fun requestCategoryData()
    }
}