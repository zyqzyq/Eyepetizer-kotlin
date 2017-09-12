package com.zyqzyq.eyepetizer.mvp.contract

import com.zyqzyq.eyepetizer.mvp.base.BasePresenter
import com.zyqzyq.eyepetizer.mvp.base.BaseView
import com.zyqzyq.eyepetizer.mvp.model.bean.HomeItem
import io.reactivex.disposables.Disposable

interface PlayContract{
    interface View: BaseView<Presenter>{
        /**
         * 设置播放器
         */
        fun setPlayer(playUrl: String)
        /**
         * 设置影片信息
         */
        fun setVideoInfo(info: HomeItem)

        /**
         * 设置相关视频
         */
        fun setRelated(items: ArrayList<HomeItem>)
    }
    interface Presenter:BasePresenter{
        /**
         * 请求相关视频数据
         */
        fun requestRelatedData(id: Long): Disposable?

        /**
         * 从内存中获取基础数据（影片信息、作者信息等）
         */
        fun requestBasicDataFromMemory(itemData: HomeItem): Disposable?


    }
}