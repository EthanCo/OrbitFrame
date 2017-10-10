package com.lib.kotlinex.extension

import android.content.Context
import android.widget.Toast

/**
 * Toast 扩展
 *
 * @author EthanCo
 * @since 2017/6/29
 */
fun String.toast(context: Context,duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, this, duration).show()
}