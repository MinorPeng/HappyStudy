package com.hesheng1024.happystudy.custom.blocks.event

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import com.hesheng1024.base.utils.DensityUtil
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.custom.base.BaseBgBlockView
import com.hesheng1024.happystudy.custom.base.IBaseBlock

/**
 *
 * @author hesheng1024
 * @date 2020/3/31 10:15
 */
abstract class BaseEventBlockView : BaseBgBlockView {

    private val mDisTop = DensityUtil.dp2px(context, 16f).toFloat()

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        setBgColorId(R.color.colorControlYellow)
        setBgColorId(R.color.colorEventYellow)
        this.setPadding(
            (IBaseBlock.sDis2Top * 2).toInt(),
            (IBaseBlock.sDis2Top * 2 + mDisTop).toInt(),
            (IBaseBlock.sDis2Top * 2).toInt(),
            (IBaseBlock.sDis2Top * 3).toInt()
        )
    }

    override fun drawBackground(canvas: Canvas, paint: Paint, path: Path, measuredW: Float, measuredH: Float) {
        val rectF = RectF(-mDisTop, 0f, measuredW - IBaseBlock.sLineLen, measuredW - IBaseBlock.sLineLen + mDisTop * 2)
        path.reset()
        path.moveTo(0f, mDisTop)
        path.arcTo(rectF, -138f, 90f)
        path.lineTo(measuredW, mDisTop)
        path.lineTo(measuredW, measuredH - IBaseBlock.sDis2Top)
        path.lineTo(IBaseBlock.sDis2Left + IBaseBlock.sDis2Top * 2 + IBaseBlock.sLineLen, measuredH - IBaseBlock.sDis2Top)
        path.lineTo(IBaseBlock.sDis2Left + IBaseBlock.sDis2Top + IBaseBlock.sLineLen, measuredH)
        path.lineTo(IBaseBlock.sDis2Left + IBaseBlock.sDis2Top, measuredH)
        path.lineTo(IBaseBlock.sDis2Left, measuredH - IBaseBlock.sDis2Top)
        path.lineTo(0f, measuredH - IBaseBlock.sDis2Top)
        path.lineTo(0f, IBaseBlock.sDis2Top)
        paint.style = Paint.Style.FILL
        paint.color = getBgColor()
        paint.pathEffect = CornerPathEffect(IBaseBlock.sRadius)
        canvas.drawPath(path, paint)
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = IBaseBlock.sStrokeW
        paint.color = getBgBorderColor()
        canvas.drawPath(path, paint)
    }
}