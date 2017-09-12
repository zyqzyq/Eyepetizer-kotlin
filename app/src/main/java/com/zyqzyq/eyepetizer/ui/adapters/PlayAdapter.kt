package com.zyqzyq.eyepetizer.ui.adapters

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.google.android.exoplayer.util.PlayerControl
import com.zyqzyq.eyepetizer.TAG
import com.zyqzyq.eyepetizer.mvp.model.bean.HomeItem
import com.zyqzyq.eyepetizer.ui.activities.PlayActivity
import com.zyqzyq.eyepetizer.ui.view.play.PlayEndItem
import com.zyqzyq.eyepetizer.ui.view.play.PlayInfoItem
import com.zyqzyq.eyepetizer.ui.view.play.PlayTextCardItem
import com.zyqzyq.eyepetizer.ui.view.play.PlayVideoCardItem

class PlayAdapter: RecyclerView.Adapter<PlayAdapter.ViewHolder>(){
    private val TYPE_END_CARD = 0
    private val TYPE_INFO_CARD = 1
    private val TYPE_TEXT_CARD = 2
    private val TYPE_VIDEO_CARD = 3

    val data: ArrayList<HomeItem> by lazy { ArrayList<HomeItem>() }

    fun addData(item: HomeItem) {
        data.clear()
        notifyDataSetChanged()
        data.add(item)
        notifyItemInserted(0)
    }

    fun addData(item: ArrayList<HomeItem>){
        data.addAll(item)
        notifyItemRangeInserted(1, item.size)
    }
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val itemView: View
         when(viewType){
            TYPE_INFO_CARD -> itemView = PlayInfoItem(parent?.context)
            TYPE_END_CARD -> {
                itemView = PlayEndItem(parent?.context)
                itemView.layoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT)}
            TYPE_TEXT_CARD -> itemView = PlayTextCardItem(parent?.context)
            TYPE_VIDEO_CARD -> itemView = PlayVideoCardItem(parent?.context)
             else -> itemView = PlayTextCardItem(parent?.context)
        }
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val itemView = holder?.itemView
        when(getItemViewType(position)){
            TYPE_INFO_CARD -> (itemView as PlayInfoItem).setData(data[position])
            TYPE_END_CARD -> (itemView as PlayEndItem).setShow(true)
            TYPE_TEXT_CARD -> (itemView as PlayTextCardItem).setText(data[position])
            TYPE_VIDEO_CARD -> (itemView as PlayVideoCardItem).let {
                it.setData(data[position])
                it.setOnClickListener {
                    val intent = Intent(it.context, PlayActivity::class.java)
                    intent.putExtra("data", data[position])
                    it.context.startActivity(intent)  }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> TYPE_INFO_CARD
            itemCount -1 -> TYPE_END_CARD
            else -> when (data[position].type){
                "textCard" -> TYPE_TEXT_CARD
                "videoSmallCard" -> TYPE_VIDEO_CARD
                else -> throw IllegalAccessException("ERROR")
            }
        }

    }
    override fun getItemCount(): Int {
        return data.size + 1
    }
    class ViewHolder(view: View): RecyclerView.ViewHolder(view)
}