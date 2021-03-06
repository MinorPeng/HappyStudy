package com.hesheng1024.happystudy.custom.blocks.calculate

import android.content.Context
import android.graphics.Canvas
import android.graphics.CornerPathEffect
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.custom.blocks.base.BaseLinearBlockView
import com.hesheng1024.happystudy.custom.blocks.base.IBaseBlock

/**
 *
 * @author hesheng1024
 * @date 2020/4/3 15:43
 */
abstract class BaseLogicBlockView : BaseLinearBlockView {
    protected val sDisLeft = IBaseBlock.DIS_TO_LEFT + IBaseBlock.DIS_TO_TOP

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        this.setBgColorId(R.color.colorCalculate500)
        this.setPadding(sDisLeft.toInt(), IBaseBlock.DIS_TO_TOP.toInt(), sDisLeft.toInt(), IBaseBlock.DIS_TO_TOP.toInt())
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

        canvas.clipPath(path)
        paint.style = Paint.Style.FILL
        paint.color = getBgColor()
        paint.pathEffect = CornerPathEffect(IBaseBlock.sRadius)
        canvas.drawPath(path, paint)
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = IBaseBlock.sStrokeW
        paint.color = getBgBorderColor()
        canvas.drawPath(path, paint)
    }

    abstract suspend fun judgeResult(): Boolean
}