package com.zyqzyq.eyepetizer.ui.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.zyqzyq.eyepetizer.R
import com.zyqzyq.eyepetizer.mvp.contract.CategoryContract
import com.zyqzyq.eyepetizer.mvp.model.bean.CategoryBean
import com.zyqzyq.eyepetizer.mvp.presenter.CategoryPresenter
import com.zyqzyq.eyepetizer.ui.adapters.CategoryAdapter
import kotlinx.android.synthetic.main.activity_category.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.toast

class CategoryActivity : AppCompatActivity(),CategoryContract.View {
    private var categoryList: ArrayList<CategoryBean>? = null
    private val categoryPresenter: CategoryPresenter = CategoryPresenter(this)
    private val categoryAdapter: CategoryAdapter by lazy { CategoryAdapter(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        initView()
        categoryPresenter.requestCategoryData()
    }
    fun initView(){
        toolbarTitle.text = "全部分类"
        toolbar.setNavigationIcon(R.mipmap.ic_action_close)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        categoryGridView.adapter = categoryAdapter
        categoryGridView.setOnItemClickListener { _, _, position, _ ->
            val categoryID = categoryList!![position].id.toString()
            val categoryName = categoryList!![position].name
            val intent = Intent(this,CategoryDetailActivity::class.java)
            intent.putExtra("id",categoryID)
            intent.putExtra("name",categoryName)
            startActivity(intent)
        }
    }

    override fun setCategory(categories: ArrayList<CategoryBean>) {
        categoryList = categories
        categoryAdapter.setData(categories)
    }
}
