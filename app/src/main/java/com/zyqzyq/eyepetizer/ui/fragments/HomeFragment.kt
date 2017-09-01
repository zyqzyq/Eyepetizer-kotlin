package com.zyqzyq.eyepetizer.ui.fragments

import android.app.Fragment
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zyqzyq.eyepetizer.R
import com.zyqzyq.eyepetizer.mvp.HomeContract
import com.zyqzyq.eyepetizer.mvp.HomePresenter
import com.zyqzyq.eyepetizer.ui.adapters.HomeAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.toast
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.zyqzyq.eyepetizer.mvp.Model.bean.HomeBean
import com.zyqzyq.eyepetizer.mvp.Model.bean.HomeItem


class HomeFragment: Fragment(), HomeContract.View{

    private val homeAdapter: HomeAdapter by lazy { HomeAdapter() }
    lateinit var presenter:HomePresenter
    override fun setPresenter(presenter: HomeContract.Presenter) {
        this.presenter = presenter as HomePresenter
    }
    init {
        setPresenter(HomePresenter(this))
    }
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater!!.inflate(R.layout.fragment_home,container,false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        presenter.requestFirstData()
    }
    private fun initView(){
        homeRecyclerView.adapter = homeAdapter
        homeRecyclerView.layoutManager = LinearLayoutManager(activity)

        homeSwipeLayout.setOnRefreshListener(object: SwipeRefreshLayout.OnRefreshListener{
            override fun onRefresh() {
                toast("刷新")
//                homeSwipeLayout.isRefreshing = false
                presenter.requestFirstData()
            }
        })

        homeRecyclerView.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    val childCount = homeRecyclerView.childCount
                    val itemCount = homeRecyclerView.layoutManager.itemCount
                    val firstVisiableItem = (homeRecyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                    if (firstVisiableItem + childCount == itemCount){
                        Log.d(TAG,"到底了")
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

    override fun setFirstData(homeBean: HomeBean) {

        homeAdapter.setBannerSize(homeBean.count)
        homeAdapter.itemList = homeBean.itemList
        Log.d(TAG,"SETFIRST")
        homeSwipeLayout.isRefreshing = false
    }

    override fun setMoreData(itemList: ArrayList<HomeItem>) {
        homeAdapter.addData(itemList)
    }

    override fun onError() {
        toast("网络错误")
    }

    companion object {
        val TAG = "HomeFragment"
    }
}