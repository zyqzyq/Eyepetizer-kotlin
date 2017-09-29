package com.zyqzyq.eyepetizer.mvp.model.bean


data class CategoryBean(val id: Int, val name: String , val alias: Any , val description: String,
                        val bgPicture: String, val bgColor: String, val headerImage: String)
//"id": 24,
//"name": "时尚",
//"alias": null,
//"description": "优雅地行走在潮流尖端",
//"bgPicture": "http://img.kaiyanapp.com/22192a40de238fe853b992ed57f1f098.jpeg",
//"bgColor": "",
//"headerImage": "http://img.kaiyanapp.com/c9b19c2f0a2a40f4c45564dd8ea766d3.png"

data class CategoryDetailTab(val categoryInfo: CategoryInfo,val tabInfo: TabInfo)
data class CategoryInfo(val dataType: String, val id: Int ,val name: String,val description: String,
                        val headerImage: String,val actionUrl: String)

//"dataType": "CategoryInfo",
//"id": 36,
//"name": "生活",
//"description": "匠心、健康、生活感悟",
//"headerImage": "http://img.kaiyanapp.com/a1a1bf7ed3ac906ee4e8925218dd9fbe.png",
//"actionUrl": "eyepetizer://category/36/?title=%E7%94%9F%E6%B4%BB",
//"follow": {
//    "itemType": "category",
//    "itemId": 36,
//    "followed": false
//}