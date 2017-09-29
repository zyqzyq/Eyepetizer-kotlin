package com.zyqzyq.eyepetizer.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.ScrollView
import com.bumptech.glide.Glide
import com.zyqzyq.eyepetizer.R
import com.zyqzyq.eyepetizer.mvp.contract.CategoryDetailContract
import com.zyqzyq.eyepetizer.mvp.model.bean.CategoryDetailTab
import com.zyqzyq.eyepetizer.mvp.presenter.CategoryDetailPresenter
import com.zyqzyq.eyepetizer.ui.adapters.DiscoveryAdapter
import com.zyqzyq.eyepetizer.ui.fragments.*
import kotlinx.android.synthetic.main.activity_category_detail.*
import kotlinx.android.synthetic.main.toolbar.*
import android.opengl.ETC1.getHeight



class CategoryDetailActivity : AppCompatActivity() ,CategoryDetailContract.View{

    private val categoryDetailPresenter: CategoryDetailPresenter = CategoryDetailPresenter(this)
//    private val categoryDetailAdapter: CategoryDetailAdapter by lazy { CategoryDetailAdapter(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_detail)
        initView()
        initListener()
        val id = intent.getStringExtra("id").toLong()
        categoryDetailPresenter.requestCategoryDetailData(id)
    }
    fun initView(){
        val name = intent.getStringExtra("name")
        toolbarTitle.text = name
        toolbar.setNavigationIcon(R.mipmap.ic_action_back)

//        categoryDetailRV.layoutManager = LinearLayoutManager(this)
//        categoryDetailRV.adapter = categoryDetailAdapter
    }
    private fun initListener(){
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    override fun setCategoryDetailTab(categoryDetailTab: CategoryDetailTab) {

        Glide.with(this).load(categoryDetailTab.categoryInfo.headerImage).centerCrop().into(categoryDetailHeaderImage)
        categoryDetailTitle.text = categoryDetailTab.categoryInfo.name
        categoryDetailDescription.text = categoryDetailTab.categoryInfo.description

        val titleList= ArrayList<String>()
        val fragmentList= ArrayList<Fragment>()
        categoryDetailTab.tabInfo.tabList.map{
            titleList.add(it.name)
            Log.d("HOME",it.name)
            when (it.name){
                "首页" -> fragmentList.add(CategoryDetailIndexFragment(it.apiUrl))
                "全部" -> fragmentList.add(DiscoveryHotFragment(it.apiUrl))
                "作者" -> fragmentList.add(DiscoveryPgcsFragment(it.apiUrl))
                "专辑" -> fragmentList.add(DiscoveryPgcsFragment(it.apiUrl))
                else -> fragmentList.add(DiscoveryPgcsFragment(it.apiUrl))
            }
        }
        //getSupportFragmentManager() 替换为getChildFragmentManager()解决切换后无法显示的问题
        val discoveryAdapter = DiscoveryAdapter(supportFragmentManager,titleList,fragmentList)
        categoryDetailViewPager.adapter = discoveryAdapter
        categoryDetailTabLayout.setupWithViewPager(categoryDetailViewPager)
    }
}
