package com.zyqzyq.eyepetizer.ui.adapters



import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter


class DiscoveryAdapter(supportFragmentManager: FragmentManager,
                       private var titleList: ArrayList<String>?,
                       private var fragmentList: ArrayList<Fragment>?)
    : FragmentPagerAdapter(supportFragmentManager) {


    override fun getCount(): Int = fragmentList?.size ?:0

    override fun getPageTitle(position: Int): CharSequence {
        return titleList!![position]
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList!![position]
    }


}