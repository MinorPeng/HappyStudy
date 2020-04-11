package com.minorpeng.happystudy.custom.blocks.control

import android.content.Context
import android.graphics.Canvas
import android.graphics.CornerPathEffect
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.minorpeng.base.utils.DensityUtil
import com.minorpeng.happystudy.R
import com.minorpeng.happystudy.custom.base.BaseBlockViewGroup
import kotlin.math.max

/**
 *
 * @author MinorPeng
 * @date 2020/4/4 17:06
 */
abstract class BaseControlBlockView(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : BaseBlockViewGroup(context, attrs, defStyleAttr, defStyleRes) {

    private val mDis2Left = DensityUtil.dp2px(context, 10f).toFloat()
    private val mDis2Top = DensityUtil.dp2px(context, 4f).toFloat()
    private val mLineLen = DensityUtil.dp2px(context, 12f).toFloat()
    private val mRadius = 6f
    private val mPaint = Paint()
    private val mStrokeW = 2f
    private var mTopViewH = DensityUtil.dp2px(context, 32f).toFloat()
    private var mTopViewW = DensityUtil.dp2px(context, 150f).toFloat()

    init {
        this.setWillNotDraw(false)
        this.setPadding((mDis2Top * 2).toInt(), mDis2Top.toInt(), (mDis2Top * 2).toInt(), (mDis2Top * 2).toInt())
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val sizeW = MeasureSpec.getSize(widthMeasureSpec)
        val modeW = MeasureSpec.getMode(widthMeasureSpec)
        val sizeH = MeasureSpec.getSize(heightMeasureSpec)
        val modeH = MeasureSpec.getMode(heightMeasureSpec)

        var topViewW = 0
        var topViewMaxH = 0
        var centerMaxW = 0
        var centerViewH = 0
        for (index in 0 until childCount) {
            val child = getChildAt(index)
            measureChild(child, widthMeasureSpec, heightMeasureSpec)
            val childLp = child.layoutParams as MarginLayoutParams
            when(child.tag) {
                ChildTag.TAG_TOP -> {
                    val childWidth = child.measuredWidth + childLp.leftMargin + childLp.rightMargin
                    val childHeight = child.measuredHeight + childLp.topMargin + childLp.bottomMargin
                    // top view width sum
                    topViewW += childWidth
                    // top view max height
                    topViewMaxH = max(topViewMaxH, childHeight)
                }
                ChildTag.TAG_CHILD -> {
                    val childWidth = child.measuredWidth + childLp.leftMargin + childLp.rightMargin
                    val childHeight = child.measuredHeight + childLp.topMargin + childLp.bottomMargin - mDis2Top.toInt()
                    // center view max width
                    centerMaxW = max(centerMaxW, childWidth)
                    // center view height sum
                    centerViewH += childHeight
                }
                ChildTag.TAG_BOTTOM -> {
                    val childHeight = child.measuredHeight + childLp.topMargin + childLp.bottomMargin
                    topViewMaxH = max(topViewMaxH, childHeight)
                }
                else -> {

                }
            }
        }
        mTopViewW = max((topViewW + paddingLeft + paddingRight).toFloat(), mTopViewW)
        mTopViewH = max(topViewMaxH + paddingTop + paddingBottom - mDis2Top, mTopViewH)
        centerViewH = if (centerViewH == 0) mTopViewH.toInt() else centerViewH
        var width = max(mTopViewW, centerMaxW + mDis2Left).toInt()
        var height = centerViewH + mTopViewH.toInt() * 2 + mDis2Top.toInt()

        width = if (modeW == MeasureSpec.EXACTLY) sizeW else width
        height = if (modeH == MeasureSpec.EXACTLY) sizeH else height
        setMeasuredDimension(width, height)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var topL = paddingLeft
        var centerT = mTopViewH.toInt()
        for (index in 0 until childCount) {
            val child = getChildAt(index)
            if (child.visibility == View.GONE) {
                continue
            }
            val childLp = child.layoutParams as MarginLayoutParams
            var childL = 0
            var childT = 0
            var childR = 0
            var childB = 0
            when(child.tag) {
                ChildTag.TAG_TOP -> {
                    childL = topL + childLp.leftMargin
                    childT = (mTopViewH.toInt() - child.measuredHeight) / 2 + childLp.topMargin
                    childR = childL + child.measuredWidth
                    childB = childT + child.measuredHeight
                    topL += child.measuredWidth + childLp.leftMargin + childLp.rightMargin
                }
                ChildTag.TAG_CHILD -> {
                    childL = mDis2Left.toInt() + childLp.leftMargin
                    childT = centerT + childLp.topMargin
                    childR = childL + child.measuredWidth
                    childB = childT + child.measuredHeight
                    centerT += child.measuredHeight + childLp.topMargin + childLp.bottomMargin
                }
                ChildTag.TAG_BOTTOM -> {
                    // layout from right to left
                    childL = mTopViewW.toInt() - child.measuredWidth - childLp.rightMargin - paddingRight
                    childT = measuredHeight - (mTopViewH.toInt() - child.measuredHeight) / 2 - child.measuredHeight -
                            mDis2Top.toInt()
                    childR = childL + child.measuredWidth
                    childB = childT + child.measuredHeight
                }
                else -> {

                }
            }
            child.layout(childL, childT, childR, childB)
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            drawBackground(canvas)
        }
    }

    private fun drawBackground(canvas: Canvas) {
        val path = Path()
        // top
        path.moveTo(0f, 0f)
        path.lineTo(mDis2Left, 0f)
        path.lineTo(mDis2Left + mDis2Top, mDis2Top)
        path.lineTo(mDis2Left + mDis2Top + mLineLen, mDis2Top)
        path.lineTo(mDis2Left + mDis2Top * 2 + mLineLen, 0f)
        path.lineTo(mTopViewW, 0f)

        path.lineTo(mTopViewW, mTopViewH)
        path.lineTo(mDis2Left * 2 + mDis2Top * 2 + mLineLen, mTopViewH)
        path.lineTo(mDis2Left * 2 + mDis2Top + mLineLen, mTopViewH + mDis2Top)
        path.lineTo(mDis2Left * 2 + mDis2Top, mTopViewH + mDis2Top)
        path.lineTo(mDis2Left * 2, mTopViewH)
        path.lineTo(mDis2Left, mTopViewH)
        // bottom
        path.lineTo(mDis2Left, measuredHeight - mTopViewH - mDis2Top)
        path.lineTo(mDis2Left * 2, measuredHeight - mTopViewH - mDis2Top)
        path.lineTo(mDis2Left * 2 + mDis2Top, (measuredHeight - mTopViewH))
        path.lineTo(mDis2Left * 2 + mDis2Top + mLineLen, (measuredHeight - mTopViewH))
        path.lineTo(mDis2Left * 2 + mDis2Top * 2 + mLineLen, measuredHeight - mTopViewH - mDis2Top)
        path.lineTo(mTopViewW, measuredHeight - mTopViewH - mDis2Top)

        path.lineTo(mTopViewW, measuredHeight - mDis2Top)
        path.lineTo(mDis2Left + mDis2Top * 2 + mLineLen, measuredHeight - mDis2Top)
        path.lineTo(mDis2Left + mDis2Top + mLineLen, measuredHeight.toFloat())
        path.lineTo(mDis2Left + mDis2Top, measuredHeight.toFloat())
        path.lineTo(mDis2Left, measuredHeight - mDis2Top)
        path.lineTo(0f, measuredHeight - mDis2Top)
        path.close()

        mPaint.style = Paint.Style.FILL
        mPaint.color = ContextCompat.getColor(context, R.color.colorControlYellow)
        mPaint.pathEffect = CornerPathEffect(mRadius)
        canvas.drawPath(path, mPaint)
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = mStrokeW
        mPaint.color = ContextCompat.getColor(context, android.R.color.darker_gray)
        canvas.drawPath(path, mPaint)
    }

    protected enum class ChildTag(tag: String) {
        TAG_TOP("tag_top"),
        TAG_BOTTOM("tag_bottom"),
        TAG_CHILD("tag_child")
    }
}