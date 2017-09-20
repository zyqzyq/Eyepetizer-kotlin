package com.zyqzyq.eyepetizer.ui.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zyqzyq.eyepetizer.R
import com.zyqzyq.eyepetizer.TAG
import com.zyqzyq.eyepetizer.mvp.contract.DiscoveryContract
import com.zyqzyq.eyepetizer.mvp.model.bean.DiscoveryTabInfo
import com.zyqzyq.eyepetizer.mvp.presenter.DiscoveryPresenter
import com.zyqzyq.eyepetizer.ui.adapters.DiscoveryAdapter
import kotlinx.android.synthetic.main.fragment_discover.*

class DiscoverFragment: Fragment(),DiscoveryContract.View{

    private val presenter: DiscoveryPresenter = DiscoveryPresenter(this)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_discover,container,false)
    }

    override fun onResume() {
        super.onResume()
        initView()
        initListener()
        presenter.requestTabInfoData()
        Log.d(TAG,"Discover view created")
    }

    private fun initView(){

    }
    private fun initListener(){
        /*discover_srLayout.setOnRefreshListener{
            activity.toast("刷新discover")
            presenter.requestTabInfoData()
        }*/
    }

    override fun setTabAndFragment(tabInfo: DiscoveryTabInfo) {
        Log.d(TAG,"SET TAB AND FRAGMENT")
        val titleList= ArrayList<String>()
        val fragmentList= ArrayList<Fragment>()
        tabInfo.tabInfo.tabList.map {
            titleList.add(it.name)
            fragmentList.add(DiscoverItemFragment(it))
        }
        //getSupportFragmentManager() 替换为getChildFragmentManager()解决切换后无法显示的问题
        val discoveryAdapter = DiscoveryAdapter(childFragmentManager,titleList,fragmentList)
        discoverViewPager.adapter = discoveryAdapter
        discoverViewPager.offscreenPageLimit = 0
        discoverTabLayout.setupWithViewPager(discoverViewPager)
    }
}