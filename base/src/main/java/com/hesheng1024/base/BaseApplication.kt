package com.hesheng1024.base

import android.app.Application
import android.content.Context
import android.util.Log
import com.hesheng1024.base.utils.ContextHolder

/**
 *
 * @author hesheng1024
 * @date 2020/2/7 10:38
 */
abstract class BaseApplication : Application() {


    companion object {
        private const val TAG = "BackupApplication"
    }


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        Log.i(TAG, "startTime: ${System.currentTimeMillis()}")
    }

    override fun onCreate() {
        super.onCreate()
        ContextHolder.holdMainApplication(this)
        ContextHolder.holdMainContext(baseContext)
    }
}