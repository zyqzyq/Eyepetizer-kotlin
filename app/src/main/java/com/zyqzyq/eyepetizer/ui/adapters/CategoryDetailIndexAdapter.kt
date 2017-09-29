package com.zyqzyq.eyepetizer.ui.adapters

import android.content.Intent
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.zyqzyq.eyepetizer.mvp.model.bean.HomeItem
import com.zyqzyq.eyepetizer.ui.activities.PlayActivity
import com.zyqzyq.eyepetizer.ui.view.discover.category.CategoryScrollCardItem
import com.zyqzyq.eyepetizer.ui.view.discover.hot.ScrollCardItem
import com.zyqzyq.eyepetizer.ui.view.discover.hot.SquareCardItem
import com.zyqzyq.eyepetizer.ui.view.discover.pgcs.BlankCardItem
import com.zyqzyq.eyepetizer.ui.view.discover.pgcs.BriefVideo
import com.zyqzyq.eyepetizer.ui.view.home.HomeStandardItem
import com.zyqzyq.eyepetizer.ui.view.home.HomeTextFooterItem
import com.zyqzyq.eyepetizer.ui.view.home.HomeTextHeaderItem
import com.zyqzyq.eyepetizer.ui.view.play.PlayEndItem
import kotlinx.android.synthetic.main.item_standard_text.view.*

class CategoryDetailIndexAdapter : RecyclerView.Adapter<CategoryDetailIndexAdapter.ViewHolder>(){
    private val TYPE_CATEGORY_CARD = 1
    private val TYPE_STANDARD = 2
    private val TYPE_TEXT_HEADER = 3
    private val TYPE_TEXT_FOOTER = 4
    private val TYPE_BRIEF_VIDEO = 5
    private val TYPE_END_CARD = 6
    val data: ArrayList<HomeItem> by lazy { ArrayList<HomeItem>() }
    override fun getItemCount(): Int {
        return data.size+1
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

            TYPE_CATEGORY_CARD -> (holder?.itemView as CategoryScrollCardItem).setData(data[position])
            TYPE_STANDARD -> (holder?.itemView as HomeStandardItem).let {
                it.setData(data[position])
                it.setOnClickListener {
                    val intent = Intent(it.context, PlayActivity::class.java)
                    intent.putExtra("data", data[position])
                    it.context.startActivity(intent)  }
            }
            TYPE_TEXT_HEADER -> (holder?.itemView as HomeTextHeaderItem).setHeaderText(data[position].data?.text)
            TYPE_TEXT_FOOTER -> (holder?.itemView as HomeTextFooterItem).setFooterText(data[position].data?.text)
            TYPE_BRIEF_VIDEO -> (holder?.itemView as BriefVideo).setData(data[position])
            TYPE_END_CARD -> (holder?.itemView as PlayEndItem).let {
                it.standardText.setTextColor(Color.BLACK)
                it.setShow(true) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        when(viewType) {
            TYPE_CATEGORY_CARD -> {
                return ViewHolder(CategoryScrollCardItem(parent!!.context))
            }
            TYPE_STANDARD -> {
                val textView = HomeStandardItem(parent!!.context)
                return ViewHolder(textView)
            }
            TYPE_TEXT_FOOTER -> {
                val headerText = HomeTextFooterItem(parent!!.context)
                headerText.layoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                        RecyclerView.LayoutParams.WRAP_CONTENT)
                return ViewHolder(headerText)
            }
            TYPE_TEXT_HEADER -> {
                val headerText = HomeTextHeaderItem(parent!!.context)
                headerText.layoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                        RecyclerView.LayoutParams.WRAP_CONTENT)
                return ViewHolder(headerText)
            }
            TYPE_BRIEF_VIDEO -> {
                return ViewHolder(BriefVideo(parent!!.context))
            }
            TYPE_END_CARD -> {
                val itemView = PlayEndItem(parent?.context)
                itemView.layoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT)
                return ViewHolder(itemView)
            }
            else -> return ViewHolder(BlankCardItem(parent!!.context))
        }

    }

    override fun getItemViewType(position: Int): Int {
        if (position==itemCount -1)  return TYPE_END_CARD
        when(data[position].type){
            "video" -> return TYPE_STANDARD
            "videoCollectionOfHorizontalScrollCard" -> return TYPE_CATEGORY_CARD
            "textHeader" -> return TYPE_TEXT_HEADER
            "textFooter" -> return TYPE_TEXT_FOOTER
            "videoCollectionWithBrief" -> return TYPE_BRIEF_VIDEO
            else -> return 0
        }
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)

}