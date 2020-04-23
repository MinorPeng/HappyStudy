package com.hesheng1024.happystudy.custom.base

/**
 * 角色操作接口
 *
 * @author hesheng1024
 * @date 2020/4/15 10:14
 */
interface IRoleView {

    fun say(content: String)

    fun hide()

    fun show()

    fun nextBackground()


    fun moveStep(step: Int)

    fun moveToXY(x: Float, y: Float)

    fun decreaseX(x: Float)

    fun decreaseY(y: Float)

    fun increaseX(x: Float)

    fun increaseY(y: Float)

    fun setPX(x: Float)

    fun setPY(y: Float)

    fun setDirection(direction: Float)

    fun leftRotate(rotation: Float)

    fun rightRotate(rotation: Float)


    fun decreaseVolume(volume: Float)

    fun increaseVolume(volume: Float)

    fun playVoice()

    fun playVoice(rawId: Int)

    fun stopVoice()
}