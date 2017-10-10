package com.zyqzyq.eyepetizer.ui.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zyqzyq.eyepetizer.mvp.model.bean.HomeItem
import com.zyqzyq.eyepetizer.ui.activities.PlayActivity
import com.zyqzyq.eyepetizer.ui.view.RankListItem

/**
 * Created by pc on 2017/10/9.
 */
class SearchAdapter: RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
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
        return ViewHolder(RankListItem(parent!!.context))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        (holder?.itemView as RankListItem).let{
            it.setData(data[position])
            it.setOnClickListener {
                val intent = Intent(it.context, PlayActivity::class.java)
                intent.putExtra("data", data[position])
                it.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}