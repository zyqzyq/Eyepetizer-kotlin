package com.zyqzyq.eyepetizer.network

import com.zyqzyq.eyepetizer.mvp.model.bean.DiscoveryTabInfo
import com.zyqzyq.eyepetizer.mvp.model.bean.HomeBean
import com.zyqzyq.eyepetizer.mvp.model.bean.HomeItem
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {

    /**
     * banner2+一页数据，num=1
     */
//    @GET("v2/feed?&num=1")
    @GET("v4/tabs/selected?&num=1")
    fun getFirstHomeData(@Query("date") date: Long): Observable<HomeBean>

    /**
     * 根据nextpageurl请求数据
     */
    @GET
    fun getMoreHomeData(@Url url: String): Observable<HomeBean>


    /**
     * 根据item id获取相关视频
     */
    @GET("v4/video/related?")
    fun getRelatedData(@Query("id") id: Long): Observable<HomeBean>

    /**
     * 获取发现
     */
    @GET("v4/discovery")
    fun getDiscovery(): Observable<DiscoveryTabInfo>

    /**
     * 获取发现下分类的数据
     */
    @GET
    fun getDiscoveryItemData(@Url url: String): Observable<HomeBean>

    /**
     * 获取发现下分类的更多数据
     */
    @GET
    fun getMoreDiscoveryItemData(@Url url: String): Observable<HomeBean>

    /**
     * 获取关注
     */
    @GET("v4/tabs/follow")
    fun getFollowData(): Observable<HomeBean>

    /**
     * 获取关注的更多数据
     */
    @GET
    fun getMoreFollowData(@Url url: String): Observable<HomeBean>
}