package com.minorpeng.happystudy.custom.blocks.calculate

import android.content.Context
import android.graphics.Canvas
import android.graphics.CornerPathEffect
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.minorpeng.happystudy.R
import com.minorpeng.happystudy.custom.base.BaseBgBlockView

/**
 *
 * @author MinorPeng
 * @date 2020/4/3 15:43
 */
abstract class BaseLogicBlockView(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : BaseBgBlockView(context, attrs, defStyleAttr, defStyleRes) {
    protected val mDisLeft = mDis2Left + mDis2Top

    init {
        this.setPadding(mDisLeft.toInt(), mDis2Top.toInt(), mDisLeft.toInt(), mDis2Top.toInt())
    }

    override fun drawBackground(canvas: Canvas) {
        val path = Path()
        path.moveTo(0f, measuredHeight / 2f)
        path.lineTo(mDisLeft, 0f)
        path.lineTo(measuredWidth - mDisLeft, 0f)
        path.lineTo(measuredWidth.toFloat(), measuredHeight / 2f)
        path.lineTo(measuredWidth - mDisLeft, measuredHeight.toFloat())
        path.lineTo(mDisLeft, measuredHeight.toFloat())
        path.close()

        mPaint.style = Paint.Style.FILL
        mPaint.color = ContextCompat.getColor(context, mBgColorId)
        mPaint.pathEffect = CornerPathEffect(mRadius)
        canvas.drawPath(path, mPaint)
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = mStrokeW
        mPaint.color = ContextCompat.getColor(context, android.R.color.darker_gray)
        canvas.drawPath(path, mPaint)
    }

    override fun getBgColorId(): Int {
        return R.color.colorCalculateGreen
    }
}