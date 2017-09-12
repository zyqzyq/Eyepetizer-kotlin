package com.zyqzyq.eyepetizer.ui.fragments

import android.app.Fragment
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zyqzyq.eyepetizer.R
import com.zyqzyq.eyepetizer.mvp.contract.HomeContract
import com.zyqzyq.eyepetizer.mvp.presenter.HomePresenter
import com.zyqzyq.eyepetizer.ui.adapters.HomeAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.toast
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.zyqzyq.eyepetizer.mvp.model.bean.HomeBean
import com.zyqzyq.eyepetizer.mvp.model.bean.HomeItem
import com.zyqzyq.eyepetizer.TAG
import com.zyqzyq.eyepetizer.ui.view.home.PullRecyclerView
import kotlinx.android.synthetic.main.toolbar.*

/**
 * 首页（使用recyclerView 显示个个项目）
 * 1.显示banner（viewpager + indicator）
 * 2.显示textHeader(打算后期在写个往期编辑精选的页面，到时候再改)
 * 3.显示textFooter
 * 4.显示每个视频的图片介绍信息
* */
class HomeFragment: Fragment(), HomeContract.View{

    private val homeAdapter: HomeAdapter by lazy { HomeAdapter() }
    private lateinit var presenter: HomePresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater!!.inflate(R.layout.fragment_home,container,false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initView()
        presenter = HomePresenter(this)
        presenter.requestFirstData()
    }
    private fun initToolbar(){
        activity.toolbar.setBackgroundColor(Color.WHITE)
        activity.toolbar.background.alpha = 0
        activity.toolbarTitle.text = ""

    }
    private fun initView(){

        homeRecyclerView.adapter = homeAdapter
        homeRecyclerView.layoutManager = LinearLayoutManager(activity)
        homeRecyclerView.setOnRefreshListener(object : PullRecyclerView.OnRefreshListener{
            override fun onRefresh() {
                Log.d(TAG,"刷新")
                toast("刷新")
                presenter.requestFirstData()
            }
        })


        homeRecyclerView.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    val childCount = homeRecyclerView.childCount
                    val itemCount = homeRecyclerView.layoutManager.itemCount
                    val firstVisibleItem = (homeRecyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                    if (firstVisibleItem + childCount == itemCount){
                        Log.d(TAG,"到底了")
//                        下拉到底加载更多，暂未设置加载图标
                        onLoadMore()
                    }

                }
            }

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

            }
        })
    }

    fun onLoadMore(){
        presenter.requestMoreData()
    }

    override fun setFirstData(homeBean: HomeBean,bannerSize: Int) {
        Log.d(TAG,bannerSize.toString())
        homeAdapter.setBannerSize(bannerSize)
        homeAdapter.itemList = homeBean.itemList
        homeRecyclerView.hideLoading()
    }

    override fun setMoreData(itemList: ArrayList<HomeItem>) {
        homeAdapter.addData(itemList)
    }

    override fun onError() {
        toast("网络错误")
        homeRecyclerView.hideLoading()
    }


}