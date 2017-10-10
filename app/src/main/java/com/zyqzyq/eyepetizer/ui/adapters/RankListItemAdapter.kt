package com.zyqzyq.eyepetizer.ui.adapters

import android.content.Intent
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.zyqzyq.eyepetizer.mvp.model.bean.HomeItem
import com.zyqzyq.eyepetizer.ui.activities.PlayActivity
import com.zyqzyq.eyepetizer.ui.view.RankListItem
import com.zyqzyq.eyepetizer.ui.view.play.PlayEndItem
import kotlinx.android.synthetic.main.item_standard_text.view.*


class RankListItemAdapter : RecyclerView.Adapter<RankListItemAdapter.ViewHolder>(){
    val TYPE_END = 0
    val TYPE_VIDEO = 1
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
        when(viewType){
            TYPE_END -> return ViewHolder(PlayEndItem(parent!!.context))
            TYPE_VIDEO ->  return ViewHolder(RankListItem(parent!!.context))
            else ->  return ViewHolder(RankListItem(parent!!.context))
        }

    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val viewType = getItemViewType(position)
        when(viewType){
            TYPE_VIDEO -> (holder?.itemView as RankListItem).let{
                it.setData(data[position])
                it.setOnClickListener {
                    val intent = Intent(it.context, PlayActivity::class.java)
                    intent.putExtra("data", data[position])
                    it.context.startActivity(intent)
                }
            }
                TYPE_END -> (holder?.itemView as PlayEndItem).let {
                it.standardText.setTextColor(Color.BLACK)
                it.setShow(true)
            }
            }
        }


    override fun getItemCount(): Int {
        return data.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        if (position == data.size) return TYPE_END
        else return TYPE_VIDEO
    }
    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}