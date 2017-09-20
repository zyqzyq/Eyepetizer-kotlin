package com.zyqzyq.eyepetizer.ui.view.home

import android.content.Intent
import android.support.v4.view.PagerAdapter
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.zyqzyq.eyepetizer.TAG
import com.zyqzyq.eyepetizer.mvp.model.bean.HomeItem
import com.zyqzyq.eyepetizer.ui.activities.PlayActivity

class BannerAdapter: PagerAdapter(){
    var datas: ArrayList<HomeItem>? = null
    var viewList: ArrayList<HomeBannerItem> = ArrayList()

    override fun getCount(): Int {
        return datas?.size ?:0
//        return Int.MAX_VALUE
    }

    override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        container?.removeView(viewList[position])
        viewList[position].releasePlayer()
    }

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        if (viewList.size <= position+1){
          val bannerItem  =  HomeBannerItem(container?.context,datas!![position])
            viewList.add(bannerItem)
        }

        Log.d(TAG,position.toString())
        val view = viewList[position]
        container?.addView(view)
        viewList[position].play()
        view.setOnClickListener { _ ->
        val intent = Intent(view.context, PlayActivity::class.java)
            intent.putExtra("data", datas!![position])
            view.context.startActivity(intent)  }
        return view
    }
}