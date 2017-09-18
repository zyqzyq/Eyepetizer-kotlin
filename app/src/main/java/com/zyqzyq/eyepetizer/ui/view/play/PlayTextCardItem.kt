package com.zyqzyq.eyepetizer.ui.view.play

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.zyqzyq.eyepetizer.R
import com.zyqzyq.eyepetizer.mvp.model.bean.HomeItem
import kotlinx.android.synthetic.main.item_standard_text.view.*

class PlayTextCardItem : RelativeLayout{
    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
        initView()
    }

    private fun init() {
        View.inflate(context, R.layout.item_standard_text, this)
    }
    private fun initView(){
        val standardTextParams = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT)
        standardTextParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT)
        standardText.layoutParams = standardTextParams
        standardText.setTextColor(Color.WHITE)
        standardImage.visibility = View.VISIBLE
        standardLine.visibility = View.GONE
    }
    fun setText(item: HomeItem){
        standardText.text = item.data?.text
    }
}