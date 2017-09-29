package com.zyqzyq.eyepetizer.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.zyqzyq.eyepetizer.mvp.model.bean.HomeItem
import com.zyqzyq.eyepetizer.ui.view.discover.pgcs.BlankCardItem
import com.zyqzyq.eyepetizer.ui.view.discover.pgcs.BriefCardItem
import com.zyqzyq.eyepetizer.ui.view.discover.pgcs.BriefVideo
import com.zyqzyq.eyepetizer.ui.view.discover.pgcs.LeftTextHeaderItem

class DiscoveryPgcsAdapter: RecyclerView.Adapter<DiscoveryPgcsAdapter.ViewHolder>(){

    val data: ArrayList<HomeItem> by lazy { ArrayList<HomeItem>() }
    val TYPE_LEFT_TEXT_HEADER = 1
    val TYPE_BRIEF_CARD = 2
    val TYPE_BRIEF_VIDEO = 3
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
        when(viewType){
            TYPE_LEFT_TEXT_HEADER -> return ViewHolder(LeftTextHeaderItem(parent!!.context))
            TYPE_BRIEF_CARD -> return ViewHolder(BriefCardItem(parent!!.context))
            TYPE_BRIEF_VIDEO -> return ViewHolder(BriefVideo(parent!!.context))
            else ->
            {
                val headerText = BlankCardItem(parent!!.context)
                headerText.layoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                        36)
                return ViewHolder(headerText)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val viewType = getItemViewType(position)
        when(viewType){
            TYPE_LEFT_TEXT_HEADER -> (holder?.itemView as LeftTextHeaderItem).setHeaderText(data[position].data?.text)
            TYPE_BRIEF_CARD -> (holder?.itemView as BriefCardItem).setData(data[position])
            TYPE_BRIEF_VIDEO -> (holder?.itemView as BriefVideo).setData(data[position])
        }
    }

    override fun getItemCount(): Int {
       return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (data[position].type){
            "leftAlignTextHeader" -> TYPE_LEFT_TEXT_HEADER
            "briefCard" -> TYPE_BRIEF_CARD
            "videoCollectionWithBrief" -> TYPE_BRIEF_VIDEO
            else -> 0
        }
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}