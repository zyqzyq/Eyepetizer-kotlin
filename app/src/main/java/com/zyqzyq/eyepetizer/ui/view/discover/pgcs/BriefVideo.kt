package com.zyqzyq.eyepetizer.ui.view.discover.pgcs

import android.content.Context
import android.graphics.Bitmap
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.support.v7.widget.LinearLayoutManager
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.zyqzyq.eyepetizer.R
import com.zyqzyq.eyepetizer.mvp.model.bean.HomeItem
import kotlinx.android.synthetic.main.item_pgcs_brief_card.view.*

class BriefVideo : FrameLayout {

    val briefAdapter: BriefVideoAdapter by lazy { BriefVideoAdapter() }
    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
        initView()
    }

    private fun init() {
        View.inflate(context, R.layout.item_pgcs_brief_card, this)
    }

    private fun initView(){
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        briefVideoRV.layoutManager = linearLayoutManager
        briefCardLine.visibility = View.GONE
        briefVideoRV.visibility = View.VISIBLE
        briefVideoRV.adapter = briefAdapter
    }

    fun setData(data: HomeItem?) {
        val icon = data?.data?.header?.icon
        val iconType = data?.data?.header?.iconType
        val title = data?.data?.header?.title
        val description = data?.data?.header?.description
        val iconDefault = R.mipmap.pgc_default_avatar
        val iconCircle = object : BitmapImageViewTarget(briefCardIcon) {
            override fun setResource(resource: Bitmap?) {
                super.setResource(resource)
                val circularBitmapDrawable = RoundedBitmapDrawableFactory.create(resources, resource)
                circularBitmapDrawable.isCircular=true
                briefCardIcon.setImageDrawable(circularBitmapDrawable)
            }
        }
        if (icon == null || "" == icon) {
            Glide.with(context).load(iconDefault).asBitmap().centerCrop().into(iconCircle)
        } else {
            Glide.with(context).load(icon).asBitmap().centerCrop().into(iconCircle)
        }
        briefCardTitle.text = title
        briefCardDescription.text = description

        briefAdapter.setItemList(data?.data?.itemList!!)
        briefAdapter.notifyDataSetChanged()
    }
}