package com.zyqzyq.eyepetizer.ui.view.hot

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.zyqzyq.eyepetizer.R
import com.zyqzyq.eyepetizer.mvp.model.bean.HomeItem
import com.zyqzyq.eyepetizer.ui.view.GlideImageLoader
import kotlinx.android.synthetic.main.item_hot_banner.view.*


class ScrollCardItem: FrameLayout {

//    private var banner: Banner? = null
//    private val viewPager: ViewPager by lazy {ViewPager(context)}
//    private val scrollCardAdapter: ScrollCardAdapter by lazy {ScrollCardAdapter()}
    /*private val handler = object : Handler() {
        override fun handleMessage(msg: android.os.Message) {
            viewPager.currentItem = (viewPager.currentItem + 1)%scrollCardAdapter.datas!!.size//收到消息，指向下一个页面
            this.sendEmptyMessageDelayed(0, 5000)//2S后在发送一条消息，由于在handleMessage()方法中，造成死循环。
//            Log.d(TAG, "handleMessage")
        }
    }*/

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
//        val banner: Banner by lazy { Banner(context,attrs,defStyleAttr) }
        init()
    }

    private fun init() {
        initView()
//        handler.sendEmptyMessageDelayed(0, 5000)
    }

    private fun initView(){
//        viewPager.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, DisplayManager.getRealHeight(810)?:350)
//        viewPager.adapter = scrollCardAdapter
//        addView(viewPager)
//        banner.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, DisplayManager.getRealHeight(810)?:350)
//        addView(banner)
        View.inflate(context,R.layout.item_hot_banner,this)
    }

    fun setData(data: ArrayList<HomeItem>?) {
//        scrollCardAdapter.datas = data
//        scrollCardAdapter.notifyDataSetChanged()


        val images = arrayListOf<String>()

        for (i in 0 until data!!.size){
            images.add(data!![i].data?.image!! )
        }
        val titles = arrayListOf("nihao","wohao","dajiahao")


        hotBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
        //设置图片加载器
        hotBanner.setImageLoader(GlideImageLoader())
        //设置图片集合
        hotBanner.setImages(images)
        //设置banner样式

        //设置banner动画效果
//        banner.setBannerAnimation(Transformer.DepthPage)
        //设置标题集合（当banner样式有显示title时）
//        banner.setBannerTitles(titles)
        //设置自动轮播，默认为true
//        banner.isAutoPlay(true)
        //设置轮播时间
//        banner.setDelayTime(1500)
        //设置指示器位置（当banner模式中有指示器时）
//        banner.setIndicatorGravity(BannerConfig.CENTER)
        //banner设置方法全部调用完毕时最后调用
        hotBanner.start()
    }
}