package com.minorpeng.base.utils

import android.content.Context
import android.widget.Toast

/**
 *
 * @author MinorPeng
 * @date 2020/2/7 14:14
 */
object ToastUtil {
    fun show(context: Context? = null, msg: String) {
        context?.let {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
            return
        }
        Toast.makeText(ContextHolder.getMainContext(), msg, Toast.LENGTH_SHORT).show()
    }

    fun show(context: Context? = null, res: Int) {
        context?.let {
            Toast.makeText(context, res, Toast.LENGTH_SHORT).show()
            return
        }
        Toast.makeText(ContextHolder.getMainContext(), res, Toast.LENGTH_SHORT).show()
    }

    fun showLong(context: Context? = null, msg: String) {
        context?.let {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
            return
        }
        Toast.makeText(ContextHolder.getMainContext(), msg, Toast.LENGTH_LONG).show()
    }

    fun showLong(context: Context? = null, res: Int) {
        context?.let {
            Toast.makeText(context, res, Toast.LENGTH_LONG).show()
            return
        }
        Toast.makeText(ContextHolder.getMainContext(), res, Toast.LENGTH_LONG).show()
    }

}