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
 * @date 2020/4/3 15:43
 */
abstract class BaseLogicBlockView : BaseBgBlockView {
    private val mDisLeft = mDis2Left + mDis2Top

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
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
        mPaint.color = ContextCompat.getColor(context, getBgColorId())
        mPaint.pathEffect = CornerPathEffect(mRadius)
        canvas.drawPath(path, mPaint)
    }

    override fun getBgColorId(): Int {
        return R.color.colorCalculateGreen
    }
}