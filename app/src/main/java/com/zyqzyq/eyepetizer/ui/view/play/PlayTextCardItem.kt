package com.zyqzyq.eyepetizer.ui.view.play

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.zyqzyq.eyepetizer.R
import com.zyqzyq.eyepetizer.mvp.model.bean.HomeItem
import kotlinx.android.synthetic.main.item_play_text_card.view.*

class PlayTextCardItem : RelativeLayout{
    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        View.inflate(context, R.layout.item_play_text_card, this)
    }
    fun setText(item: HomeItem){
        playTextCard.text = item.data?.text
    }
}