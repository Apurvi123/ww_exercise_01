package com.weightwatchers.ww_exercise_01.util

import android.net.Uri
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.weightwatchers.ww_exercise_01.R
import com.weightwatchers.ww_exercise_01.ui.glide.GlideApp
import com.weightwatchers.ww_exercise_01.ui.glide.GlideLogger

interface ImageHelper {
    fun loadProfileImage(
        uri: String?,
        imageView: ImageView,
        placeholderResource: Int = R.mipmap.ic_launcher_round,
        errorHandler: () -> Unit = {}
    )

    fun clearGlideRequest(imageView: ImageView)

    fun loadImageResource(iconResId: Int, imageView: ImageView, enableCircleCrop: Boolean = false)

    fun loadVideoImageResources(
        uri: String?,
        imageView: ImageView,
        placeholderResource: Int = R.mipmap.ic_launcher_round,
        errorHandler: () -> Unit = {}
    )
}

class GlideImageHelper : ImageHelper {
    override fun loadProfileImage(
        uri: String?,
        imageView: ImageView,
        placeholderResource: Int,
        errorHandler: () -> Unit
    ) {
        if (uri.isNullOrBlank()) {
            loadImageResource(placeholderResource, imageView, true)
        } else {
            GlideApp.with(imageView.context)
                .load(Uri.parse(uri))
                .thumbnail(Glide.with(imageView.context).load(placeholderResource))
                .dontAnimate()
                .error(placeholderResource)
                .listener(ErrorGlideLogger(errorHandler))
                .transform(CircleCrop())
                .into(imageView)
        }
    }

    override fun loadImageResource(
        iconResId: Int,
        imageView: ImageView,
        enableCircleCrop: Boolean
    ) {
        val glideApp = GlideApp.with(imageView.context)
            .load(iconResId)
            .dontAnimate()
            .listener(GlideLogger())
        if (enableCircleCrop)
            glideApp.circleCrop()
        glideApp.into(imageView)
    }

    override fun clearGlideRequest(imageView: ImageView) {
        val glideApp = GlideApp.with(imageView.context)
        glideApp.clear(imageView)
    }

    override fun loadVideoImageResources(
        uri: String?,
        imageView: ImageView,
        placeholderResource: Int,
        errorHandler: () -> Unit
    ) {
        if (uri.isNullOrBlank()) {
            loadImageResource(placeholderResource, imageView, true)
        } else {
            GlideApp.with(imageView.context)
                .load(Uri.parse(uri))
                .thumbnail(Glide.with(imageView.context).load(placeholderResource))
                .dontAnimate()
                .error(placeholderResource)
                .listener(ErrorGlideLogger(errorHandler))
                .transform(CircleCrop())
                .into(imageView)

            val glideApp1 = GlideApp.with(imageView.context)
            glideApp1.pauseRequests()
        }
    }

    companion object {
        private class ErrorGlideLogger<T>(val errorFunc: () -> Unit) : RequestListener<T> {
            override fun onResourceReady(
                resource: T,
                model: Any?,
                target: Target<T>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                Log.d(
                    "Glide",
                    "onResourceReady($resource, $model, $target, $dataSource, $isFirstResource"
                )
                return false
            }

            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<T>?,
                isFirstResource: Boolean
            ): Boolean {
                Log.d("Glide", "onLoadFailed($e, $model, $target, $isFirstResource)", e)
                errorFunc()
                return false
            }
        }
    }
}

fun ImageView.loadProfileImage(
    uri: String?,
    placeholder: Int = R.drawable.user_icon_black,
    errorHandler: () -> Unit = {}
) =
    ImageHelperProvider.imageHelper.loadProfileImage(uri, this, placeholder, errorHandler)

object ImageHelperProvider {
    var imageHelper: ImageHelper = GlideImageHelper()
}