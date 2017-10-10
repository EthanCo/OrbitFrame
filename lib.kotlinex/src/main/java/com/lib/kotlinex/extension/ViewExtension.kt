package com.lib.kotlinex.extension

import android.view.View

/**
 * View扩展类
 *
 * @author EthanCo
 * @since 2017/6/14
 */
fun View.setHeight(height: Int) {
    val params = layoutParams
    params.height = height
    layoutParams = params
}

fun View.visible() {
    visibility = View.VISIBLE
}
fun View.gone() {
    visibility = View.GONE
}