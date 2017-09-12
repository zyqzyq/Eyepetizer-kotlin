package com.zyqzyq.eyepetizer.ui.view.play

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import com.zyqzyq.eyepetizer.R
import com.zyqzyq.eyepetizer.durationFormat
import com.zyqzyq.eyepetizer.mvp.model.bean.HomeItem
import kotlinx.android.synthetic.main.item_play_video_card.view.*

class PlayVideoCardItem : RelativeLayout {
    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        View.inflate(context, R.layout.item_play_video_card, this)
    }

    fun setData(item: HomeItem) {
        //设置图片
        Glide.with(context).load(item.data?.cover?.detail).centerCrop().into(this.iv_video_card)
        this.tv_title.text = item.data?.title
        this.tv_tag.text = "#${item.data?.category}  /  ${durationFormat(item.data?.duration)} ${if (item.data?.library == "DAILY") " /  开眼精选" else ""}"
    }
}