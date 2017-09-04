package com.zyqzyq.eyepetizer.ui.adapters

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.zyqzyq.eyepetizer.mvp.Model.bean.HomeItem
import com.zyqzyq.eyepetizer.ui.view.HomeStandardItem
import com.zyqzyq.eyepetizer.ui.view.HomeTextHeaderItem
import com.zyqzyq.eyepetizer.TAG


class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {


    //首次获取数据
    var itemList: ArrayList<HomeItem> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var bannerItemListCount = 0

    fun addData(itemList: ArrayList<HomeItem>) {
        this.itemList.addAll(itemList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val itemViewType = getItemViewType(position)
        Log.d(TAG,position.toString())
        Log.d(TAG,itemList[position].toString())
        when (itemViewType) {
//            TYPE_BANNER -> (holder?.itemView as HomeBanner).setData(itemList.take(bannerItemListCount).toCollection(ArrayList()))
            TYPE_STANDARD -> (holder?.itemView as HomeStandardItem).setData(itemList[position])
            TYPE_TEXT_HEADER -> (holder?.itemView as HomeTextHeaderItem).setHeaderText(itemList[position].data?.text)
            TYPE_TEXT_FOOTER -> (holder?.itemView as HomeTextHeaderItem).setHeaderText(itemList[position].data?.text)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    private val TYPE_BANNER = 1
    private val TYPE_STANDARD = 2
    private val TYPE_TEXT_HEADER = 3
    private val TYPE_TEXT_FOOTER = 4


    override fun getItemViewType(position: Int): Int {
        if (position > bannerItemListCount-1) return TYPE_BANNER
        when(itemList[position].type ) {
            "video" -> return TYPE_STANDARD

            "textHeader"-> return TYPE_TEXT_HEADER

            "textFooter" -> return TYPE_TEXT_FOOTER

            else -> return 0
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        when (viewType) {
//            TYPE_BANNER -> return ViewHolder(HomeBanner(parent!!.context))

            TYPE_STANDARD -> {
                val textView = HomeStandardItem(parent!!.context)
                return ViewHolder(textView)
            }

            else -> {
                val headerText = HomeTextHeaderItem(parent!!.context)
                headerText.layoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                        80)
                return ViewHolder(headerText)
            }
        }
    }

    fun setBannerSize(size: Int) {
        bannerItemListCount = size
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}