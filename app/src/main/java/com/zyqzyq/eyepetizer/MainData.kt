package com.zyqzyq.eyepetizer

import com.zyqzyq.eyepetizer.ui.fragments.DiscoveryFragment
import com.zyqzyq.eyepetizer.ui.fragments.FollowFragment
import com.zyqzyq.eyepetizer.ui.fragments.HomeFragment
import com.zyqzyq.eyepetizer.ui.fragments.MineFragment

object MainData {
    val mainFragmentList = arrayOf(HomeFragment(), DiscoveryFragment(), FollowFragment(), MineFragment())
    val mainTabRes = listOf(R.mipmap.ic_tab_home,R.mipmap.ic_tab_find,
            R.mipmap.ic_tab_follow,R.mipmap.ic_tab_mine)
    val mainTabResPressed = listOf(R.mipmap.ic_tab_home_selected,R.mipmap.ic_tab_find_selected,
            R.mipmap.ic_tab_follow_selected,R.mipmap.ic_tab_mine_selected)
    val mainTabStr = listOf("首页","发现","关注","我的")
}

val DAILY = "http://baobab.kaiyanapp.com/api/v4/tabs/selected"