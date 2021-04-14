package com.tyagiabhinav.loremipsum.ui

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("android:liveVisibility")
fun setVisibility(view: View, isVisible: Boolean) {
    if (isVisible) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.VISIBLE
    }
}