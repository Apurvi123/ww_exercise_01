package com.weightwatchers.ww_exercise_01.ui.glide

import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

@GlideModule
class MyGlideApp : AppGlideModule() {
    // Needed for Glide LibraryGlideModule to work properly
    override fun isManifestParsingEnabled() = false
}