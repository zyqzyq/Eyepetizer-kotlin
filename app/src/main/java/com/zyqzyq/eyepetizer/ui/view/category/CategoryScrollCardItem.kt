package com.zyqzyq.eyepetizer.ui.view.category

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.zyqzyq.eyepetizer.R
import com.zyqzyq.eyepetizer.mvp.model.bean.HomeItem
import kotlinx.android.synthetic.main.item_category_scroll.view.*

class CategoryScrollCardItem: FrameLayout{
    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }
    private fun init(){
        initView()
    }
    private fun initView(){
        View.inflate(context, R.layout.item_category_scroll,this)
    }
    fun setData(data: HomeItem?){
        categoryTitle.text = data?.data?.header?.title ?: "热门排行"
        categoryTitle.paint.isFakeBoldText = true
        categoryTitle.setTextColor(Color.BLACK)
        categorySubTitle.text = data?.data?.header?.subtitle ?: "热门排行sub"
    }
}