package com.zyqzyq.eyepetizer.ui.fragments

import android.app.Fragment
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zyqzyq.eyepetizer.R
import kotlinx.android.synthetic.main.toolbar.*

class DiscoverFragment: Fragment(){
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_discover,container,false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
    }
    private fun initToolbar(){
        activity.toolbar.setBackgroundColor(Color.BLUE)
//        activity.toolbar.alpha=1f
        activity.toolbar.background.alpha = 255
        activity.toolbarTitle.text = "Discover"
        activity.toolbarTitle.setTextColor(Color.BLACK)
        activity.toolbarTitle.typeface = Typeface.createFromAsset(activity.assets, "fonts/Lobster-1.4.otf")
    }
}