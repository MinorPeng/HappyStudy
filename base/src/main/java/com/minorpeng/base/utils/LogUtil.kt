package com.minorpeng.base.utils

import android.util.Log
import com.minorpeng.base.BuildConfig

/**
 *
 * @author MinorPeng
 * @date 2020/2/7 11:32
 */
object LogUtil {

    fun v(tag: String = getTag(), msg: String, tr: Throwable? = null) {
        if (BuildConfig.DEBUG) {
            tr?.let {
                Log.v(tag, msg, tr)
                return
            }
            Log.v(tag, msg)
        }
    }

    fun d(tag: String = getTag(), msg: String, tr: Throwable? = null) {
        if (BuildConfig.DEBUG) {
            tr?.let {
                Log.d(tag, msg, tr)
                return
            }
            Log.d(tag, msg)
        }
    }

    fun i(tag: String = getTag(), msg: String, tr: Throwable? = null) {
        tr?.let {
            Log.i(tag, msg, tr)
            return
        }
        Log.i(tag, msg)
    }

    fun w(tag: String = getTag(), msg: String, tr: Throwable? = null) {
        tr?.let {
            Log.w(tag, msg, tr)
            return
        }
        Log.w(tag, msg)
    }

    fun e(tag: String = getTag(), msg: String, tr: Throwable? = null) {
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
        return className.substring(className.lastIndexOf('.') + 1) + ":" + lineNum
    }
}
