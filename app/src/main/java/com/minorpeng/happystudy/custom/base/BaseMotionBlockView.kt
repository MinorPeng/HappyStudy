package com.minorpeng.happystudy.custom.base

import android.content.Context
import android.graphics.Canvas
import android.graphics.CornerPathEffect
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.minorpeng.base.utils.DensityUtil
import com.minorpeng.happystudy.R

/**
 *
 * @author MinorPeng
 * @date 2020/3/29 17:39
 */
abstract class BaseMotionBlockView : BaseBlockView {

    private val mDis2Left: Float
    private val mDis2Top: Float
    private val mLineLen: Float
    private val mPaint = Paint()

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        setWillNotDraw(false)
        mDis2Left = DensityUtil.dp2px(context, 10f).toFloat()
        mDis2Top = DensityUtil.dp2px(context, 4f).toFloat()
        mLineLen = DensityUtil.dp2px(context, 8f).toFloat()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(measuredWidth, measuredHeight + mDis2Top.toInt())
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            drawBackground(canvas)
        }
    }

    private fun drawBackground(canvas: Canvas) {
        val path = Path()
        path.moveTo(0f, 0f)
        path.lineTo(mDis2Left, 0f)
        path.lineTo(mDis2Left + mDis2Top, mDis2Top)
        path.lineTo(mDis2Left + mDis2Top + mLineLen, mDis2Top)
        path.lineTo(mDis2Left + mDis2Top * 2 + mLineLen, 0f)
        path.lineTo(measuredWidth.toFloat(), 0f)
        path.lineTo(measuredWidth.toFloat(), measuredHeight.toFloat() - mDis2Top)
        path.lineTo(mDis2Left + mDis2Top * 2 + mLineLen, measuredHeight.toFloat() - mDis2Top)
        path.lineTo(mDis2Left + mDis2Top + mLineLen, measuredHeight.toFloat())
        path.lineTo(mDis2Left + mDis2Top, measuredHeight.toFloat())
        path.lineTo(mDis2Left, measuredHeight.toFloat() - mDis2Top)
        path.lineTo(0f, measuredHeight.toFloat() - mDis2Top)
        path.lineTo(0f, 0f)
        mPaint.style = Paint.Style.FILL
        mPaint.color = ContextCompat.getColor(context, R.color.colorMotionBlue)
        mPaint.pathEffect = CornerPathEffect(4f)
        canvas.drawPath(path, mPaint)
    }
}