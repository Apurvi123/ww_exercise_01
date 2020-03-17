package com.weightwatchers.ww_exercise_01

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.ProgressBar
import androidx.annotation.ColorInt

class TestAwareProgressBar : ProgressBar {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun setIndeterminateDrawable(drawable: Drawable?) {
        super.setIndeterminateDrawable(drawable)
    }

    fun setProgressBarColor(@ColorInt color: Int) {
        indeterminateDrawable.setColorFilter(color, PorterDuff.Mode.SRC_IN)
    }
}