package com.lib.kotlinex.extension

import com.lib.kotlinex.KotlinGlobal

/**
 * Object 扩展
 *
 * @author EthanCo
 * @since 2017/6/30
 */
fun Any.getString(stringId: Int): String {
    return KotlinGlobal.context.getString(stringId)
}