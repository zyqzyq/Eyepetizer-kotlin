package com.zyqzyq.eyepetizer.ui.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zyqzyq.eyepetizer.R
import com.zyqzyq.eyepetizer.mvp.contract.FollowContract
import com.zyqzyq.eyepetizer.mvp.model.bean.HomeItem
import com.zyqzyq.eyepetizer.mvp.presenter.FollowPresenter
import com.zyqzyq.eyepetizer.ui.adapters.FollowAdapter
import kotlinx.android.synthetic.main.fragment_follow.*
import org.jetbrains.anko.toast

class FollowFragment: Fragment(),FollowContract.View{

    private lateinit var presenter: FollowPresenter
    private val followAdapter: FollowAdapter by lazy { FollowAdapter() }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_follow,container,false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        initView()
        presenter = FollowPresenter(this)
        presenter.requestFirstData()
    }
    fun initView(){
        followRV.layoutManager = LinearLayoutManager(context)
        followRV.adapter = followAdapter
        followSwipeRefreshLayout.setOnRefreshListener {
            presenter.requestFirstData()
        }
        followRV.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    val childCount = followRV.childCount
                    val itemCount = followRV.layoutManager.itemCount
                    val firstVisibleItem = (followRV.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                    if (firstVisibleItem + childCount == itemCount){
//                        下拉到底加载更多，暂未设置加载图标
                        onLoadMore()
                    }

                }
            }

        })
    }
    fun onLoadMore(){
        presenter.requestMoreData()
    }

    override fun setFirstData(itemList: ArrayList<HomeItem>) {
        followAdapter.setItemList(itemList)
        followSwipeRefreshLayout.isRefreshing = false
    }

    override fun setMoreData(itemList: ArrayList<HomeItem>) {
        followAdapter.addItemList(itemList)
    }
    override fun onError() {
        activity.toast("网络错误")
    }
}