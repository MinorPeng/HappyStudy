package com.minorpeng.happystudy.custom.blocks.calculate

import android.content.Context
import android.graphics.Canvas
import android.graphics.CornerPathEffect
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.minorpeng.happystudy.R
import com.minorpeng.happystudy.custom.base.BaseBgBlockView

/**
 *
 * @author MinorPeng
 * @date 2020/4/3 16:36
 */
abstract class BaseCalculateBlockView : BaseBgBlockView {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        this.setPadding(mDis2Top.toInt(), mDis2Top.toInt(), mDis2Top.toInt(), mDis2Top.toInt())
    }

    override fun drawBackground(canvas: Canvas) {
        val path = Path()
        path.arcTo(0f, 0f, measuredHeight.toFloat(), measuredHeight.toFloat(),
            90f, 180f, false)
        path.lineTo(measuredWidth - measuredHeight.toFloat() / 2, 0f)
        path.arcTo(measuredWidth - measuredHeight.toFloat(), 0f, measuredWidth.toFloat(), measuredHeight.toFloat(),
            -90f, 180f, false)
        path.close()

        mPaint.style = Paint.Style.FILL
        mPaint.color = ContextCompat.getColor(context, getBgColorId())
        mPaint.pathEffect = CornerPathEffect(mRadius)
        canvas.drawPath(path, mPaint)
    }

    override fun getBgColorId(): Int {
        return R.color.colorCalculateGreen
    }
}