package com.zyqzyq.eyepetizer.ui.view.discover.hot

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import com.zyqzyq.eyepetizer.R
import com.zyqzyq.eyepetizer.mvp.model.bean.HomeItem
import com.zyqzyq.eyepetizer.ui.view.discover.GlideImageLoader
import kotlinx.android.synthetic.main.item_hot_banner.view.*


class ScrollCardItem: FrameLayout {



    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        initView()
    }

    private fun initView(){
        View.inflate(context,R.layout.item_hot_banner,this)
    }

    fun setData(data: ArrayList<HomeItem>?) {

        val images = arrayListOf<String>()

        for (i in 0 until data!!.size){
            images.add(data!![i].data?.image!! )
        }

        hotBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
        //设置图片加载器
        hotBanner.setImageLoader(GlideImageLoader())
        //设置图片集合
        hotBanner.setImages(images)
        //设置banner样式

        //设置banner动画效果
        hotBanner.setBannerAnimation(Transformer.DepthPage)
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