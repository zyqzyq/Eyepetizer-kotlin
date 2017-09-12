package com.zyqzyq.eyepetizer.ui.adapters

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.zyqzyq.eyepetizer.mvp.model.bean.HomeItem
import com.zyqzyq.eyepetizer.ui.view.home.HomeStandardItem
import com.zyqzyq.eyepetizer.ui.view.home.HomeTextHeaderItem
import com.zyqzyq.eyepetizer.TAG
import com.zyqzyq.eyepetizer.ui.activities.PlayActivity
import com.zyqzyq.eyepetizer.ui.view.home.HomeTextFooterItem
import com.zyqzyq.eyepetizer.ui.view.home.HomeBanner

/**
 * 首页 adapter
 * 分4种类型分别显示
* */
class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private var isNewBanner = false
    //首次获取数据
    var itemList: ArrayList<HomeItem> = ArrayList()
        set(value) {
            field = value
            isNewBanner =true
            notifyDataSetChanged()
        }

    private var bannerItemListCount = 0

    fun addData(itemList: ArrayList<HomeItem>) {
        this.itemList.addAll(itemList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val itemViewType = getItemViewType(position)
        when (itemViewType) {
            TYPE_BANNER -> {
                if (isNewBanner) {
                    isNewBanner = false
                    (holder?.itemView as HomeBanner).setData(itemList.take(bannerItemListCount).toCollection(ArrayList()))
                }
            }
            TYPE_STANDARD -> (holder?.itemView as HomeStandardItem).let {
                it.setData(itemList[position+ bannerItemListCount -1])
                it.setOnClickListener {
                    val intent = Intent(it.context, PlayActivity::class.java)
                    intent.putExtra("data", itemList[position+ bannerItemListCount -1])
                    it.context.startActivity(intent)  }
            }
            TYPE_TEXT_HEADER -> (holder?.itemView as HomeTextHeaderItem).setHeaderText(itemList[position+ bannerItemListCount -1].data?.text)
//            TYPE_TEXT_FOOTER -> (holder?.itemView as HomeTextFooterItem).setFooterText(itemList[position+ bannerItemListCount -1].data?.text)
        }
    }

    override fun getItemCount(): Int {
        return when {
            itemList.size > bannerItemListCount -> itemList.size - bannerItemListCount + 1
            itemList.size == 0 -> 0
            else -> 1
        }
    }

    private val TYPE_BANNER = 1
    private val TYPE_STANDARD = 2
    private val TYPE_TEXT_HEADER = 3
    private val TYPE_TEXT_FOOTER = 4


    override fun getItemViewType(position: Int): Int {
        if (itemList[position].type!="video"){
            Log.d(TAG,"type  "+itemList[position].type+ " "+position)
        }

        if (position == 0 ) return TYPE_BANNER
        return when(itemList[position + bannerItemListCount -1].type ) {
            "video" -> TYPE_STANDARD

            "textHeader"-> TYPE_TEXT_HEADER

            "textFooter" -> TYPE_TEXT_FOOTER

            else -> 0
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        when (viewType) {
            TYPE_BANNER -> return ViewHolder(HomeBanner(parent!!.context))

            TYPE_STANDARD -> {
                val textView = HomeStandardItem(parent!!.context)
                return ViewHolder(textView)
            }
            TYPE_TEXT_FOOTER ->{
                val headerText = HomeTextFooterItem(parent!!.context)
                headerText.layoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                        RecyclerView.LayoutParams.WRAP_CONTENT)
                return ViewHolder(headerText)
            }
            else -> {
                val headerText = HomeTextHeaderItem(parent!!.context)
                headerText.layoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                        RecyclerView.LayoutParams.WRAP_CONTENT)
                return ViewHolder(headerText)
            }

        }
    }

    fun setBannerSize(size: Int) {
        bannerItemListCount = size
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}