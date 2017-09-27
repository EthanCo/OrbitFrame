package com.lib.kotlinex.extension

import android.content.Context
import android.support.v4.app.Fragment
import android.widget.Toast

/**
 * Toast 扩展
 *
 * @author EthanCo
 * @since 2017/6/29
 */
fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Fragment.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(activity, message, duration).show()
}

fun android.app.Fragment.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(activity, message, duration).show()
}

fun Context.toast(messageRes: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, messageRes, duration).show()
}

fun Fragment.toast(messageRes: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(activity, messageRes, duration).show()
}

fun android.app.Fragment.toast(messageRes: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(activity, messageRes, duration).show()
}
