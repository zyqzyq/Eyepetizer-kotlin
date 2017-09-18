package com.zyqzyq.eyepetizer.ui.view.play

import android.content.Context
import android.graphics.Color

import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.zyqzyq.eyepetizer.R
import kotlinx.android.synthetic.main.item_standard_text.view.*

class PlayEndItem : LinearLayout {

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        View.inflate(context, R.layout.item_standard_text, this)
        standardText.text = "-The end-"
        standardText.setPadding(0,100,0,100)
        standardText.setTextColor(Color.WHITE)
        standardText.typeface = Typeface.createFromAsset(context.assets,"fonts/Lobster-1.4.otf")
    }

    fun setShow(isShow: Boolean){
        standardText.visibility = if (isShow) View.VISIBLE else View.GONE
    }
}