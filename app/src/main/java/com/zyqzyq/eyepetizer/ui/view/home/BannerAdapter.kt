package com.zyqzyq.eyepetizer.ui.view.home

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import com.zyqzyq.eyepetizer.mvp.Model.bean.HomeItem

class BannerAdapter: PagerAdapter(){
    var datas: ArrayList<HomeItem>? = null
    var viewList: ArrayList<HomeBannerItem> = ArrayList()
    override fun getCount(): Int {
        return datas?.size ?:0
    }

    override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        container?.removeView(viewList[position])
        viewList[position].releasePlayer()
    }

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        if (viewList.size <= position){
            val homeBannerItem = HomeBannerItem(container?.context,datas!![position])
            viewList.add(homeBannerItem)
        }
        val view = viewList[position]
        container?.addView(view)
        viewList[position].play()
        return view
    }
}