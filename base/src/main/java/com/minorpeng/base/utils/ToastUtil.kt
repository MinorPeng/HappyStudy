package com.minorpeng.base.utils

import android.content.Context
import android.widget.Toast

/**
 *
 * @author MinorPeng
 * @date 2020/2/7 14:14
 */
object ToastUtil {

    fun show(context: Context?, msg: String) {
        context?.let {
            Toast.makeText(it, msg, Toast.LENGTH_SHORT).show()
            return
        }
        show(msg)
    }

    fun show(msg: String) {
        show(ContextHolder.getMainContext(), msg)
    }

    fun show(context: Context?, res: Int) {
        context?.let {
            Toast.makeText(it, res, Toast.LENGTH_SHORT).show()
            return
        }
        show(res)
    }

    fun show(res: Int) {
        show(ContextHolder.getMainContext(), res)
    }

    fun showLong(context: Context?, msg: String) {
        context?.let {
            Toast.makeText(it, msg, Toast.LENGTH_LONG).show()
            return
        }
        showLong(msg)
    }

    fun showLong(msg: String) {
        showLong(ContextHolder.getMainContext(), msg)
    }

    fun showLong(context: Context?, res: Int) {
        context?.let {
            Toast.makeText(it, res, Toast.LENGTH_LONG).show()
            return
        }
        showLong(res)
    }

    fun showLong(res: Int) {
        showLong(ContextHolder.getMainContext(), res)
    }
}