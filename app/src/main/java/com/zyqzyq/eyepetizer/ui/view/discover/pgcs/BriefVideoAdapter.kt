package com.zyqzyq.eyepetizer.ui.view.discover.pgcs

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.zyqzyq.eyepetizer.mvp.model.bean.HomeItem
import com.zyqzyq.eyepetizer.ui.activities.PlayActivity
import com.zyqzyq.eyepetizer.ui.view.home.HomeStandardItem
import com.zyqzyq.eyepetizer.util.DisplayManager
import kotlinx.android.synthetic.main.item_home_standard.view.*

class BriefVideoAdapter: RecyclerView.Adapter<BriefVideoAdapter.ViewHolder>(){

    val data: ArrayList<HomeItem> by lazy { ArrayList<HomeItem>() }
    override fun getItemCount(): Int {
        return data.size
    }
    fun setItemList(itemList: ArrayList<HomeItem>){
        data.clear()
        data.addAll(itemList)
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        (holder?.itemView as HomeStandardItem).let {
            it.setData(data[position])
            it.tvCategory.visibility = View.GONE
            it.blankView.visibility =View.VISIBLE
            it.ivAvatar.visibility = View.GONE
            it.setOnClickListener {
                val intent = Intent(it.context, PlayActivity::class.java)
                intent.putExtra("data", data[position])
                it.context.startActivity(intent)  }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val videoView = HomeStandardItem(parent!!.context)
        videoView.layoutParams = RecyclerView.LayoutParams(DisplayManager.dip2px(320f)!!.toInt(), RecyclerView.LayoutParams.MATCH_PARENT)
        return ViewHolder(videoView)
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}