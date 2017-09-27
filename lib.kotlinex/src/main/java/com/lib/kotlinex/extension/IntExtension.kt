package com.lib.kotlinex.extension

import android.view.ViewGroup
import com.lib.kotlinex.KotlinGlobal

/**
 * Int 扩展类
 *
 * @author EthanCo
 * @since 2017/6/1
 */
fun Int.dpToPx(): Int {
    if (toInt() in intArrayOf(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)) {
        return this
    }

    return (this * KotlinGlobal.density + 0.5f).toInt()
}

fun Int.pxToDp(): Int {
    if (toInt() in intArrayOf(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)) {
        return this
    }

    return (this / KotlinGlobal.density + 0.5f).toInt()
}