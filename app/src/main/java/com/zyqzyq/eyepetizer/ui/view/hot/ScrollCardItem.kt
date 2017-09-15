package com.zyqzyq.eyepetizer.ui.view.hot

import android.content.Context
import android.os.Handler
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.widget.FrameLayout
import com.zyqzyq.eyepetizer.mvp.model.bean.HomeItem
import com.zyqzyq.eyepetizer.util.DisplayManager

class ScrollCardItem: FrameLayout {

    private val viewPager: ViewPager by lazy {ViewPager(context)}
    private val scrollCardAdapter: ScrollCardAdapter by lazy {ScrollCardAdapter()}
    private val handler = object : Handler() {
        override fun handleMessage(msg: android.os.Message) {
            viewPager.currentItem = (viewPager.currentItem + 1)%scrollCardAdapter.datas!!.size//收到消息，指向下一个页面
            this.sendEmptyMessageDelayed(0, 5000)//2S后在发送一条消息，由于在handleMessage()方法中，造成死循环。
//            Log.d(TAG, "handleMessage")
        }
    }

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        initView()
        handler.sendEmptyMessageDelayed(0, 5000)
    }

    private fun initView(){
        viewPager.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, DisplayManager.getRealHeight(810)?:350)
        viewPager.adapter = scrollCardAdapter
        addView(viewPager)
    }

    fun setData(data: ArrayList<HomeItem>?) {
        scrollCardAdapter.datas = data
        scrollCardAdapter.notifyDataSetChanged()
    }
}