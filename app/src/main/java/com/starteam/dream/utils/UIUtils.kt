package com.starteam.dream.utils

import android.content.Context

object UIUtils {

    /**
     * dip-->px
     */
    fun dip2Px(context: Context, dip: Int): Int {
        val density = context.resources.displayMetrics.density
        return (dip * density + 0.5f).toInt()
    }
}