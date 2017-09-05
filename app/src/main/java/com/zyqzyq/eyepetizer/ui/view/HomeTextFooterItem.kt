package com.zyqzyq.eyepetizer.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.zyqzyq.eyepetizer.R
import kotlinx.android.synthetic.main.item_home_text_footer.view.*

class HomeTextFooterItem : FrameLayout {

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        View.inflate(context,R.layout.item_home_text_footer,this)
    }

    fun setFooterText(text: String?) {
        textFooter.text = text
    }
}