package com.zyqzyq.eyepetizer.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.zyqzyq.eyepetizer.PACS_ALL
import com.zyqzyq.eyepetizer.R
import com.zyqzyq.eyepetizer.ui.fragments.DiscoveryPgcsFragment
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.toast

class PgcsAllActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pgcs_all)
        toolbarTitle.text = "全部作者"
        toolbar.setNavigationIcon(R.mipmap.ic_action_close)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        val transaction = supportFragmentManager.beginTransaction()//v4 使用supportFragmentManager
        transaction.replace(R.id.pgcsFrame, DiscoveryPgcsFragment(PACS_ALL)).commit()

    }
}
