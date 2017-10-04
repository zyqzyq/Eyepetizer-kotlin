package com.zyqzyq.eyepetizer.network

import com.zyqzyq.eyepetizer.mvp.model.bean.*
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

    /**
     * 获取全部分类
     */
    @GET("v4/categories")
    fun getCategoryData():Observable<ArrayList<CategoryBean>>


    /**
     * 获取分类下的全部数据
     */
    @GET("v4/categories/detail/tab")
    fun getCategoryDetail(@Query("id") id: Long): Observable<CategoryDetailTab>

    /**
     * 获取分类下一级分页的数据
     */
    @GET
    fun getCategoryDetailTab(@Url url: String): Observable<HomeBean>

    /**
     * 获取排行榜
     */
    @GET("v4/rankList")
    fun getRankList(): Observable<DiscoveryTabInfo>

    /**
     * 获取排行榜下分类的数据
     */
    @GET
    fun getRankListItemData(@Url url: String): Observable<HomeBean>
}