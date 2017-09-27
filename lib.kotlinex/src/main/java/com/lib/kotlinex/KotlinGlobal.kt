package com.lib.kotlinex

import android.content.Context

/**
 * 全局参数 - 程序启动时初始化
 *
 * @author EthanCo
 * @since 2017/5/22
 */
object KotlinGlobal {
    var density: Float = 1F
    lateinit var context: Context

    fun init(_context: Context) {
        context = _context
        density = _context.resources.displayMetrics.density
    }
}