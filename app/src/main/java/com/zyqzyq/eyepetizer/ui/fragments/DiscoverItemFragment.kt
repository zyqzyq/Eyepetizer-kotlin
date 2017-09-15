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
import com.zyqzyq.eyepetizer.mvp.model.bean.Tab
import com.zyqzyq.eyepetizer.mvp.presenter.DiscoveryItemPresenter
import com.zyqzyq.eyepetizer.ui.adapters.HotAdapter
import com.zyqzyq.eyepetizer.ui.view.home.PullRecyclerView
import org.jetbrains.anko.toast


class DiscoverItemFragment(private val tab: Tab) : Fragment(), DiscoveryContract.ItemView {

    var presenter: DiscoveryItemPresenter = DiscoveryItemPresenter(this)
    val recyclerView by lazy { PullRecyclerView(context)}
    val adapter by lazy {
        when (tab.name){
            "热门" -> HotAdapter()
            else -> HotAdapter()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        recyclerView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        recyclerView.overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        return recyclerView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        presenter.requestTabItemData(tab.apiUrl)
    }

    override fun setTabItemData(itemList: ArrayList<HomeItem>) {
        adapter.addItemList(itemList)
    }

    override fun onResume() {
        super.onResume()
        recyclerView.setOnRefreshListener(object : PullRecyclerView.OnRefreshListener{
            override fun onRefresh() {
                presenter.requestTabItemData(tab.apiUrl)
                activity.toast("刷新")
            }
        })
    }
}