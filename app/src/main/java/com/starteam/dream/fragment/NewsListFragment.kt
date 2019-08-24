package com.starteam.dream.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chad.library.adapter.base.BaseQuickAdapter
import com.starteam.dream.R
import com.starteam.dream.ThirdWebViewActivity
import com.starteam.dream.adater.NewsListAdapter
import com.starteam.dream.widget.ScrollRecyclerViewLinearLayoutManager
import kotlinx.android.synthetic.main.fragment_news_list.*

class NewsListFragment : Fragment() {

    private lateinit var mNewsListAdapter: NewsListAdapter

    companion object {
        fun newsInstance(index: Int) = NewsListFragment().apply {
            val bundle = Bundle().apply { putInt("index", index) }
            arguments = bundle
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_news_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var index = arguments?.getInt("index", 0) ?: 0

        val scrollRecyclerViewLinearLayoutManager = ScrollRecyclerViewLinearLayoutManager(activity)
        //scrollRecyclerViewLinearLayoutManager.setScrollEnabled(false)
        mRvNews.layoutManager = scrollRecyclerViewLinearLayoutManager
        //mRvNews.isNestedScrollingEnabled = false
        mNewsListAdapter = NewsListAdapter()
        mNewsListAdapter.setNewData(NewsListAdapter.newsInfoList[index])
        mNewsListAdapter.setOnItemClickListener { adapter, view, position ->
            ThirdWebViewActivity.startActivity(view.context, mNewsListAdapter.getItem(position)?.url)
        }
        mRvNews.adapter = mNewsListAdapter
    }

}