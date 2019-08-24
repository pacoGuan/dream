package com.starteam.dream

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.starteam.dream.adater.MainTopHeadAdapter
import com.starteam.dream.adater.NewsPagerAdapter
import com.starteam.dream.constant.Datas
import com.starteam.dream.fragment.NewsListFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity() {

    private lateinit var mainTopHeadAdapter: MainTopHeadAdapter
    private var mHandler: InnerHandler? = null
    private var downTime: Long = 0
    private var downX = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mLlSearch.setOnClickListener { view ->
            ThirdWebViewActivity.startActivity(view.context, Datas.searchUrl)
        }

        mainTopHeadAdapter = MainTopHeadAdapter(MainTopHeadAdapter.mainTopHeadInfos)
        mVpHead.adapter = mainTopHeadAdapter
        mHandler = InnerHandler(this)
        mHandler!!.removeMessages(ACTION_PLAY_IMAGE)
        mHandler!!.sendEmptyMessageDelayed(ACTION_PLAY_IMAGE, IMAGE_AUTO_PLAY_MILLISECOND.toLong())

        mVpHead.setOnTouchListener(View.OnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    downX = event.x
                    downTime = System.currentTimeMillis()
                    mHandler?.removeMessages(ACTION_PLAY_IMAGE)
                }
                MotionEvent.ACTION_UP -> {
                    val leftAndRight = Math.abs(event.x - downX) < 15
                    if (System.currentTimeMillis() - downTime < 1000 && leftAndRight) {
                        //轮播图点击事件
                        val entity =
                            mainTopHeadAdapter.getItemData(mVpHead.currentItem % mainTopHeadAdapter.getDataSize())
                        ThirdWebViewActivity.startActivity(v.context, entity.url)

                    }
                    mHandler?.sendEmptyMessageDelayed(ACTION_PLAY_IMAGE, IMAGE_AUTO_PLAY_MILLISECOND.toLong())
                }
                MotionEvent.ACTION_CANCEL -> mHandler?.sendEmptyMessageDelayed(
                    ACTION_PLAY_IMAGE,
                    IMAGE_AUTO_PLAY_MILLISECOND.toLong()
                )
            }
            false
        })


        val newsListFragments = listOf<Fragment>(
            NewsListFragment.newsInstance(0),
            NewsListFragment.newsInstance(1),
            NewsListFragment.newsInstance(2),
            NewsListFragment.newsInstance(3),
            NewsListFragment.newsInstance(4)
        )

        mTabNews.tabMode = TabLayout.MODE_FIXED
        val newsPagerAdapter = NewsPagerAdapter(this, supportFragmentManager, newsListFragments)
        mVpNews.adapter = newsPagerAdapter
        mVpNews.offscreenPageLimit = 5
        mTabNews.setupWithViewPager(mVpNews)

    }

    companion object {
        const val IMAGE_AUTO_PLAY_MILLISECOND = 5000
        const val ACTION_PLAY_IMAGE = 0
    }

    internal class InnerHandler(activity: MainActivity) : Handler() {

        private var reference: WeakReference<MainActivity>? = null

        init {
            reference = WeakReference(activity)
        }

        override fun handleMessage(msg: Message) {

            val activity = reference!!.get() ?: return

            when (msg.what) {
                ACTION_PLAY_IMAGE -> {
                    activity.mVpHead.setCurrentItem(activity.mVpHead.currentItem + 1, true)
                    activity.mHandler?.sendEmptyMessageDelayed(ACTION_PLAY_IMAGE, IMAGE_AUTO_PLAY_MILLISECOND.toLong())
                }
            }
        }
    }

    public fun onIconClick(v: View) {
        ThirdWebViewActivity.startActivity(this, v.tag?.toString())
    }

    override fun onDestroy() {
        mHandler?.removeCallbacksAndMessages(null)
        super.onDestroy()
    }
}
