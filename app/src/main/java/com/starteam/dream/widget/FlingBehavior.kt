package com.starteam.dream.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.OverScroller
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import com.google.android.material.appbar.AppBarLayout
import timber.log.Timber

class FlingBehavior(context: Context?, attrs: AttributeSet?) : AppBarLayout.Behavior(context, attrs) {

    private var mScroller: OverScroller? = null

    init {
        getParentScroller(context)
    }

    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: AppBarLayout,
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int
    ) {
        mScroller?.let {
            if (it.computeScrollOffset()) {
                it.abortAnimation()
            }
        }
        if (type == ViewCompat.TYPE_NON_TOUCH && topAndBottomOffset == 0) { //recyclerview的惯性比较大 ,会顶在头部一会儿, 到头直接干掉它的滑动
            ViewCompat.stopNestedScroll(target, type)
        }
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
    }

    private fun getParentScroller(context: Context?) {
        if (mScroller != null) return
        mScroller = OverScroller(context)
        try {

            val reflexClass = this.javaClass.superclass?.superclass//父类AppBarLayout.Behavior父类的父类HeaderBehavior
            val fieldScroller = reflexClass?.getDeclaredField("mScroller")
            fieldScroller?.setAccessible(true)
            fieldScroller?.set(this, mScroller)
        } catch (e: Exception) {
            Timber.e(e)
        }
    }
}