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
 * @date 2020/4/3 16:36
 */
abstract class BaseCalculateBlockView : BaseBgBlockView {
    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        setBgColorId(R.color.colorCalculateGreen)
        this.setPadding(
            IBaseBlockBg.sDis2Top.toInt(),
            IBaseBlockBg.sDis2Top.toInt(),
            IBaseBlockBg.sDis2Top.toInt(),
            IBaseBlockBg.sDis2Top.toInt()
        )
    }

    override fun drawBackground(canvas: Canvas, paint: Paint, path: Path, measuredW: Float, measuredH: Float) {
        path.reset()
        path.arcTo(0f, 0f, measuredH, measuredH, 90f, 180f, false)
        path.lineTo(measuredW - measuredH / 2, 0f)
        path.arcTo(measuredW - measuredH, 0f, measuredW, measuredH, -90f, 180f, false)
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