package com.zyqzyq.eyepetizer.ui.view.home

import android.content.Context
import android.graphics.Color
import android.os.Handler
import android.os.Message
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.shuyu.gsyvideoplayer.utils.GSYVideoType
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer
import com.zyqzyq.eyepetizer.EmptyControlVideo
import com.zyqzyq.eyepetizer.R
import com.zyqzyq.eyepetizer.TAG
import com.zyqzyq.eyepetizer.mvp.model.bean.HomeItem
import kotlinx.android.synthetic.main.item_home_banner_item.view.*


/*主要是适配banner的图片和播放器*/
class HomeBannerItem: FrameLayout{

    lateinit var data: HomeItem
    private var isVideo = false
    private var isInitVideoView = false

    constructor(context: Context?, data: HomeItem) : super(context) {
        this.data = data
        initView()
        setUpView()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
    }
    private fun setUpView(){
        val thumbPlayUrl = data.data?.thumbPlayUrl
        imageView.visibility = View.VISIBLE
        val feedImgUrl = data.data?.cover?.feed
        Glide.with(context).load(feedImgUrl).centerCrop().into(imageView)
        if (thumbPlayUrl == null || thumbPlayUrl == ""){
            isVideo = false
            videoView.visibility = View.GONE
        }else{
            Log.d(TAG,thumbPlayUrl)
            isVideo = true
            videoView.visibility = View.VISIBLE
            videoView.setUp(thumbPlayUrl,false,"")
            videoView.isLooping =true
            GSYVideoType.setShowType(GSYVideoType.SCREEN_MATCH_FULL)
        }
    }
    private fun initView(){
       View.inflate(context, R.layout.item_home_banner_item,this)
    }

    /**
     * 开始播放
     */
    fun play() {
        if (!isInitVideoView && videoView.visibility == View.VISIBLE) {
            videoView.startPlayLogic()
            isInitVideoView = true
        }
    }
    /**
     * 释放播放器
     */
    fun releasePlayer() {
        isInitVideoView = false
        if (videoView.visibility == View.VISIBLE) {
            videoView.setStandardVideoAllCallBack(null)
            GSYVideoPlayer.releaseAllVideos()
        }
    }

}