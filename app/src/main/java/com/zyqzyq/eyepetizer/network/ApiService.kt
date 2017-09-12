package com.zyqzyq.eyepetizer.network

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
     * issue里面包了itemlist和nextpageurl
     */
    @GET
    fun getIssue(@Url url: String): Observable<HomeItem>


    /**
     * 获取回复
     */
    @GET("v2/replies/video?")
    fun getReply(@Query("videoId") videoId: Long): Observable<HomeItem>

    /**
     * 根据item id获取相关视频
     */
    @GET("v4/video/related?")
    fun getRelatedData(@Query("id") id: Long): Observable<HomeBean>
}