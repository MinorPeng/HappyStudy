package com.minorpeng.happystudy.custom.base

import android.content.Context
import android.graphics.Canvas
import android.graphics.CornerPathEffect
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.Gravity
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.minorpeng.base.utils.DensityUtil

/**
 *
 * @author MinorPeng
 * @date 2020/3/29 19:40
 */
abstract class BaseTextBlockView(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    AppCompatTextView(context, attrs, defStyleAttr), IRoleListener {

    protected val mDis2Left = DensityUtil.dp2px(context, 10f).toFloat()
    protected val mDis2Top = DensityUtil.dp2px(context, 4f).toFloat()
    protected val mLineLen = DensityUtil.dp2px(context, 12f).toFloat()
    protected val mRadius = 6f
    protected val mPaint = Paint()
    private val mStrokeW = 2f
    private var mLastX: Float = 0f
    private var mLastY: Float = 0f
    private var mCanMove = false

    init {
        gravity = Gravity.CENTER
        this.setPadding((mDis2Top * 2).toInt(), (mDis2Top * 2).toInt(), (mDis2Top * 2).toInt(), (mDis2Top * 2).toInt())
        this.setTextColor(ContextCompat.getColor(context, android.R.color.white))
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.let {
            drawBackground(canvas)
        }
        super.onDraw(canvas)
    }

    protected open fun drawBackground(canvas: Canvas) {
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
        mPaint.color = ContextCompat.getColor(context, getBgColorId())
        mPaint.pathEffect = CornerPathEffect(mRadius)
        canvas.drawPath(path, mPaint)
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = mStrokeW
        mPaint.color = ContextCompat.getColor(context, android.R.color.darker_gray)
        canvas.drawPath(path, mPaint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    mLastX = event.x
                    mLastY = event.y
                    return true
                }
                MotionEvent.ACTION_MOVE -> {
                    if (mCanMove) {
                        //通过ViewParent去重新绘制子view
                        offsetLeftAndRight((event.x - mLastX).toInt())
                        offsetTopAndBottom((event.y - mLastY).toInt())
                        return true
                    }
                }
            }
        }
        return super.onTouchEvent(event)
    }

    fun setMoved(moved: Boolean) {
        this.mCanMove = moved
    }

    abstract fun getBgColorId(): Int
}