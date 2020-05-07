package com.hesheng1024.happystudy.custom.role

/**
 *
 * @author hesheng1024
 * @date 2020/5/7 8:46
 */
interface IRoleMotion {

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
}