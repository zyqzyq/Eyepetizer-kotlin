package com.zyqzyq.eyepetizer.ui.view.hot

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.zyqzyq.eyepetizer.R
import com.zyqzyq.eyepetizer.mvp.model.bean.HomeItem
import kotlinx.android.synthetic.main.item_hot_square.view.*

class SquareCardItem : FrameLayout {

    private val squareCardAdapter: SquareCardAdapter by lazy { SquareCardAdapter() }
    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
        initView()
    }

    private fun init() {
        View.inflate(context, R.layout.item_hot_square, this)
    }

    private fun initView(){
//        hot_square_rv.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT )
//        hot_square_rv.isHorizontalScrollBarEnabled = true
        //设置布局管理器
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        hotSquareRv.layoutManager = linearLayoutManager
        hotSquareRv.adapter = squareCardAdapter
    }
    fun setData(data: HomeItem?) {
        hotSquartTv.text = data?.data?.header?.title ?: "热门排行"
        hotSquartTv.paint.isFakeBoldText = true
        hotSquartTv.gravity = Gravity.CENTER
        squareCardAdapter.setItemList(data?.data?.itemList!!)
        squareCardAdapter.notifyDataSetChanged()
    }
}