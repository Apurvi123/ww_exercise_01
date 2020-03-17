package com.weightwatchers.ww_exercise_01.utils

import java.util.*

private val random = Random()

fun String.appendRandom() = "$this-${random.nextInt(10000)}"