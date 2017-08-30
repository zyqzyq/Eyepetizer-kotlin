package com.zyqzyq.eyepetizer.activities

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.LayoutInflater
import android.view.View
import com.zyqzyq.eyepetizer.MainData
import com.zyqzyq.eyepetizer.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.tabview.view.*



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }
    private fun initView(){
        bottom_tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                onTabItemSelected(tab.position)
                // Tab 选中之后，改变各个Tab的状态
                for (i in 0 until bottom_tab_layout.tabCount ) {
                    val view = bottom_tab_layout.getTabAt(i)!!.customView

                    if (i == tab.position) { // 选中状态
                        view?.tab_content_image?.setImageResource(MainData.mainTabResPressed[i])
                        view?.tab_content_text?.setTextColor(resources.getColor(android.R.color.black))

                    } else {// 未选中状态
                        view?.tab_content_image?.setImageResource(MainData.mainTabRes[i])
                        view?.tab_content_text?.setTextColor(resources.getColor(android.R.color.darker_gray))
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

        for(i in 0..3) {
            bottom_tab_layout.addTab(bottom_tab_layout.newTab().setCustomView(getTabView(this, i)))
        }
    }

    private fun getTabView(context: Context,position: Int): View{
        val view = LayoutInflater.from(context).inflate(R.layout.tabview, null)
        view.tab_content_image.setImageResource(MainData.mainTabRes[position])
        view.tab_content_text.text = MainData.mainTabStr[position]
        return view
    }

    fun onTabItemSelected(position: Int){
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.main_container, MainData.mainFragmentList[position]).commit()
    }
}
