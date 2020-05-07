package com.hesheng1024.happystudy.custom.blocks.base

import com.hesheng1024.happystudy.custom.role.IRoleView

/**
 *
 * @author hesheng1024
 * @date 2020/3/24 18:06
 */
interface IRoleListener {

    suspend fun onRun(role: IRoleView)
}