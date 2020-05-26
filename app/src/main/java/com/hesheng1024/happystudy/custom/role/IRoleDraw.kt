package com.hesheng1024.happystudy.custom.role

import android.graphics.Paint
import androidx.annotation.NonNull
import androidx.annotation.Nullable

/**
 *
 * @author hesheng1024
 * @date 2020/5/7 8:44
 */
interface IRoleDraw {

    /**
     *
     * @param name: name of the shape
     * @param cx: x of center point
     * @param cy: y of center point
     * @param r: radius
     * @param w: line width
     * @param color: circle color
     * @param style: stroke, fill, stroke with fill
     */
    fun drawCircle(cx: Float, cy: Float, r: Float, w: Float, color: Int, style: Paint.Style, name: String? = null)

    /**
     *
     * @param name: name of the shape
     * @param cx: x of center point
     * @param cy: y of center point
     * @param r: radius
     * @param color: circle color
     */
    fun drawPoint(cx: Float, cy: Float, r: Float, color: Int, name: String? = null)

    /**
     *
     * @param name: name of the shape
     * @param x1: x of left and top point
     * @param y1: y of left and top point
     * @param x2: x of right and bottom point
     * @param y2: y of right and bottom point
     * @param w: line width
     * @param color: arc color
     * @param style: stroke, fill, stroke with fill
     * @param rotation: rotation degree
     */
    fun drawRect(
        x1: Float, y1: Float, x2: Float, y2: Float, w: Float,
        color: Int, style: Paint.Style, name: String? = null, rotation: Float = 0f
    )

    /**
     *
     * @param name: name of the shape
     * @param startX: x of start point
     * @param startY: y of start point
     * @param endX: x of end point
     * @param endY: y of end point
     * @param w: line width
     * @param color: line color
     * @param rotation: rotation degree
     */
    fun drawLine(
        startX: Float, startY: Float, endX: Float, endY: Float,
        w: Float, color: Int, name: String? = null, rotation: Float = 0f
    )

    /**
     *
     * @param name: name of the shape
     * @param x1: x of left and bottom point
     * @param y1: y of left and bottom point
     * @param x2: x of right and bottom point
     * @param y2: y of right and bottom point
     * @param x3: x of top point
     * @param y3: y of top point
     * @param w: line width
     * @param color: triangle color
     * @param style: stroke, fill, stroke with fill
     * @param rotation: rotation degree
     */
    fun drawTriangle(
        x1: Float, y1: Float, x2: Float, y2: Float, x3: Float, y3: Float,
        w: Float, color: Int, style: Paint.Style, name: String? = null, rotation: Float = 0f
    )
}