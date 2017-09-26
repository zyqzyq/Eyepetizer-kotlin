package com.zyqzyq.eyepetizer.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zyqzyq.eyepetizer.mvp.contract.DiscoveryContract
import com.zyqzyq.eyepetizer.mvp.model.bean.HomeItem
import com.zyqzyq.eyepetizer.mvp.presenter.DiscoveryItemPresenter
import com.zyqzyq.eyepetizer.ui.adapters.DiscoveryHotAdapter
import com.zyqzyq.eyepetizer.ui.adapters.DiscoveryPgcsAdapter

class DiscoveryPgcsFragment(private val apiUrl: String) : Fragment(), DiscoveryContract.ItemView {

    var presenter: DiscoveryItemPresenter = DiscoveryItemPresenter(this)
    val recyclerView by lazy { RecyclerView(context) }
    val adapter by lazy { DiscoveryPgcsAdapter() }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        recyclerView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        recyclerView.overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        return recyclerView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.requestTabItemData(apiUrl)
        initView()
    }

    fun initView(){
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    val childCount = recyclerView?.childCount
                    val itemCount = recyclerView?.layoutManager?.itemCount
                    val firstVisibleItem = (recyclerView?.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                    if (firstVisibleItem + childCount!! == itemCount){
                        onLoadMore()
                    }

                }
            }
        })
    }

    fun onLoadMore(){
        presenter.requestMoreTabItemData()
    }

    override fun setTabItemData(itemList: ArrayList<HomeItem>) {
        adapter.setItemList(itemList)
    }

    override fun addTabItemData(itemList: ArrayList<HomeItem>) {
        adapter.addItemList(itemList)
    }
}