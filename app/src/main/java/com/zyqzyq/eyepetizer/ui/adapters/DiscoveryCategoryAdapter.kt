package com.zyqzyq.eyepetizer.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.zyqzyq.eyepetizer.mvp.model.bean.HomeItem
import com.zyqzyq.eyepetizer.ui.view.discover.category.CategoryScrollCardItem


class DiscoveryCategoryAdapter : RecyclerView.Adapter<DiscoveryCategoryAdapter.ViewHolder>(){

    val data: ArrayList<HomeItem> by lazy { ArrayList<HomeItem>() }

    fun setItemList(itemList: ArrayList<HomeItem>){
        data.clear()
        data.addAll(itemList)
        notifyDataSetChanged()
    }

    fun addItemList(itemList: ArrayList<HomeItem>){
        data.addAll(itemList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {

        return ViewHolder(CategoryScrollCardItem(parent!!.context))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        (holder?.itemView as CategoryScrollCardItem).setData(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }


    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}