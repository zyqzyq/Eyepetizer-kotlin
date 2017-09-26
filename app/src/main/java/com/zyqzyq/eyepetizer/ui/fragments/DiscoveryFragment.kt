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

class DiscoveryFragment: Fragment(),DiscoveryContract.View{

    private val presenter: DiscoveryPresenter = DiscoveryPresenter(this)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_discover,container,false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

        val titleList= ArrayList<String>()
        val fragmentList= ArrayList<Fragment>()
        tabInfo.tabInfo.tabList.map {
            titleList.add(it.name)
            when (it.name){
                "热门" -> fragmentList.add(DiscoveryHotFragment(it.apiUrl))
                "分类" -> fragmentList.add(DiscoveryCategoryFragment(it.apiUrl))
                else -> fragmentList.add(DiscoveryPgcsFragment(it.apiUrl))
            }
        }
        //getSupportFragmentManager() 替换为getChildFragmentManager()解决切换后无法显示的问题
        val discoveryAdapter = DiscoveryAdapter(childFragmentManager,titleList,fragmentList)
        discoverViewPager.adapter = discoveryAdapter
        discoverViewPager.offscreenPageLimit = 0
        discoverTabLayout.setupWithViewPager(discoverViewPager)
    }
}