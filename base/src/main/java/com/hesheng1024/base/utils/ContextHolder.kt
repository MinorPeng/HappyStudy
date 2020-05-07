package com.hesheng1024.base.utils

import android.app.Application
import android.content.Context
import androidx.annotation.NonNull

/**
 *
 * @author hesheng1024
 * @date 2020/2/7 11:50
 */
object ContextHolder {
    private lateinit var sContext: Context
    private lateinit var sApplication: Application

    fun holdMainApplication(@NonNull application: Application) {
        sApplication = application
    }

    fun holdMainContext(@NonNull context: Context) {
        sContext = context
    }

    fun getMainContext(): Context = sContext

    fun getMainApplication(): Application = sApplication
}