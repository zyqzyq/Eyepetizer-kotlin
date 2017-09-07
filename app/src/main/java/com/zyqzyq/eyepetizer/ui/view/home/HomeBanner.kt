package com.zyqzyq.eyepetizer.ui.view.home

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Handler
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import com.zyqzyq.eyepetizer.R
import com.zyqzyq.eyepetizer.mvp.Model.bean.HomeItem
import com.zyqzyq.eyepetizer.util.DisplayManager
import com.zyqzyq.eyepetizer.util.XTextView


/**
 * banner （viewpager +inidcater）
 * 文字动画照搬了大佬的，网上的draw canves 用不来。。。
 * */
class HomeBanner: FrameLayout{
    private val viewPager: ViewPager by lazy {ViewPager(context)}
    private val bannerAdapter: BannerAdapter by lazy {BannerAdapter()}
    private val tvTitle by lazy {XTextView(context)}
    private val tvSlogan by lazy {XTextView(context)}
    private val indicators: LinearLayout by lazy { LinearLayout(context) }
    private val msgWhat = 0
    private val handler = object : Handler() {
        override fun handleMessage(msg: android.os.Message) {
            viewPager.currentItem = (viewPager.currentItem + 1)%bannerAdapter.datas!!.size//收到消息，指向下一个页面
            this.sendEmptyMessageDelayed(msgWhat, 5000)//2S后在发送一条消息，由于在handleMessage()方法中，造成死循环。
//            Log.d(TAG, "handleMessage")
        }

    }
    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }
    private fun init(){
        initView()
        initListener()
        handler.sendEmptyMessageDelayed(msgWhat, 5000)
    }

    private fun setTitleSlogan(position: Int) {
//        currentTitlePostion=position
        val bannerItemData = bannerAdapter.datas!![position]

        tvTitle.text=bannerItemData?.data?.title
        tvSlogan.text = bannerItemData.data?.slogan
    }
    fun setData(itemList: ArrayList<HomeItem>){

        bannerAdapter.datas = itemList
        bannerAdapter.notifyDataSetChanged()
        setIndicators(itemList)
        setTitleSlogan(0)
    }

    private fun initListener(){
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                setTitleSlogan(position)
                (0 until indicators.childCount)
                        .forEach {
                            if (it == position) {
                                (indicators.getChildAt(it) as Indicator).setState(true)
                            } else {
                                (indicators.getChildAt(it) as Indicator).setState(false)
                            }
                        }
            }
        })
    }
    private fun initView(){

        viewPager.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT,DisplayManager.getRealHeight(810)?:350)
        viewPager.adapter = bannerAdapter
        viewPager.setPageTransformer(true,HomeBannerTransformer())

        val floatInfo = LinearLayout(context)
        floatInfo.gravity = Gravity.CENTER_HORIZONTAL
        floatInfo.orientation = LinearLayout.VERTICAL
        val floatInfoParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        floatInfoParams.gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
        floatInfo.layoutParams = floatInfoParams

        val homePageHeaderIcon = ImageView(context)
        homePageHeaderIcon.setImageResource(R.mipmap.home_page_header_icon)
        homePageHeaderIcon.scaleType = ImageView.ScaleType.CENTER_INSIDE
        homePageHeaderIcon.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DisplayManager.getRealHeight(110)!!)

        tvTitle.textSize = 60f
        tvTitle.color = Color.WHITE
        tvTitle.marginBottom=DisplayManager.dip2px(5f)?.toFloat()!!
        tvTitle.layoutParams=LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)

        tvSlogan.textSize = 36f
        tvSlogan.color=Color.WHITE
        tvSlogan.layoutParams=LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)

        indicators.gravity = Gravity.CENTER_HORIZONTAL
        val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DisplayManager.getRealHeight(20)!!)
        layoutParams.topMargin = DisplayManager.getRealHeight(30)!!
        layoutParams.bottomMargin = DisplayManager.getRealHeight(28)!!
        indicators.layoutParams = layoutParams
        indicators.orientation = LinearLayout.HORIZONTAL

        floatInfo.addView(homePageHeaderIcon)
        floatInfo.addView(tvTitle)
        floatInfo.addView(tvSlogan)
        floatInfo.addView(indicators)

        addView(viewPager)
        addView(floatInfo)
    }
    private fun setIndicators(bannerDatas: ArrayList<HomeItem>){
        indicators.removeAllViews()
        bannerDatas.forEach { _ ->
            val indicator = Indicator(context)
            val layoutParams = LinearLayout.LayoutParams(DisplayManager.getRealHeight(20)!!, DisplayManager.getRealHeight(20)!!)
            layoutParams.leftMargin = DisplayManager.getRealWidth(10)!!
            layoutParams.rightMargin = DisplayManager.getRealWidth(10)!!
            indicator.layoutParams = layoutParams


            indicators.addView(indicator)
        }
        (indicators.getChildAt(0) as Indicator).setState(true)
    }


}


