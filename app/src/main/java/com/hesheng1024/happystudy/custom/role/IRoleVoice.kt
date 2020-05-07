package com.hesheng1024.happystudy.custom.role

/**
 *
 * @author hesheng1024
 * @date 2020/5/7 8:51
 */
interface IRoleVoice {

    fun decreaseVolume(volume: Float)

    fun increaseVolume(volume: Float)

    fun playVoice()

    fun playVoice(rawId: Int)

    fun stopVoice()
}