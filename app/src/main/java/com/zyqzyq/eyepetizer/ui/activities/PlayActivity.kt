package com.zyqzyq.eyepetizer.ui.activities

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.media.AudioManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.shuyu.gsyvideoplayer.utils.OrientationUtils
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer
import com.zyqzyq.eyepetizer.R
import com.zyqzyq.eyepetizer.mvp.contract.PlayContract
import com.zyqzyq.eyepetizer.mvp.model.bean.HomeItem
import com.zyqzyq.eyepetizer.mvp.presenter.PlayPresenter
import com.zyqzyq.eyepetizer.ui.adapters.PlayAdapter
import kotlinx.android.synthetic.main.activity_play.*
import org.jetbrains.anko.toast
import tv.danmaku.ijk.media.player.IjkMediaPlayer


class PlayActivity : AppCompatActivity() ,PlayContract.View{
    private lateinit var presenter: PlayPresenter
    private lateinit var itemData: HomeItem
    private val adapter by lazy { PlayAdapter() }
    private val orientationUtils by lazy {   OrientationUtils(this,gsyPlayer) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)
        presenter = PlayPresenter(this)
        initView()
        loadData()
    }
    private fun initView(){
        gsyPlayer.isRotateViewAuto = false
        gsyPlayer.fullscreenButton.setOnClickListener {
            if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            }
            gsyPlayer.startWindowFullscreen(this,true,true)
        }
        gsyPlayer.backButton.setOnClickListener { onBackPressed() }
        gsyPlayer.backButton.setImageResource(R.mipmap.ic_action_detail_back)
        rv_detail.layoutManager = LinearLayoutManager(this)
        rv_detail.adapter = adapter
        val mediaPlayer = IjkMediaPlayer()
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
    }


    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        gsyPlayer?.fullWindowPlayer?.fullscreenButton?.setOnClickListener {
            GSYVideoPlayer.backFromWindowFull(this)
            if (this.resources.configuration.orientation != Configuration.ORIENTATION_PORTRAIT) {
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }
        }
        if (newConfig?.orientation==Configuration.ORIENTATION_LANDSCAPE)
            gsyPlayer.startWindowFullscreen(this,true,true)
        else
            gsyPlayer.onBackFullscreen()
    }

    private fun loadData(){
        itemData = intent.getSerializableExtra("data") as HomeItem
        presenter.requestBasicDataFromMemory(itemData)
    }

    override fun setPlayer(playUrl: String) {
        gsyPlayer.setUp(playUrl, true, null,"")
        gsyPlayer.startPlayLogic()
        gsyPlayer.backButton.visibility = View.VISIBLE
//        GSYVideoType.setShowType(GSYVideoType.SCREEN_MATCH_FULL)
    }

    override fun setVideoInfo(info: HomeItem) {
        adapter.addData(info)
    }

    override fun setRelated(items: ArrayList<HomeItem>) {
        adapter.addData(items)
    }
    override fun onPause() {
        super.onPause()
        GSYVideoManager.onPause()
    }

    override fun onResume() {
        super.onResume()
        GSYVideoManager.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        GSYVideoPlayer.releaseAllVideos()
        orientationUtils.releaseListener()
    }

}
