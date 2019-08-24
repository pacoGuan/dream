package com.starteam.dream.adater

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.starteam.dream.R
import com.starteam.dream.entity.MainTopHeadInfo
import com.starteam.dream.utils.GlideUtils

class MainTopHeadAdapter(var mData: List<MainTopHeadInfo>) : PagerAdapter() {

    companion object {
        var mainTopHeadInfos = listOf(
            MainTopHeadInfo(R.mipmap.banner_android, "https://developer.android.google.cn/"),
            MainTopHeadInfo(R.mipmap.banner_github, "https://github.com/search?q=android")
        )
    }

    override fun getCount(): Int {
        return if (mData.size == 1) mData.size else Integer.MAX_VALUE
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        if (mData.isEmpty()) {
            return Unit
        }
        val realPosition = position % mData.size
        val imageView = ImageView(container.context)
        imageView.scaleType = ImageView.ScaleType.FIT_XY
        GlideUtils.load(container.context, mData[realPosition].resource, imageView)
        container.addView(imageView)
        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    fun getDataSize(): Int {
        return mData.size
    }

    fun getItemData(position: Int): MainTopHeadInfo {
        return mData[position]
    }
}