package com.lib.kotlinex.extension

import com.google.gson.Gson

/**
 * Gson 扩展
 *
 * @author EthanCo
 * @since 2017/6/28
 */
//无需再传泛型
inline fun <reified T> Gson.fromJson(json: String): T {
    return fromJson(json, T::class.java)
}