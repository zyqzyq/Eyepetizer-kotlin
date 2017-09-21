package com.zyqzyq.eyepetizer.ui.adapters

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.zyqzyq.eyepetizer.mvp.model.bean.HomeItem
import com.zyqzyq.eyepetizer.ui.activities.PlayActivity
import com.zyqzyq.eyepetizer.ui.view.home.HomeStandardItem
import com.zyqzyq.eyepetizer.ui.view.home.HomeTextHeaderItem
import com.zyqzyq.eyepetizer.ui.view.hot.ScrollCardItem
import com.zyqzyq.eyepetizer.ui.view.hot.SquareCardItem

class DiscoveryHotAdapter : RecyclerView.Adapter<DiscoveryHotAdapter.ViewHolder>(){
    private val TYPE_SCROLL_CARD = 1
    private val TYPE_TEXT_HEADER = 2
    private val TYPE_STANDARD = 3
    private val TYPE_SQUARE_CARD = 4
    val data: ArrayList<HomeItem> by lazy { ArrayList<HomeItem>() }

    fun addItemList(itemList: ArrayList<HomeItem>){
        data.addAll(itemList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        when(viewType){
            TYPE_SCROLL_CARD ->{
                val headerText = ScrollCardItem(parent!!.context)
                headerText.layoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                        RecyclerView.LayoutParams.WRAP_CONTENT)
                return ViewHolder(headerText)
            }
            TYPE_STANDARD -> {
                val textView = HomeStandardItem(parent!!.context)
                return ViewHolder(textView)
            }
            TYPE_SQUARE_CARD -> {
                val squareView = SquareCardItem(parent!!.context)
                squareView.layoutParams = ViewGroup.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                        RecyclerView.LayoutParams.WRAP_CONTENT)
                return ViewHolder(squareView)
            }
            else ->
            {
                val headerText = HomeTextHeaderItem(parent!!.context)
                headerText.layoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                        RecyclerView.LayoutParams.WRAP_CONTENT)
                return ViewHolder(headerText)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val itemViewType = getItemViewType(position)
        when (itemViewType) {
            TYPE_SCROLL_CARD ->(holder?.itemView as ScrollCardItem).setData(data[position].data?.itemList)

            TYPE_STANDARD -> (holder?.itemView as HomeStandardItem).let {
                it.setData(data[position])
                it.setOnClickListener {
                    val intent = Intent(it.context, PlayActivity::class.java)
                    intent.putExtra("data", data[position])
                    it.context.startActivity(intent)  }
            }
            TYPE_SQUARE_CARD -> (holder?.itemView as SquareCardItem).setData(data[position])
            TYPE_TEXT_HEADER -> (holder?.itemView as HomeTextHeaderItem).setHeaderText(data[position].data?.text)
//
    }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (data[position].type){
            "horizontalScrollCard" -> TYPE_SCROLL_CARD
            "textHeader" -> TYPE_TEXT_HEADER
            "video" -> TYPE_STANDARD
            "squareCardCollection" -> TYPE_SQUARE_CARD
            else -> 0
        }
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}