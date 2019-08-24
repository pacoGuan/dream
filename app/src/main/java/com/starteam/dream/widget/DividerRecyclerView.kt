package com.starteam.dream.widget

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.starteam.dream.R
import com.starteam.dream.utils.UIUtils

class DividerRecyclerView : RecyclerView {

    private val mContext: Context

    //分割线的颜色
    private var mDividerColor: Int
    //分割线的大小
    private var mDividerSize = 1
    //分割线的drawable
    private var mDividerDrawable: Drawable?
    //是否使用瀑布流布局,默认不是
    private var mUseStaggerLayout: Boolean = false
    //列数，默认为1
    private var mNumColumns = 1
    //RecyclerView的方向，默认为垂直方向
    private var mOrientation = VERTICAL

    private var mMarginLeft: Int
    private var mMarginRight: Int

    private lateinit var mLayoutManager: LayoutManager
    private lateinit var mDividerDecoration: DividerDecoration
    private var mItemDrawable: Drawable? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)

    constructor(context: Context, attributeSet: AttributeSet?, defStyle: Int) : super(context, attributeSet, defStyle) {

        mContext = context

        val ta = context.obtainStyledAttributes(attributeSet, R.styleable.PowerfulRecyclerView)

        mDividerColor = ta.getColor(R.styleable.PowerfulRecyclerView_dividerColor, Color.parseColor("#ffd8d8d8"))
        mDividerSize =
            ta.getDimensionPixelSize(R.styleable.PowerfulRecyclerView_dividerSize, UIUtils.dip2Px(context, 1))

        mDividerDrawable = ta.getDrawable(R.styleable.PowerfulRecyclerView_dividerDrawable)

        mUseStaggerLayout = ta.getBoolean(R.styleable.PowerfulRecyclerView_useStaggerLayout, mUseStaggerLayout)
        mNumColumns = ta.getInt(R.styleable.PowerfulRecyclerView_numColumns, mNumColumns)

        mOrientation = ta.getInt(R.styleable.PowerfulRecyclerView_rvOrientation, mOrientation)

        mMarginLeft = ta.getDimensionPixelSize(R.styleable.PowerfulRecyclerView_dividerMarginLeft, 0)
        mMarginRight = ta.getDimensionPixelSize(R.styleable.PowerfulRecyclerView_dividerMarginRight, 0)

        ta.recycle()

        setLayoutManager()
        setDivider()
    }

    /**
     * 设置layoutManager
     */
    private fun setLayoutManager() {
        if (!mUseStaggerLayout) {
            //不是瀑布流布局，即是列表或网格布局
            if (mOrientation == LinearLayoutManager.VERTICAL) {
                mLayoutManager = GridLayoutManager(mContext, mNumColumns)
            } else {
                mLayoutManager = GridLayoutManager(mContext, mNumColumns, mOrientation, false)
            }
        } else {
            //瀑布流布局
            mLayoutManager = StaggeredGridLayoutManager(mNumColumns, mOrientation)
        }

        layoutManager = mLayoutManager
    }

    /**
     * 设置分割线
     */
    private fun setDivider() {

        if (mDividerDrawable == null) {
            //绘制颜色分割线
            mDividerDecoration =
                DividerDecoration(mContext, mOrientation, mDividerColor, mDividerSize, mMarginLeft, mMarginRight)
        } else {
            mDividerDrawable?.let {
                //绘制图片分割线
                mDividerDecoration =
                    DividerDecoration(mContext, mOrientation, it, mDividerSize, mMarginLeft, mMarginRight)
            }
        }
        this.addItemDecoration(mDividerDecoration)
    }
}