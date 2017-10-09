package com.zyqzyq.eyepetizer.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.zyqzyq.eyepetizer.R
import com.zyqzyq.eyepetizer.mvp.contract.SearchContract
import com.zyqzyq.eyepetizer.mvp.model.bean.HomeItem
import com.zyqzyq.eyepetizer.mvp.presenter.SearchPresenter
import com.zyqzyq.eyepetizer.ui.adapters.SearchAdapter
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.toast
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager

class SearchActivity : AppCompatActivity(),SearchContract.View {

    val adapter by lazy { SearchAdapter() }
    val presenter by lazy { SearchPresenter(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        initView()
    }
    fun initView(){
        searchRecyclerView.layoutManager = LinearLayoutManager(this)
        searchRecyclerView.adapter = adapter

        searchBackImage.setOnClickListener { onBackPressed() }
        searchImageView.setOnClickListener {
            searchMore.visibility = View.GONE
            searchHint.text = "搜索结果"
            val searchText = searchKeyWord.text
            presenter.requestFirstData(searchText.toString())
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(searchKeyWord.windowToken, 0)
        }
        searchKeyWord.setOnEditorActionListener { textView, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH)
            {
                searchMore.visibility = View.GONE
                searchHint.text = "搜索结果"
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(searchKeyWord.windowToken, 0)
                val searchText = searchKeyWord.text
                presenter.requestFirstData(searchText.toString())
                true
            }
            false
        }
        searchRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    val childCount = searchRecyclerView.childCount
                    val itemCount = searchRecyclerView.layoutManager.itemCount
                    val firstVisibleItem = (searchRecyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                    if (firstVisibleItem + childCount == itemCount){
//                        下拉到底加载更多，暂未设置加载图标
//                        onLoadMore()
                        presenter.requestMoreData()
                    }

                }
            }

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }

        })

    }

    override fun setFirstData(itemList: ArrayList<HomeItem>) {
        adapter.setItemList(itemList)
    }

    override fun setMoreData(itemList: ArrayList<HomeItem>) {
        adapter.addItemList(itemList)
    }
    override fun onError() {
        toast("网络错误")
    }

}
