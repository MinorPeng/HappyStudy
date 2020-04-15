package com.hesheng1024.happystudy.custom.base

/**
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

    fun leftRotate(rotation: Float)

    fun rightRotate(rotation: Float)

    fun setX(x: Float)

    fun setY(y: Float)


    fun decreaseVolume(volume: Float)

    fun increaseVolume(volume: Float)

    fun playVoice()

    fun stopVoice()
}