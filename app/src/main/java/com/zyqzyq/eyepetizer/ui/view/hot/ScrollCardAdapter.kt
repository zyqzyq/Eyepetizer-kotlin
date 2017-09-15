package com.zyqzyq.eyepetizer.ui.view.hot

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.zyqzyq.eyepetizer.R
import com.zyqzyq.eyepetizer.TAG
import com.zyqzyq.eyepetizer.mvp.model.bean.HomeItem

class ScrollCardAdapter: PagerAdapter(){
    var datas: ArrayList<HomeItem>? = null
    var viewList: ArrayList<View> = ArrayList()

    override fun getCount(): Int {
        return datas?.size ?:0
//        return Int.MAX_VALUE
    }

    override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        container?.removeView(viewList[position])

    }

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        if (viewList.size <= position+1){
            val imageView = ImageView(container?.context)
            imageView.visibility = View.VISIBLE
            if (datas!![position].data?.image != null){
                Glide.with(container?.context).load(datas!![position].data?.image).centerCrop().into(imageView)
                Log.d(TAG,"IMAGE"+datas!![position].data?.image)
//                imageView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)
    //            imageView.setImageResource(R.drawable.splash_background)
                viewList.add(imageView)
            }
            else{
                val mtextView = TextView(container?.context)
                mtextView.text = "111123321"
                viewList.add(mtextView)
            }
        }
        val view = viewList[position]
        container?.addView(view)
        return view
    }
}