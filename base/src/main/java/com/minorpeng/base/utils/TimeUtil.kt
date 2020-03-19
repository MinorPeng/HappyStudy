package com.minorpeng.base.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

/**
 *
 * @author MinorPeng
 * @date 2020/2/9 19:45
 */
object TimeUtil {

    fun getCurrentDay(): String {
        return getCurrentDayByFormat("yyyy-MM-dd")
    }

    fun getCurrentDayWithHMS(): String {
        return getCurrentDayByFormat("yyyy-MM-dd HH:mm:ss")
    }

    @SuppressLint("SimpleDateFormat")
    fun getCurrentDayByFormat(format: String): String {
        val date = Date(System.currentTimeMillis())
        val simpleDateFormat = SimpleDateFormat(format)
        return simpleDateFormat.format(date)
    }
}