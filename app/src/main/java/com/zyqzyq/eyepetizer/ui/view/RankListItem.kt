package com.zyqzyq.eyepetizer.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.zyqzyq.eyepetizer.R
import com.zyqzyq.eyepetizer.durationFormat
import com.zyqzyq.eyepetizer.mvp.model.bean.HomeItem
import kotlinx.android.synthetic.main.item_rank_list_item.view.*

/*adapter TYPE_VIDEO*/
class RankListItem : FrameLayout {

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        View.inflate(context, R.layout.item_rank_list_item, this)
    }

    fun setData(item: HomeItem) {
        val tagTxt = "#" + item.data?.category + " / " + durationFormat(item.data?.duration)
        rankListTitle.text = item.data?.title
        rankListTag.text = tagTxt
        Glide.with(context).load(item.data?.cover?.feed).centerCrop().into(rankListImage)
    }
}