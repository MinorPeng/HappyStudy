package com.hesheng1024.happystudy.custom.blocks.control

import android.content.Context
import android.graphics.Canvas
import android.graphics.CornerPathEffect
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.hesheng1024.base.utils.DensityUtil
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.custom.base.BaseBlockViewGroup
import com.hesheng1024.happystudy.custom.base.IBaseBlock
import kotlin.math.max

/**
 *
 * @author hesheng1024
 * @date 2020/4/4 17:06
 */
abstract class BaseControlBlockView : BaseBlockViewGroup  {

    private var mTopViewH = DensityUtil.dp2px(context, 32f).toFloat()
    private var mTopViewW = DensityUtil.dp2px(context, 150f).toFloat()

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        setBgColorId(R.color.colorControlYellow)
        this.setWillNotDraw(false)
        this.setPadding(
            (IBaseBlock.sDis2Top * 2).toInt(),
            IBaseBlock.sDis2Top.toInt(),
            (IBaseBlock.sDis2Top * 2).toInt(),
            (IBaseBlock.sDis2Top * 2)
                .toInt()
        )
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
            when (child.tag) {
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
                    val childHeight =
                        child.measuredHeight + childLp.topMargin + childLp.bottomMargin - IBaseBlock.sDis2Top.toInt()
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
        mTopViewH = max(topViewMaxH + paddingTop + paddingBottom - IBaseBlock.sDis2Top, mTopViewH)
        centerViewH = if (centerViewH == 0) mTopViewH.toInt() else centerViewH
        var width = max(mTopViewW, centerMaxW + IBaseBlock.sDis2Left).toInt()
        var height = centerViewH + mTopViewH.toInt() * 2 + IBaseBlock.sDis2Top.toInt()

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
            when (child.tag) {
                ChildTag.TAG_TOP -> {
                    childL = topL + childLp.leftMargin
                    childT = (mTopViewH.toInt() - child.measuredHeight) / 2 + childLp.topMargin
                    childR = childL + child.measuredWidth
                    childB = childT + child.measuredHeight
                    topL += child.measuredWidth + childLp.leftMargin + childLp.rightMargin
                }
                ChildTag.TAG_CHILD -> {
                    childL = IBaseBlock.sDis2Left.toInt() + childLp.leftMargin
                    childT = centerT + childLp.topMargin
                    childR = childL + child.measuredWidth
                    childB = childT + child.measuredHeight
                    centerT += child.measuredHeight + childLp.topMargin + childLp.bottomMargin
                }
                ChildTag.TAG_BOTTOM -> {
                    // layout from right to left
                    childL = mTopViewW.toInt() - child.measuredWidth - childLp.rightMargin - paddingRight
                    childT = measuredHeight - (mTopViewH.toInt() - child.measuredHeight) / 2 - child.measuredHeight -
                            IBaseBlock.sDis2Top.toInt()
                    childR = childL + child.measuredWidth
                    childB = childT + child.measuredHeight
                }
                else -> {

                }
            }
            child.layout(childL, childT, childR, childB)
        }
    }

    override fun drawBackground(canvas: Canvas, paint: Paint, path: Path, measuredW: Float, measuredH: Float) {
        path.reset()
        // top
        path.moveTo(0f, 0f)
        path.lineTo(IBaseBlock.sDis2Left, 0f)
        path.lineTo(IBaseBlock.sDis2Left + IBaseBlock.sDis2Top, IBaseBlock.sDis2Top)
        path.lineTo(IBaseBlock.sDis2Left + IBaseBlock.sDis2Top + IBaseBlock.sLineLen, IBaseBlock.sDis2Top)
        path.lineTo(IBaseBlock.sDis2Left + IBaseBlock.sDis2Top * 2 + IBaseBlock.sLineLen, 0f)
        path.lineTo(mTopViewW, 0f)

        path.lineTo(mTopViewW, mTopViewH)
        path.lineTo(IBaseBlock.sDis2Left * 2 + IBaseBlock.sDis2Top * 2 + IBaseBlock.sLineLen, mTopViewH)
        path.lineTo(IBaseBlock.sDis2Left * 2 + IBaseBlock.sDis2Top + IBaseBlock.sLineLen, mTopViewH + IBaseBlock.sDis2Top)
        path.lineTo(IBaseBlock.sDis2Left * 2 + IBaseBlock.sDis2Top, mTopViewH + IBaseBlock.sDis2Top)
        path.lineTo(IBaseBlock.sDis2Left * 2, mTopViewH)
        path.lineTo(IBaseBlock.sDis2Left, mTopViewH)
        // bottom
        path.lineTo(IBaseBlock.sDis2Left, measuredH - mTopViewH - IBaseBlock.sDis2Top)
        path.lineTo(IBaseBlock.sDis2Left * 2, measuredH - mTopViewH - IBaseBlock.sDis2Top)
        path.lineTo(IBaseBlock.sDis2Left * 2 + IBaseBlock.sDis2Top, (measuredH - mTopViewH))
        path.lineTo(IBaseBlock.sDis2Left * 2 + IBaseBlock.sDis2Top + IBaseBlock.sLineLen, (measuredH - mTopViewH))
        path.lineTo(IBaseBlock.sDis2Left * 2 + IBaseBlock.sDis2Top * 2 + IBaseBlock.sLineLen, measuredH - mTopViewH - IBaseBlock.sDis2Top)
        path.lineTo(mTopViewW, measuredH - mTopViewH - IBaseBlock.sDis2Top)

        path.lineTo(mTopViewW, measuredH - IBaseBlock.sDis2Top)
        path.lineTo(IBaseBlock.sDis2Left + IBaseBlock.sDis2Top * 2 + IBaseBlock.sLineLen, measuredH - IBaseBlock.sDis2Top)
        path.lineTo(IBaseBlock.sDis2Left + IBaseBlock.sDis2Top + IBaseBlock.sLineLen, measuredH)
        path.lineTo(IBaseBlock.sDis2Left + IBaseBlock.sDis2Top, measuredH)
        path.lineTo(IBaseBlock.sDis2Left, measuredH - IBaseBlock.sDis2Top)
        path.lineTo(0f, measuredH - IBaseBlock.sDis2Top)
        path.close()

        paint.style = Paint.Style.FILL
        paint.color = getBgColor()
        paint.pathEffect = CornerPathEffect(IBaseBlock.sRadius)
        canvas.drawPath(path, paint)
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = IBaseBlock.sStrokeW
        paint.color = getBgBorderColor()
        canvas.drawPath(path, paint)
    }

    protected enum class ChildTag(tag: String) {
        TAG_TOP("tag_top"),
        TAG_BOTTOM("tag_bottom"),
        TAG_CHILD("tag_child")
    }
}