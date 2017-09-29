package com.zyqzyq.eyepetizer.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.zyqzyq.eyepetizer.mvp.model.bean.HomeItem
import com.zyqzyq.eyepetizer.ui.view.discover.category.CategoryScrollCardItem
import com.zyqzyq.eyepetizer.ui.view.discover.pgcs.BlankCardItem
import com.zyqzyq.eyepetizer.ui.view.discover.pgcs.BriefVideo

class FollowAdapter: RecyclerView.Adapter<FollowAdapter.ViewHolder>(){
    val TYPE_BRIEF_VIDEO = 1
    val TYPE_CATEGORY_CARD = 2
    val data: ArrayList<HomeItem> by lazy { ArrayList<HomeItem>() }
    override fun getItemCount(): Int {
        return data.size
    }
    fun setItemList(itemList: ArrayList<HomeItem>){
        data.clear()
        data.addAll(itemList)
        notifyDataSetChanged()
    }

    fun addItemList(itemList: ArrayList<HomeItem>){
        data.addAll(itemList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val itemViewType = getItemViewType(position)
        when(itemViewType){
            TYPE_BRIEF_VIDEO -> (holder?.itemView as BriefVideo).setData(data[position])
            TYPE_CATEGORY_CARD -> (holder?.itemView as CategoryScrollCardItem).setData(data[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        when(viewType){
            TYPE_BRIEF_VIDEO -> {
                return ViewHolder(BriefVideo(parent!!.context))
            }
            TYPE_CATEGORY_CARD ->{
                return ViewHolder(CategoryScrollCardItem(parent!!.context))
            }
            else -> return ViewHolder(BlankCardItem(parent!!.context))
        }

    }

    override fun getItemViewType(position: Int): Int {
        when(data[position].type){
            "videoCollectionWithBrief" -> return TYPE_BRIEF_VIDEO
            "videoCollectionOfHorizontalScrollCard" -> return TYPE_CATEGORY_CARD
            else -> return 0
        }
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}