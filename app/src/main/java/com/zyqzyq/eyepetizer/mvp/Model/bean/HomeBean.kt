package com.zyqzyq.eyepetizer.mvp.Model.bean


data class HomeBean(var itemList: ArrayList<HomeItem>,val count: Int, val total: Int, val nextPageUrl: String,
                    val date: Long, val nextPublishTime: Long, val dialog: Any, val topIssue: Any,
                    val refreshCount: Int, val lastStartId: Int)

//        "itemList": []
//        "count": 17,
//        "total": 0,
//        "nextPageUrl": "http://baobab.kaiyanapp.com/api/v4/tabs/selected?date=1504054800000&num=2&page=2",
//        "date": 1504227600000,
//        "nextPublishTime": 1504314000000,
//        "dialog": null,
//        "topIssue": null,
//        "refreshCount": 0,
//        "lastStartId": 0