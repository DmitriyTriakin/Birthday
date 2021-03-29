package com.nanit.nanitbirthday.ui.adapter

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.nanit.nanitbirthday.R

object BindingAdapter {

    @JvmStatic
    @BindingAdapter(value = ["imageUrl", "placeholder"], requireAll = false)
    fun loadImage(view: ImageView, url: String?, resId: Int?) {
        url?.let {
            Glide.with(view.context)
                .load(url)
                .circleCrop()
                .placeholder(resId ?: R.drawable.ic_def_place_holder_blue)
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