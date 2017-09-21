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
    var itemCount: Int = 1
    var datas: ArrayList<HomeItem>? = null
    var viewList: ArrayList<HomeBannerItem> = ArrayList()

    override fun getCount(): Int {
//        return datas?.size ?:0
        if (datas == null )
            return 0
        else return Int.MAX_VALUE
    }

    override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        container?.removeView(viewList[position%itemCount])
        viewList[position%itemCount].releasePlayer()
    }

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        if (viewList.size <= itemCount) {
            for (i in 0 until itemCount){
                val homeBannerItem = HomeBannerItem(container?.context, datas!![i])
                viewList.add(homeBannerItem)
            }
        }

        val view = viewList[position%itemCount]
        container?.addView(view)
        viewList[position%itemCount].play()
        view.setOnClickListener { _ ->
        val intent = Intent(view.context, PlayActivity::class.java)
            intent.putExtra("data", datas!![position%itemCount])
            view.context.startActivity(intent)  }
        return view
    }
}