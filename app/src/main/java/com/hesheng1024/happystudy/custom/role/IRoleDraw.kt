package com.hesheng1024.happystudy.custom.role

import android.graphics.Paint
import androidx.annotation.NonNull

/**
 *
 * @author hesheng1024
 * @date 2020/5/7 8:44
 */
interface IRoleDraw {

    /**
     *
     * @param cx: x of center point
     * @param cy: y of center point
     * @param r: radius
     * @param w: line width
     * @param color: circle color
     * @param style: stroke, fill, stroke with fill
     */
    fun drawCircle(
        @NonNull cx: Float, @NonNull cy: Float, @NonNull r: Float,
        @NonNull w: Float, @NonNull color: Int, @NonNull style: Paint.Style
    )

    /**
     *
     * @param cx: x of center point
     * @param cy: y of center point
     * @param r: radius
     * @param color: circle color
     */
    fun drawPoint(@NonNull cx: Float, @NonNull cy: Float, @NonNull r: Float, @NonNull color: Int)

    /**
     *
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
        @NonNull x1: Float, @NonNull y1: Float,
        @NonNull x2: Float, @NonNull y2: Float,
        @NonNull w: Float, @NonNull color: Int, @NonNull style: Paint.Style, @NonNull rotation: Float = 0f
    )

    /**
     *
     * @param startX: x of start point
     * @param startY: y of start point
     * @param endX: x of end point
     * @param endY: y of end point
     * @param w: line width
     * @param color: line color
     * @param rotation: rotation degree
     */
    fun drawLine(
        @NonNull startX: Float, @NonNull startY: Float,
        @NonNull endX: Float, @NonNull endY: Float,
        @NonNull w: Float, @NonNull color: Int, @NonNull rotation: Float = 0f
    )

    /**
     *
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
        @NonNull x1: Float, @NonNull y1: Float,
        @NonNull x2: Float, @NonNull y2: Float,
        @NonNull x3: Float, @NonNull y3: Float,
        @NonNull w: Float, @NonNull color: Int, @NonNull style: Paint.Style, @NonNull rotation: Float = 0f
    )
}