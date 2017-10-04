package com.zyqzyq.eyepetizer.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zyqzyq.eyepetizer.mvp.contract.DiscoveryContract
import com.zyqzyq.eyepetizer.mvp.contract.RankListContract
import com.zyqzyq.eyepetizer.mvp.model.bean.HomeItem
import com.zyqzyq.eyepetizer.mvp.presenter.DiscoveryItemPresenter
import com.zyqzyq.eyepetizer.mvp.presenter.RanklistItemPresenter
import com.zyqzyq.eyepetizer.ui.adapters.DiscoveryHotAdapter
import com.zyqzyq.eyepetizer.ui.adapters.RankListItemAdapter


class RankListFragment(private val apiUrl: String) : Fragment(), RankListContract.ItemView {


    var presenter: RanklistItemPresenter = RanklistItemPresenter(this)
    val recyclerView by lazy { RecyclerView(context)}
    val adapter by lazy { RankListItemAdapter() }

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
    }

    override fun setTabItemData(itemList: ArrayList<HomeItem>) {
        adapter.setItemList(itemList)
    }

}