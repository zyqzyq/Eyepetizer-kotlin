package com.zyqzyq.eyepetizer.ui.view.home

import android.content.Context
import android.graphics.Color
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.zyqzyq.eyepetizer.R
import com.zyqzyq.eyepetizer.mvp.Model.bean.HomeItem
import com.zyqzyq.eyepetizer.util.DisplayManager

/**
 * banner （viewpager +inidcater）
 * 偷懒没有写文字动画（等以后研究下大佬的搬上来）
 * */
class HomeBanner: FrameLayout{
    private val viewPager: ViewPager by lazy {ViewPager(context)}
    private val bannerAdapter: BannerAdapter by lazy {BannerAdapter()}
    private val tvTitle by lazy {TextView(context)}
    private val tvSlogan by lazy {TextView(context)}
    private val indicators: LinearLayout by lazy { LinearLayout(context) }

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }
    private fun init(){
        initView()
        initListener()
    }

    private fun setTitleSlogan(position: Int) {
//        currentTitlePostion=position
        val bannerItemData = bannerAdapter.datas!![position]
        tvTitle.text = bannerItemData.data?.title
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

        tvTitle.textSize = 26f
        tvTitle.setTextColor(Color.WHITE)
        tvTitle.paint.isFakeBoldText =true
//        tvTitle.marginBottom=DisplayManager.dip2px(5f)?.toFloat()!!
        tvTitle.layoutParams=LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)

        tvSlogan.textSize = 16f
        tvSlogan.setTextColor(Color.WHITE)
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


