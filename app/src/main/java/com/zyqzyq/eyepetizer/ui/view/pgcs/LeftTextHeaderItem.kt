package com.zyqzyq.eyepetizer.ui.view.pgcs

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.RelativeLayout
import com.zyqzyq.eyepetizer.R
import kotlinx.android.synthetic.main.item_standard_text.view.*


class LeftTextHeaderItem : FrameLayout {


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
        standardText.setTextAppearance(context,android.R.attr.textAppearanceSmall)
        standardText.typeface = Typeface.createFromAsset(context.assets,"fonts/FZLanTingHeiS-L-GB-Regular.TTF")
        standardLine.visibility = View.GONE
    }

    fun setHeaderText(text: String?) {
        standardText.text = text
    }
}