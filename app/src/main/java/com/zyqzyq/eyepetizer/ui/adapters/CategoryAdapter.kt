package com.zyqzyq.eyepetizer.ui.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import com.zyqzyq.eyepetizer.R
import com.zyqzyq.eyepetizer.mvp.model.bean.CategoryBean
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryAdapter(context: Context): BaseAdapter(){

    val myContext = context

    var mList : ArrayList<CategoryBean>? = null

    fun setData(data: ArrayList<CategoryBean>){
        mList = data
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View
        if (convertView == null) {
            view = LayoutInflater.from(myContext).inflate(R.layout.item_category,parent,false)
        }else{
            view = convertView
        }
        val bgPicture = mList?.get(position)!!.bgPicture
        Glide.with(myContext).load(bgPicture).centerCrop().into(view.categoryItemImg)
        view.categoryItemTitle.text = mList?.get(position)!!.name
        return  view
    }

    override fun getItem(position: Int): CategoryBean? {
        return mList?.get(position)
    }

    override fun getItemId(position: Int): Long {
        return  position.toLong()
    }

    override fun getCount(): Int {
        return mList?.size ?:0
       /* if(mList!=null){
            return mList!!.size
        }else{
            return 0
        }*/
    }
}
