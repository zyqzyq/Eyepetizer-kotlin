package com.zyqzyq.eyepetizer.ui.activities

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.LayoutInflater
import android.view.View
import com.zyqzyq.eyepetizer.MainData
import com.zyqzyq.eyepetizer.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.tabview.view.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.toast

/**
*主页面
 * 主要实现底部导航栏
 * 将toolbar放在主页面上
* */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()
        initView()
    }
    private fun initToolbar() {
        toolbarTitle.text = "open eyes"
        toolbar.inflateMenu(R.menu.toolbar_item)
        toolbar.setBackgroundColor(Color.WHITE)
        toolbar.setTitleTextColor(Color.BLACK)
        toolbarTitle.setTextColor(Color.BLACK)
        toolbarTitle.typeface = Typeface.createFromAsset(this.assets, "fonts/Lobster-1.4.otf")
        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.app_bar_search -> toast("searching")
            }
            true
        }
    }
    private fun onToolbarSelected(position: Int){
        when(position){
            0 ->{toolbar.menu.getItem(0).setIcon(R.mipmap.ic_action_search_white)
                toolbar.background.alpha = 0
                    toolbarTitle.text = ""
                    toolbar.title = ""
                }
            1 ->{ toolbar.menu.getItem(0).setIcon(R.mipmap.ic_action_search)
                    toolbar.background.alpha = 255
                    toolbarTitle.text = "Discover"
                    toolbar.title = "全部分类"
                }
            2 ->{toolbar.menu.getItem(0).setIcon(R.mipmap.ic_action_search)
                toolbar.background.alpha = 255
                toolbarTitle.text = "Follow"
                toolbar.title = "全部作者"
            }
            3 ->{
                toolbar.menu.getItem(0).setIcon(R.mipmap.ic_menu_more)
                toolbar.background.alpha = 0
                toolbarTitle.text = ""
                toolbar.title = ""
            }
        }
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
                        view?.tab_content_text?.setTextColor(Color.BLACK)

                    } else {// 未选中状态
                        view?.tab_content_image?.setImageResource(MainData.mainTabRes[i])
                        view?.tab_content_text?.setTextColor(Color.DKGRAY)
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
//        val transaction = fragmentManager.beginTransaction()
        val transaction = supportFragmentManager.beginTransaction()//v4 使用supportFragmentManager
        transaction.replace(R.id.main_container, MainData.mainFragmentList[position]).commit()
        onToolbarSelected(position)
    }
}
