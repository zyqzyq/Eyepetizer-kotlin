package com.zyqzyq.eyepetizer.ui.adapters



import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter


class DiscoveryAdapter(supportFragmentManager: FragmentManager,
                       private var titleList: ArrayList<String>?,
                       fragmentList: ArrayList<Fragment>?) :
        FragmentStatePagerAdapter(supportFragmentManager) {
    private var fragmentList: ArrayList<Fragment>? = fragmentList
        set(value) {
            notifyDataSetChanged()
        }

    override fun getCount(): Int = fragmentList?.size ?:0

    override fun getPageTitle(position: Int): CharSequence {
        return titleList!![position]
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList!![position]
    }


}