package com.jack.shadowview

import android.content.Context
import android.content.res.Resources
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.FloatRange
import androidx.fragment.app.Fragment
import kotlin.math.roundToInt

fun Context.dip(value: Int): Int = dipF(value).toInt()

fun Context.dipF(value: Float): Float = value * resources.displayMetrics.density
fun Context.dipF(value: Int): Float = value * resources.displayMetrics.density

fun dipF(value: Int): Float = value * Resources.getSystem().displayMetrics.density

fun View.dip(value: Int): Int = context.dip(value)
fun View.dipF(value: Int): Float = context.dipF(value)

fun Fragment.dip(value: Int): Int = requireContext().dip(value)
fun Fragment.dipF(value: Int): Float = requireContext().dipF(value)

fun colorAlpha(@ColorInt color: Int, alpha: Int): Int {
    return (color and 0x00ffffff) or (alpha shl 24)
}

fun colorAlpha(@ColorInt color: Int, @FloatRange(from = 0.0, to = 1.0) percent: Float): Int {
    return colorAlpha(color, (255 * percent).roundToInt())
}