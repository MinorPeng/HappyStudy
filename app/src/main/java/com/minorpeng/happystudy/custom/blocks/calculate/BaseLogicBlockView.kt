package com.minorpeng.happystudy.custom.blocks.calculate

import android.content.Context
import android.graphics.Canvas
import android.graphics.CornerPathEffect
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import com.minorpeng.happystudy.R
import com.minorpeng.happystudy.custom.base.BaseBgBlockView
import com.minorpeng.happystudy.custom.base.IBaseBlockBg

/**
 *
 * @author MinorPeng
 * @date 2020/4/3 15:43
 */
abstract class BaseLogicBlockView : BaseBgBlockView {
    protected val sDisLeft = IBaseBlockBg.sDis2Left + IBaseBlockBg.sDis2Top

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        setBgColorId(R.color.colorCalculateGreen)
        this.setPadding(sDisLeft.toInt(), IBaseBlockBg.sDis2Top.toInt(), sDisLeft.toInt(), IBaseBlockBg.sDis2Top.toInt())
    }

    override fun drawBackground(canvas: Canvas, paint: Paint, path: Path, measuredW: Float, measuredH: Float) {
        path.reset()
        path.moveTo(0f, measuredH / 2f)
        path.lineTo(sDisLeft, 0f)
        path.lineTo(measuredW - sDisLeft, 0f)
        path.lineTo(measuredW, measuredH / 2f)
        path.lineTo(measuredW - sDisLeft, measuredH)
        path.lineTo(sDisLeft, measuredH)
        path.close()

        paint.style = Paint.Style.FILL
        paint.color = getBgColor()
        paint.pathEffect = CornerPathEffect(IBaseBlockBg.sRadius)
        canvas.drawPath(path, paint)
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = IBaseBlockBg.sStrokeW
        paint.color = getBgBorderColor()
        canvas.drawPath(path, paint)
    }
}