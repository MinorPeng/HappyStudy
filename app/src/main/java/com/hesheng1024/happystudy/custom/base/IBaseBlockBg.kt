package com.hesheng1024.happystudy.custom.base

import android.graphics.Canvas
import android.graphics.CornerPathEffect
import android.graphics.Paint
import android.graphics.Path
import com.hesheng1024.base.utils.ContextHolder
import com.hesheng1024.base.utils.DensityUtil

/**
 *
 * @author hesheng1024
 * @date 2020/4/11 10:57
 */
interface IBaseBlockBg {

    companion object {
        // 直接定义到接口中，可能会被实现类给覆盖重写，所以不规范，应当定义为静态
        const val sRadius: Float = 6f
        const val sStrokeW: Float = 2f
        val sDis2Left: Float = DensityUtil.dp2px(ContextHolder.getMainContext(), 10f).toFloat()
        val sDis2Top: Float =  DensityUtil.dp2px(ContextHolder.getMainContext(), 4f).toFloat()
        val sLineLen: Float = DensityUtil.dp2px(ContextHolder.getMainContext(), 12f).toFloat()
    }

    fun drawBackground(canvas: Canvas, paint: Paint, path: Path, measuredW: Float, measuredH: Float) {
        path.reset()
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

    fun setStatus(status: Status)

    fun getStatus(): Status

    fun clone(): IBaseBlockBg

    enum class Status {
        /**
         * 既drag又clone自身
         */
        STATUS_CLONE,

        /**
         * 只drag不clone
         */
        STATUS_DRAG
    }
}