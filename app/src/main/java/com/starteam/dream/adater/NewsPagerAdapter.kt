package com.starteam.dream.adater

import android.content.Context
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.starteam.dream.R

class NewsPagerAdapter(
    context: Context, fragmentManager: FragmentManager,
    newsListFragment: List<Fragment>
) : FragmentPagerAdapter(fragmentManager) {

    private var mContext: Context

    private var mTitles: Array<String>

    private var mNewsListFragment: List<Fragment>

    init {
        this.mContext = context
        this.mNewsListFragment = newsListFragment
        this.mTitles = context.resources.getStringArray(R.array.news_title)
    }

    override fun setPrimaryItem(container: ViewGroup, position: Int, `object`: Any) {
        super.setPrimaryItem(container, position, `object`)
    }

    override fun getItem(position: Int): Fragment {
        return mNewsListFragment[position]
    }

    override fun getCount(): Int {
        return mNewsListFragment.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mTitles[position % 5]
    }

}