package com.weightwatchers.ww_exercise_01.util

import android.app.Activity
import android.text.SpannableStringBuilder
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.weightwatchers.ww_exercise_01.R
import com.weightwatchers.ww_exercise_01.extensions.setColor

fun Activity.appSnackbar(coordinatorLayout: CoordinatorLayout, message: String, duration: Int) =
    Snackbar.make(
        coordinatorLayout,
        SpannableStringBuilder(message).setColor(this, android.R.color.white),
        duration
    ).apply {
        view.setBackgroundColor(ContextCompat.getColor(this@appSnackbar, R.color.dark_transparent))
        setActionTextColor(ContextCompat.getColor(view.context, android.R.color.white))
    }