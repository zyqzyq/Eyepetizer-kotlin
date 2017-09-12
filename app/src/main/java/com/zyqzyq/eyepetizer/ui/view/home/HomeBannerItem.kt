package com.zyqzyq.eyepetizer.ui.view.home

import android.content.Context
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
import kotlinx.android.synthetic.main.item_home_banner.view.*

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
            isVideo = true
            videoView.visibility = View.VISIBLE
            videoView.setUp(thumbPlayUrl,false,"")
            GSYVideoType.setShowType(GSYVideoType.SCREEN_MATCH_FULL)
        }
    }
    private fun initView(){
        val view = View.inflate(context, R.layout.item_home_banner,null)
        addView(view)
    }
    private fun initVideoView() {
        isInitVideoView = true
        videoView.setVideoAllCallBack(object : EmptyControlVideo.EmptyControlVideoCallBack() {
            override fun onPrepared(url: String?, vararg objects: Any?) {
                Log.d(TAG, "onPrepared")//加载成功
                imageView.visibility = View.INVISIBLE
            }

            override fun onAutoComplete(url: String?, vararg objects: Any?) {
                Log.d(TAG, "onAutoComplete")//播放完成
                imageView.visibility = View.VISIBLE
                videoView.startPlayLogic()

            }

            override fun onPlayError(url: String?, vararg objects: Any?) {
                Log.d(TAG, "onPlayError")
                imageView.visibility = View.VISIBLE
                videoView.startPlayLogic()
            }
        })
    }
    /**
     * 开始播放
     */
    fun play() {
        if (!isInitVideoView && videoView.visibility == View.VISIBLE) {
            videoView.startPlayLogic()
            initVideoView()
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