package com.starteam.dream.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import timber.log.Timber

class DividerDecoration : RecyclerView.ItemDecoration {

    private var mOrientation: Int = 0
    private var mDividerSize: Int = 0
    private lateinit var mPaint: Paint
    private var mDividerDrawable: Drawable? = null
    private var mMarginLeft: Int = 0
    private var mMarginRight: Int = 0


    /**
     * 颜色分割线
     *
     * @param context     上下文
     * @param orientation 方向
     * @param color       分割线颜色
     * @param dividerSize 分割线大小
     */
    constructor(
        context: Context,
        orientation: Int,
        color: Int,
        dividerSize: Int,
        marginLeft: Int,
        marginRight: Int
    ) : super() {
        if (orientation != LinearLayoutManager.VERTICAL && orientation != LinearLayoutManager.HORIZONTAL) {
            throw IllegalArgumentException("Orientation is invalidate")
        }
        if (dividerSize <= 0) {
            throw IllegalArgumentException("DividerSize must be greated than 0")
        }

        mOrientation = orientation
        mDividerSize = dividerSize
        mMarginLeft = marginLeft
        mMarginRight = marginRight

        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)//设置为抗锯齿
        mPaint.color = color//设置画笔颜色
        mPaint.style = Paint.Style.FILL//设置为填充
    }

    /**
     * 图片分割线
     *
     * @param context         上下文
     * @param orientation     方向
     * @param dividerDrawable 分割线图片
     * @param dividerSize     分割线大小
     */
    constructor(
        context: Context,
        orientation: Int,
        dividerDrawable: Drawable,
        dividerSize: Int,
        marginLeft: Int,
        marginRight: Int
    ) {
        if (orientation != LinearLayoutManager.VERTICAL && orientation != LinearLayoutManager.HORIZONTAL) {
            throw IllegalArgumentException("Orientation is invalidate")
        }

        mOrientation = orientation
        mDividerDrawable = dividerDrawable
        mMarginLeft = marginLeft
        mMarginRight = marginRight

        if (dividerSize > 0) {
            mDividerSize = dividerSize
        } else {
            //如果传入的dividerSize不大于0，则使用图片的宽或高
            if (mOrientation == LinearLayoutManager.VERTICAL) {
                //如果是垂直方向，使用图片的高度
                mDividerSize = mDividerDrawable!!.intrinsicHeight
            } else {
                mDividerSize = mDividerDrawable!!.intrinsicWidth
            }
        }
        Timber.i("mDividerSize:%s", mDividerSize)
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (mOrientation == LinearLayoutManager.VERTICAL) {
            drawVertical(c, parent)
        } else {
            drawHorizontal(c, parent)
        }
    }

    /**
     * 绘制垂直分割线
     */
    private fun drawVertical(canvas: Canvas, parent: RecyclerView) {
        val left = parent.paddingLeft + mMarginLeft
        val right = parent.measuredWidth - parent.paddingRight - mMarginRight
        var childSize = parent.childCount
        if (childSize > 0) childSize--
        for (i in 0 until childSize) {
            val child = parent.getChildAt(i)
            val layoutParams = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + layoutParams.bottomMargin
            val bottom = top + mDividerSize

            if (mDividerDrawable != null) {
                //如果是图片分割线，绘制图片
                mDividerDrawable!!.setBounds(left, top, right, bottom)
                mDividerDrawable!!.draw(canvas)
            } else {
                //绘制矩形
                canvas.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), mPaint)
            }
        }
    }

    /**
     * 绘制横向水平分割线
     */
    private fun drawHorizontal(canvas: Canvas, parent: RecyclerView) {
        val top = parent.paddingTop
        val bottom = parent.measuredHeight - parent.paddingBottom
        var childSize = parent.childCount
        if (childSize > 0) childSize--
        for (i in 0 until childSize) {
            val child = parent.getChildAt(i)
            val layoutParams = child.layoutParams as RecyclerView.LayoutParams
            val left = child.right + layoutParams.rightMargin
            val right = left + mDividerSize

            if (mDividerDrawable != null) {
                //如果是图片分割线，绘制图片
                mDividerDrawable!!.setBounds(left, top, right, bottom)
                canvas.drawPaint(mPaint)
                mDividerDrawable!!.draw(canvas)
            } else {
                //绘制矩形
                canvas.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), mPaint)
            }
        }
    }

    /**
     * 设置item分割线的size
     */
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        if (mOrientation == LinearLayoutManager.VERTICAL) {
            outRect.set(0, 0, 0, mDividerSize)
        } else {
            outRect.set(0, 0, mDividerSize, 0)
        }
    }

}