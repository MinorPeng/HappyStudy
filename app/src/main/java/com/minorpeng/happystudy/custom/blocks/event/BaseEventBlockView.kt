package com.minorpeng.happystudy.custom.blocks.event

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.minorpeng.base.utils.DensityUtil
import com.minorpeng.happystudy.R
import com.minorpeng.happystudy.custom.base.BaseBgBlockView

/**
 *
 * @author MinorPeng
 * @date 2020/3/31 10:15
 */
abstract class BaseEventBlockView(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : BaseBgBlockView(context, attrs, defStyleAttr, defStyleRes) {

    private val mDisTop = DensityUtil.dp2px(context, 16f).toFloat()

    init {
        this.setPadding((mDis2Top * 2).toInt(), (mDis2Top * 2 + mDisTop).toInt(), (mDis2Top * 2).toInt(), (mDis2Top * 3).toInt())
    }

    override fun drawBackground(canvas: Canvas) {
        val rectF = RectF(-mDisTop, 0f, measuredWidth - mLineLen, measuredWidth - mLineLen + mDisTop * 2)
        val path = Path()
        path.moveTo(0f, mDisTop)
        path.arcTo(rectF, -138f, 90f)
        path.lineTo(measuredWidth.toFloat(), mDisTop)
        path.lineTo(measuredWidth.toFloat(), measuredHeight - mDis2Top)
        path.lineTo(mDis2Left + mDis2Top * 2 + mLineLen, measuredHeight - mDis2Top)
        path.lineTo(mDis2Left + mDis2Top + mLineLen, measuredHeight.toFloat())
        path.lineTo(mDis2Left + mDis2Top, measuredHeight.toFloat())
        path.lineTo(mDis2Left, measuredHeight.toFloat() - mDis2Top)
        path.lineTo(0f, measuredHeight.toFloat() - mDis2Top)
        path.lineTo(0f, mDis2Top)
        mPaint.style = Paint.Style.FILL
        mPaint.color = ContextCompat.getColor(context, getBgColorId())
        mPaint.pathEffect = CornerPathEffect(mRadius)
        canvas.drawPath(path, mPaint)
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = mStrokeW
        mPaint.color = ContextCompat.getColor(context, android.R.color.darker_gray)
        canvas.drawPath(path, mPaint)
    }

    override fun getBgColorId(): Int {
        return R.color.colorEventYellow
    }
}