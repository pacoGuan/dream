package com.starteam.dream.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

object GlideUtils {

    fun load(context: Context, thumb: String?, iv: ImageView) {
        if (thumb.isNullOrEmpty()) {
            return
        }
        Glide.with(context)
            .load(thumb)
            .into(iv)
    }

    fun load(context: Context, resource: Int, iv: ImageView) {
        Glide.with(context)
            .load(resource)
            .into(iv)
    }
}