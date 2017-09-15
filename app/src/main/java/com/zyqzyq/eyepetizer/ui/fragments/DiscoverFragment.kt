package com.zyqzyq.eyepetizer.ui.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zyqzyq.eyepetizer.R
import com.zyqzyq.eyepetizer.mvp.contract.DiscoveryContract
import com.zyqzyq.eyepetizer.mvp.model.bean.DiscoveryTabInfo
import com.zyqzyq.eyepetizer.mvp.presenter.DiscoveryPresenter
import com.zyqzyq.eyepetizer.ui.adapters.DiscoveryAdapter
import kotlinx.android.synthetic.main.fragment_discover.*
import org.jetbrains.anko.toast

class DiscoverFragment: Fragment(),DiscoveryContract.View{

    private val presenter: DiscoveryPresenter = DiscoveryPresenter(this)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_discover,container,false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        presenter.requestTabInfoData()
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
            fragmentList.add(DiscoverItemFragment(it))
        }

        val discoveryAdapter = DiscoveryAdapter(fragmentManager,titleList,fragmentList)
        discoverViewPager.adapter = discoveryAdapter
        discover_tab_layout.setupWithViewPager(discoverViewPager)
    }
}