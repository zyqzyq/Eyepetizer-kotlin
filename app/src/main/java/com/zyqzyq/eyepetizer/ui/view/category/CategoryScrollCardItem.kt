package com.zyqzyq.eyepetizer.ui.view.category

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.text.Html
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import com.zyqzyq.eyepetizer.R
import com.zyqzyq.eyepetizer.mvp.model.bean.HomeItem
import com.zyqzyq.eyepetizer.ui.view.GlideImageLoader
import kotlinx.android.synthetic.main.item_category_scroll.view.*
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import com.zyqzyq.eyepetizer.durationFormat
import com.zyqzyq.eyepetizer.ui.activities.PlayActivity


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

        val images = arrayListOf<String>()
        val titles = arrayListOf<SpannableString>()
        for (i in 0 until data?.data?.itemList!!.size){
            images.add(data.data.itemList[i].data?.cover?.feed!! )
            val title = data.data.itemList[i].data?.title!!
            val category = data.data.itemList[i].data?.category
            val duration = durationFormat(data.data.itemList[i].data?.duration)
            val startPoint = title.length
            var spannableString = SpannableString("$title\n #$category / $duration")
            //            titles.add(data.data.itemList[i].data?.title!! + data.data.itemList[i].data?.category)

            spannableString.setSpan(ForegroundColorSpan(Color.parseColor("#B5B5B5")), startPoint, spannableString.length, Spanned.SPAN_EXCLUSIVE_INCLUSIVE )
            spannableString.setSpan(RelativeSizeSpan(.8f), startPoint, spannableString.length, Spanned.SPAN_EXCLUSIVE_INCLUSIVE )
            titles.add(spannableString)


        }
//        val images = arrayListOf(R.drawable.icon_detail_player_back,R.mipmap.ic_action_full_screen,R.mipmap.ic_action_favorites_without_padding)

        categoryBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE)
        //设置图片加载器
        categoryBanner.setImageLoader(GlideImageLoader())
        //设置图片集合
        categoryBanner.setImages(images)
        //设置banner样式

        //设置banner动画效果
//        categoryBanner.setBannerAnimation(Transformer.DepthPage)
        //设置标题集合（当banner样式有显示title时）
        categoryBanner.setBannerTitles(titles)
        //设置自动轮播，默认为true
        categoryBanner.isAutoPlay(false)
        //设置轮播时间
//        banner.setDelayTime(1500)
        //设置指示器位置（当banner模式中有指示器时）
        categoryBanner.setOnBannerListener { i ->
            val intent = Intent(context, PlayActivity::class.java)
            intent.putExtra("data", data.data.itemList[i])
            context.startActivity(intent)
        }
        categoryBanner.setIndicatorGravity(BannerConfig.CENTER)
        //banner设置方法全部调用完毕时最后调用
        categoryBanner.start()
    }
}