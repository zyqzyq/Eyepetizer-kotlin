package com.zyqzyq.eyepetizer.ui.view.home

import android.content.Context
import android.os.Handler
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.zyqzyq.eyepetizer.R
import com.zyqzyq.eyepetizer.mvp.model.bean.HomeItem
import com.zyqzyq.eyepetizer.util.DisplayManager
import com.zyqzyq.eyepetizer.util.TiaoZiUtil
import kotlinx.android.synthetic.main.item_home_banner.view.*


/**
 * banner （viewpager +inidcater）
 * 文字动画照搬了大佬的，网上的draw canves 用不来。。。
 * */
class HomeBanner: FrameLayout{
    private val bannerAdapter: BannerAdapter by lazy {BannerAdapter()}
    private val msgWhat = 0
    private val handler = object : Handler() {
        override fun handleMessage(msg: android.os.Message) {
            bannerViewPager.currentItem = (bannerViewPager.currentItem + 1)%bannerAdapter.datas!!.size//收到消息，指向下一个页面
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

//        tvTitle.text=bannerItemData.data?.title
        TiaoZiUtil(bannerTitle,bannerItemData.data?.title!!)
        TiaoZiUtil(bannerSlogan,bannerItemData.data?.slogan!!)
    }
    fun setData(itemList: ArrayList<HomeItem>){

        bannerAdapter.datas = itemList
        bannerAdapter.notifyDataSetChanged()
        setIndicators(itemList)
        setTitleSlogan(0)
    }

    private fun initListener(){
        bannerViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                setTitleSlogan(position)
                (0 until bannerIndicators.childCount)
                        .forEach {
                            if (it == position) {
                                (bannerIndicators.getChildAt(it) as Indicator).setState(true)
                            } else {
                                (bannerIndicators.getChildAt(it) as Indicator).setState(false)
                            }
                        }
            }
        })
    }
    private fun initView(){

//        bannerViewPager.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT,DisplayManager.getRealHeight(810)?:350)
        View.inflate(context,R.layout.item_home_banner,this)
        bannerViewPager.adapter = bannerAdapter
        bannerViewPager.setPageTransformer(true,HomeBannerTransformer())
    }
    private fun setIndicators(bannerDatas: ArrayList<HomeItem>){
        bannerIndicators.removeAllViews()
        bannerDatas.forEach { _ ->
            val indicator = Indicator(context)
            val layoutParams = LinearLayout.LayoutParams(DisplayManager.getRealHeight(20)!!, DisplayManager.getRealHeight(20)!!)
            layoutParams.leftMargin = DisplayManager.getRealWidth(10)!!
            layoutParams.rightMargin = DisplayManager.getRealWidth(10)!!
            indicator.layoutParams = layoutParams

            bannerIndicators.addView(indicator)
        }
        (bannerIndicators.getChildAt(0) as Indicator).setState(true)
    }


}


