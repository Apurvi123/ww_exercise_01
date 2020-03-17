package com.weightwatchers.ww_exercise_01.utils

import com.weightwatchers.ww_exercise_01.model.Message

fun Message.Companion.test(
    title: String = "title".appendRandom(),
    image: String? = "imageUrl".appendRandom(),
    filter: String = "filter".appendRandom()
) = Message(title = title, image = image, filter = filter)