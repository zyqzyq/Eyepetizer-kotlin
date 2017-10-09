package com.zyqzyq.eyepetizer.ui.view.play

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.zyqzyq.eyepetizer.R
import com.zyqzyq.eyepetizer.durationFormat
import com.zyqzyq.eyepetizer.mvp.model.bean.HomeItem
import kotlinx.android.synthetic.main.item_play_info.view.*
import org.jetbrains.anko.toast

class PlayInfoItem : LinearLayout {

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        View.inflate(context, R.layout.item_play_info, this)
    }

    fun setData(item: HomeItem) {
        playInfoTitle.text = item.data?.title
        playInfoCategory.text = "#${item.data?.category}  /  ${durationFormat(item.data?.duration)} ${if (item.data?.library == "DAILY") " /  开眼精选" else ""}"
        playInfoDescription.text = item.data?.description
        playInfoFavoritesNum.text = item.data?.consumption?.collectionCount.toString()
        playInfoShareNum.text = item.data?.consumption?.shareCount.toString()
        playInfoReplyNum.text = item.data?.consumption?.replyCount.toString()
        item.data?.playUrl
        playInfoFavoritesImg.setOnClickListener { context.toast("手动点赞成功") }
        playInfoShareImg.setOnClickListener { context.toast("分享失败,希望版本更新后能成功") }
        playInfoReplyImg.setOnClickListener { context.toast("评论部分暂未实现") }
        playInfoReplyImg.setOnClickListener { context.toast("没有流量啦,缓存失败") }
    }

}