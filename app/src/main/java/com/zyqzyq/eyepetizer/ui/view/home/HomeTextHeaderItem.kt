package com.zyqzyq.eyepetizer.ui.view.home

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.zyqzyq.eyepetizer.R
import kotlinx.android.synthetic.main.item_standard_text.view.*

class HomeTextHeaderItem : FrameLayout {


    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
        initView()
    }

    private fun init() {
        View.inflate(context, R.layout.item_standard_text,this)
    }

    private fun initView(){
        standardText.text = "hello"
        standardText.typeface = Typeface.createFromAsset(context?.assets, "fonts/Lobster-1.4.otf")
    }

    fun setHeaderText(text: String?) {
        standardText.text = text
    }
}