package com.minorpeng.happystudy.custom.base

import android.graphics.*
import com.minorpeng.base.utils.ContextHolder
import com.minorpeng.base.utils.DensityUtil

/**
 *
 * @author MinorPeng
 * @date 2020/4/11 10:57
 */
interface IBaseBlockBg {
    val sDis2Left: Float
        get() = DensityUtil.dp2px(ContextHolder.getMainContext(), 10f).toFloat()
    val sDis2Top: Float
        get() = DensityUtil.dp2px(ContextHolder.getMainContext(), 4f).toFloat()
    val sLineLen: Float
        get() = DensityUtil.dp2px(ContextHolder.getMainContext(), 12f).toFloat()
    val sRadius: Float
        get() = 6f
    val sStrokeW: Float
        get() = 2f

    fun drawBackground(canvas: Canvas, paint: Paint, measuredW: Float, measuredH: Float) {
        val path = Path()
        path.moveTo(0f, 0f)
        path.lineTo(sDis2Left, 0f)
        path.lineTo(sDis2Left + sDis2Top, sDis2Top)
        path.lineTo(sDis2Left + sDis2Top + sLineLen, sDis2Top)
        path.lineTo(sDis2Left + sDis2Top * 2 + sLineLen, 0f)
        path.lineTo(measuredW, 0f)
        path.lineTo(measuredW, measuredH - sDis2Top)
        path.lineTo(sDis2Left + sDis2Top * 2 + sLineLen, measuredH - sDis2Top)
        path.lineTo(sDis2Left + sDis2Top + sLineLen, measuredH)
        path.lineTo(sDis2Left + sDis2Top, measuredH)
        path.lineTo(sDis2Left, measuredH - sDis2Top)
        path.lineTo(0f, measuredH - sDis2Top)
        path.lineTo(0f, 0f)
        paint.style = Paint.Style.FILL
        paint.color = getBgColor()
        paint.pathEffect = CornerPathEffect(sRadius)
        canvas.drawPath(path, paint)
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = sStrokeW
        paint.color = getBgBorderColor()
        canvas.drawPath(path, paint)
    }

    fun getBgColor(): Int

    fun getBgBorderColor(): Int
}