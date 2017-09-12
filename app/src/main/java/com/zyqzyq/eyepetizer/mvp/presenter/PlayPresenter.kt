package com.zyqzyq.eyepetizer.mvp.presenter

import com.zyqzyq.eyepetizer.mvp.contract.PlayContract
import com.zyqzyq.eyepetizer.mvp.model.PlayModel
import com.zyqzyq.eyepetizer.mvp.model.bean.HomeBean
import com.zyqzyq.eyepetizer.mvp.model.bean.HomeItem
import io.reactivex.disposables.Disposable

class PlayPresenter(view: PlayContract.View): PlayContract.Presenter{
    private val playView: PlayContract.View = view
    private val playModel: PlayModel by lazy {  PlayModel() }
    override fun requestBasicDataFromMemory(itemData: HomeItem): Disposable? {
        val playInfo = itemData.data?.playInfo
//        默认播放高清
        playInfo?.let {
            for ((_, url, type) in playInfo){
                if (type == "normal"){
                    playView.setPlayer(url)
                    break
                }
            }
        }

//        设置video相关信息
        playView.setVideoInfo(itemData)
//        playView.setPlayer(itemData.data?.playUrl!!)
        return requestRelatedData(itemData.data?.id!!)
    }

    override fun requestRelatedData(id: Long): Disposable? {
        return playModel.loadRelatedData(id)
                .subscribe({(itemList) ->
                    playView.setRelated(itemList)
                })
    }
}