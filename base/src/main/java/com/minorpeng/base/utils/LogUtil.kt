package com.minorpeng.base.utils

import android.util.Log
import com.minorpeng.base.BuildConfig

/**
 *
 * @author MinorPeng
 * @date 2020/2/7 11:32
 */
object LogUtil {

    fun v(tag: String, msg: String, tr: Throwable?) {
        if (BuildConfig.DEBUG) {
            tr?.let {
                Log.v(tag, msg, tr)
                return
            }
            Log.v(tag, msg)
        }
    }

    fun v(msg: String) {
        v(getTag(), msg, null)
    }

    fun d(tag: String, msg: String, tr: Throwable?) {
        if (BuildConfig.DEBUG) {
            tr?.let {
                Log.d(tag, msg, tr)
                return
            }
            Log.d(tag, msg)
        }
    }

    fun d(msg: String) {
        d(getTag(), msg, null)
    }

    fun i(tag: String, msg: String, tr: Throwable?) {
        tr?.let {
            Log.i(tag, msg, tr)
            return
        }
        Log.i(tag, msg)
    }

    fun i(msg: String) {
        i(getTag(), msg, null)
    }

    fun w(tag: String, msg: String, tr: Throwable?) {
        tr?.let {
            Log.w(tag, msg, tr)
            return
        }
        Log.w(tag, msg)
    }

    fun w(msg: String) {
        w(getTag(), msg, null)
    }

    fun e(tag: String, msg: String, tr: Throwable?) {
        tr?.let {
            Log.e(tag, msg, tr)
            return
        }
        Log.e(tag, msg)
    }

    fun e(msg: String) {
        e(getTag(), msg, null)
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
