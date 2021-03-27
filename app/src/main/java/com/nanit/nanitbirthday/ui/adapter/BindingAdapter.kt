package com.nanit.nanitbirthday.ui.adapter

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(view: ImageView, url: String?) {
        url?.let {
            Glide.with(view.context)
                .load(url)
                .circleCrop()
                .into(view)
        }
    }

    @JvmStatic
    @BindingAdapter("resId")
    fun setImageResource(view: View, resId: Int) {
        view.setBackgroundResource(resId)
    }

    @JvmStatic
    @BindingAdapter("resId")
    fun setImageResource(view: ImageView, resId: Int) {
        view.setImageResource(resId)
    }
}