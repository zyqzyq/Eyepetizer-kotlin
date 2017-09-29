package com.zyqzyq.eyepetizer.mvp.presenter

import com.zyqzyq.eyepetizer.mvp.contract.CategoryDetailContract
import com.zyqzyq.eyepetizer.mvp.model.CategoryDetailModel


class CategoryDetailPresenter(view: CategoryDetailContract.View): CategoryDetailContract.Presenter {
    private val categoryDetailView: CategoryDetailContract.View = view
    private val categoryDetailModel: CategoryDetailModel by lazy { CategoryDetailModel() }
    override fun requestCategoryDetailData(id: Long) {
        categoryDetailModel.loadData(id)
                .subscribe({
                 categoryDetailView.setCategoryDetailTab(it)
                })
    }

    override fun requestCategoryDetailTabData() {

    }
}