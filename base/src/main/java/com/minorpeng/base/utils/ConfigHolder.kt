package com.minorpeng.base.utils

/**
 *
 * @author MinorPeng
 * @date 2020/2/11 21:39
 */
object ConfigHolder {

    val sRootCacheFile: String =
        if (ContextHolder.getMainContext().externalCacheDir != null)
            ContextHolder.getMainContext().externalCacheDir.toString()
        else ContextHolder.getMainContext().cacheDir.toString()

    val sRootExternalFile: String =
        if (ContextHolder.getMainContext().getExternalFilesDir(null) != null)
            ContextHolder.getMainContext().getExternalFilesDir(null)!!.path
        else ContextHolder.getMainContext().filesDir.path

}