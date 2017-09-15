package com.zyqzyq.eyepetizer.mvp.model.bean

data class DiscoveryTabInfo(val tabInfo: TabInfo,val defaultIdx: Int)
data class TabInfo(val tabList: ArrayList<Tab>)
data class Tab (val id: Long, val name: String, val apiUrl: String)