package com.weightwatchers.ww_exercise_01.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.weightwatchers.ww_exercise_01.util.loadProfileImage

@BindingAdapter("android:src")
fun setImageUrl(imageView: ImageView, userAvatar: String?) {
    imageView.loadProfileImage(userAvatar, placeholder = -1)
}
