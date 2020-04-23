package com.hesheng1024.base.utils

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.hesheng1024.base.BuildConfig
import java.text.SimpleDateFormat
import java.util.*

/**
 *
 * @author hesheng1024
 * @date 2020/4/20 8:44
 */

/*------------------------------ DensityUtil ------------------------------*/
fun dp2px(context: Context, dpValue: Float): Int {
    val scale = context.resources.displayMetrics.density
    return (dpValue * scale + 0.5f).toInt()
}

fun px2dp(context: Context, pxValue: Float): Int {
    val scale = context.resources.displayMetrics.density
    return (pxValue / scale + 0.5f).toInt()
}

fun px2sp(context: Context, pxValue: Float): Int {
    val fontScale = context.resources.displayMetrics.scaledDensity
    return (pxValue / fontScale + 0.5f).toInt()
}

fun sp2px(context: Context, spValue: Float): Int {
    val fontScale = context.resources.displayMetrics.scaledDensity
    return (spValue * fontScale + 0.5f).toInt()
}

/*------------------------------ TimeUtil ------------------------------*/
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

/*------------------------------ ToastUtil ------------------------------*/
fun toastShow(context: Context? = null, msg: String) {
    val ct = context ?: ContextHolder.getMainContext()
    Toast.makeText(ct, msg, Toast.LENGTH_SHORT).show()
}

fun toastShow(context: Context? = null, res: Int) {
    val ct = context ?: ContextHolder.getMainContext()
    Toast.makeText(ct, res, Toast.LENGTH_SHORT).show()
}

fun toastShowLong(context: Context? = null, msg: String) {
    val ct = context?: ContextHolder.getMainContext()
    Toast.makeText(ct, msg, Toast.LENGTH_LONG).show()
}

fun toastShowLong(context: Context? = null, res: Int) {
    val ct = context?: ContextHolder.getMainContext()
    Toast.makeText(ct, res, Toast.LENGTH_LONG).show()
}

/*------------------------------ LogUtil ------------------------------*/
fun logV(tag: String = getTag(), msg: String, tr: Throwable? = null) {
    if (BuildConfig.DEBUG) {
        tr?.let {
            Log.v(tag, msg, tr)
            return
        }
        Log.v(tag, msg)
    }
}

fun logD(tag: String = getTag(), msg: String, tr: Throwable? = null) {
    if (BuildConfig.DEBUG) {
        tr?.let {
            Log.d(tag, msg, tr)
            return
        }
        Log.d(tag, msg)
    }
}

fun logI(tag: String = getTag(), msg: String, tr: Throwable? = null) {
    tr?.let {
        Log.i(tag, msg, tr)
        return
    }
    Log.i(tag, msg)
}

fun logW(tag: String = getTag(), msg: String, tr: Throwable? = null) {
    tr?.let {
        Log.w(tag, msg, tr)
        return
    }
    Log.w(tag, msg)
}

fun logE(tag: String = getTag(), msg: String, tr: Throwable? = null) {
    tr?.let {
        Log.e(tag, msg, tr)
        return
    }
    Log.e(tag, msg)
}

private fun getTag(): String {
    val stackTraceElement = Throwable().stackTrace[2]
    val fileName = stackTraceElement.fileName
    val className = stackTraceElement.className
    val methodName = stackTraceElement.methodName
    val lineNum = stackTraceElement.lineNumber
    return className.substring(className.lastIndexOf('.') + 1) + "::$methodName($lineNum)"
}

