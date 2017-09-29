package com.zyqzyq.eyepetizer.mvp.presenter

import com.zyqzyq.eyepetizer.mvp.contract.CategoryContract
import com.zyqzyq.eyepetizer.mvp.model.CategoryModel

class CategoryPresenter(view: CategoryContract.View): CategoryContract.Presenter{
    private val categoryView: CategoryContract.View = view
    private val categoryModel: CategoryModel by lazy { CategoryModel() }
    override fun requestCategoryData() {
        categoryModel.loadData()
                .subscribe({
                    categoryView.setCategory(it)
                })
    }
}