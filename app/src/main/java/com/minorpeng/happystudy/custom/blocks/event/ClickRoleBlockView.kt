package com.minorpeng.happystudy.custom.blocks.event

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.minorpeng.base.utils.DensityUtil
import com.minorpeng.happystudy.R
import com.minorpeng.happystudy.custom.base.BaseTextBlockView

/**
 *
 * @author MinorPeng
 * @date 2020/3/30 21:44
 */
class ClickRoleBlockView(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    BaseTextBlockView(context, attrs, defStyleAttr) {

    private val mDisTop = DensityUtil.dp2px(context, 16f).toFloat()

    init {
        setBgColorId(R.color.colorEventYellow)
        this.setPadding((sDis2Top * 2).toInt(), (sDis2Top * 2 + mDisTop).toInt(), (sDis2Top * 2).toInt(), (sDis2Top * 3).toInt())
        setText(R.string.click_role)
    }

    override fun drawBackground(canvas: Canvas, paint: Paint, measuredW: Float, measuredH: Float) {
        val rectF = RectF(-mDisTop, 0f, measuredWidth - sLineLen, measuredWidth - sLineLen + mDisTop * 2)
        val path = Path()
        path.moveTo(0f, mDisTop)
        path.arcTo(rectF, -138f, 90f)
        path.lineTo(measuredW, mDisTop)
        path.lineTo(measuredW, measuredHeight - sDis2Top)
        path.lineTo(sDis2Left + sDis2Top * 2 + sLineLen, measuredHeight - sDis2Top)
        path.lineTo(sDis2Left + sDis2Top + sLineLen, measuredH)
        path.lineTo(sDis2Left + sDis2Top, measuredH)
        path.lineTo(sDis2Left, measuredH - sDis2Top)
        path.lineTo(0f, measuredH - sDis2Top)
        path.lineTo(0f, sDis2Top)
        paint.style = Paint.Style.FILL
        paint.color = getBgColor()
        paint.pathEffect = CornerPathEffect(sRadius)
        canvas.drawPath(path, paint)
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = sStrokeW
        paint.color = getBgBorderColor()
        canvas.drawPath(path, paint)
    }

    override fun onRun(role: View) {

    }
}