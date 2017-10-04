package com.zyqzyq.eyepetizer.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.zyqzyq.eyepetizer.R
import com.zyqzyq.eyepetizer.mvp.contract.RankListContract
import com.zyqzyq.eyepetizer.mvp.model.bean.DiscoveryTabInfo
import com.zyqzyq.eyepetizer.mvp.presenter.RankListPresenter
import com.zyqzyq.eyepetizer.ui.adapters.DiscoveryAdapter
import com.zyqzyq.eyepetizer.ui.fragments.RankListFragment
import kotlinx.android.synthetic.main.activity_ranklist.*
import kotlinx.android.synthetic.main.toolbar.*

class RanklistActivity : AppCompatActivity() ,RankListContract.View{

    val prestener: RankListPresenter = RankListPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranklist)
        toolbarTitle.text = "排行榜"
        toolbar.setNavigationIcon(R.mipmap.ic_action_back)
        toolbar.setNavigationOnClickListener { onBackPressed() }

        prestener.requestTabInfoData()
    }

    override fun setTabAndFragment(tabInfo: DiscoveryTabInfo) {
        val titleList= ArrayList<String>()
        val fragmentList= ArrayList<Fragment>()
        tabInfo.tabInfo.tabList.map {
            titleList.add(it.name)
            fragmentList.add(RankListFragment(it.apiUrl))
        }
        //getSupportFragmentManager() 替换为getChildFragmentManager()解决切换后无法显示的问题
        val discoveryAdapter = DiscoveryAdapter(supportFragmentManager,titleList,fragmentList)
        rankListViewPager.adapter = discoveryAdapter
        rankListTabLayout.setupWithViewPager(rankListViewPager)
    }
}
