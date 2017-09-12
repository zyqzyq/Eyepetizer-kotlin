package com.zyqzyq.eyepetizer.ui.view.home

import android.content.Context
import android.graphics.Bitmap
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.zyqzyq.eyepetizer.R
import com.zyqzyq.eyepetizer.durationFormat
import com.zyqzyq.eyepetizer.mvp.model.bean.HomeItem
import kotlinx.android.synthetic.main.item_home_standard.view.*
/*adpater TYPE_VIDEO*/
class HomeStandardItem : FrameLayout {


    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        View.inflate(context, R.layout.item_home_standard, this)
    }

    fun setData(item: HomeItem) {
        val data = item.data
        val homepage = data?.cover?.homepage ?:data?.cover?.feed
        var avatar = data?.author?.icon
        val avatarRes = R.mipmap.pgc_default_avatar

        if (avatar == null || "" == avatar) {
            avatar = data?.provider?.icon
        }

        Glide.with(context).load(homepage).centerCrop().into(iv_cover)

        val ivAvatarCircle = object : BitmapImageViewTarget(iv_avatar) {
            override fun setResource(resource: Bitmap?) {
                super.setResource(resource)
                val circularBitmapDrawable = RoundedBitmapDrawableFactory.create(resources, resource)
                circularBitmapDrawable.isCircular=true
                iv_avatar.setImageDrawable(circularBitmapDrawable)
            }
        }
        if (avatar == null || "" == avatar) {
            Glide.with(context).load(avatarRes).asBitmap().centerCrop().into(ivAvatarCircle)
        } else {
            Glide.with(context).load(avatar).asBitmap().centerCrop().into(ivAvatarCircle)
        }
        tv_title.text = item.data?.title
        var tagText = ""
        data?.tags?.take(4)?.forEach { tagText += (it.name + " / ") }
        val timeFromat = durationFormat(data?.duration)
        tagText += timeFromat
        tv_tag.text = tagText
        tv_category.text = data?.category
    }


}