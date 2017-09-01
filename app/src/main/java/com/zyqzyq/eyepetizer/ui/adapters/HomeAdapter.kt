package com.zyqzyq.eyepetizer.ui.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.zyqzyq.eyepetizer.mvp.Model.bean.HomeItem
import com.zyqzyq.eyepetizer.ui.fragments.HomeFragment
import com.zyqzyq.eyepetizer.ui.view.HomeBanner
import com.zyqzyq.eyepetizer.ui.view.HomeStandardItem
import com.zyqzyq.eyepetizer.ui.view.HomeTextHeaderItem

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {


    var isNewBanner = false

    //只会在banner数据请求到的时候set，其他都是add，所以通过set可以获取到banner的count
    var itemList: ArrayList<HomeItem> = ArrayList()
        set(value) {
            Log.d(HomeFragment.TAG,"SET")
            field = value
            isNewBanner = true
            notifyDataSetChanged()
        }

    //banner用了的item的数量（包括type为banner2的）
    var bannerItemListCount = 0

    fun addData(itemList: ArrayList<HomeItem>) {
        this.itemList.addAll(itemList)
        Log.d(HomeFragment.TAG,"add")
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        Log.d(HomeFragment.TAG,"BIND")
        val itemViewType = getItemViewType(position)
        when (itemViewType) {
            /*TYPE_BANNER -> {
                if (isNewBanner) {
                    isNewBanner = false
                    (holder?.itemView as HomeBanner).setData(itemList.take(bannerItemListCount).toCollection(ArrayList()))
                }
            }*/
            TYPE_STANDARD -> (holder?.itemView as HomeStandardItem).let {
//                it.setOnClickListener { v -> v.context.toActivityWithSerializable<DetailActivity>(itemList[position + bannerItemListCount - 1]) }
                it.setData(itemList[position + bannerItemListCount - 1])
                Log.d("TAG",itemList[position + bannerItemListCount - 1].toString())
            }

            TYPE_HEADER_TEXT -> (holder?.itemView as HomeTextHeaderItem).setHeaderText(itemList[position + bannerItemListCount - 1].data?.text)

        }

    }

    override fun getItemCount(): Int {
        if (itemList.size > bannerItemListCount) {
            return itemList.size - bannerItemListCount + 1
        } else if (itemList.size == 0) {
            return 0
        } else {
            return 1
        }
    }


    private val TYPE_BANNER = 1
    private val TYPE_STANDARD = 2
    private val TYPE_HEADER_TEXT = 3

    override fun getItemViewType(position: Int): Int {

        when(itemList[position].type ) {
            "textHeader"-> return TYPE_HEADER_TEXT

            "video" -> return TYPE_STANDARD

            else -> return 0
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        Log.d(HomeFragment.TAG,"cREATE")
        when (viewType) {
//            TYPE_BANNER -> return ViewHolder(HomeBanner(parent!!.context))

            TYPE_STANDARD -> {
                val textView = HomeStandardItem(parent!!.context)
                return ViewHolder(textView)
            }
            TYPE_HEADER_TEXT -> {
                val headerText = HomeTextHeaderItem(parent!!.context)
                headerText.layoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                        80)
                return ViewHolder(headerText)
            }
            else -> return ViewHolder(null)
        }
    }


    fun setBannerSize(size: Int) {
        bannerItemListCount = size
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}