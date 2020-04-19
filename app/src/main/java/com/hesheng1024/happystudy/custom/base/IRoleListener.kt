package com.hesheng1024.happystudy.custom.base

import android.view.View

/**
 *
 * @author hesheng1024
 * @date 2020/3/24 18:06
 */
interface IRoleListener {

    suspend fun onRun(role: IRoleView)
}