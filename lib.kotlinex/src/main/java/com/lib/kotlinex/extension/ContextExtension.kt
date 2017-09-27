package com.lib.kotlinex.extension

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Context扩展类
 *
 * @author EthanCo
 * @since 2017/6/14
 */
fun Context.inflate(res: Int, parent: ViewGroup? = null): View {
    return LayoutInflater.from(this).inflate(res, parent, false)
}