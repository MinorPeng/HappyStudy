package com.hesheng1024.happystudy.custom.blocks.control

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import com.hesheng1024.base.utils.DensityUtil
import com.hesheng1024.base.utils.LogUtil
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.custom.base.BaseBlockViewGroup
import com.hesheng1024.happystudy.custom.base.IBaseBlock
import com.hesheng1024.happystudy.custom.base.IRoleView
import com.hesheng1024.happystudy.custom.blocks.calculate.BaseCalculateBlockView
import com.hesheng1024.happystudy.custom.blocks.calculate.BaseLogicBlockView
import kotlin.math.max

/**
 *
 * @author hesheng1024
 * @date 2020/4/4 17:06
 */
abstract class BaseControlBlockView : BaseBlockViewGroup {

    /**
     * Child's RectF of parent
     */
    private val mChildRectF = RectF()
    private var mTopViewH = DensityUtil.dp2px(context, 32f).toFloat()
    private var mTopViewW = DensityUtil.dp2px(context, 150f).toFloat()

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        this.setBgColorId(R.color.colorControlYellow)
        this.setWillNotDraw(false)
        this.setPadding(
            (IBaseBlock.DIS_TO_TOP * 2).toInt(),
            IBaseBlock.DIS_TO_TOP.toInt(),
            (IBaseBlock.DIS_TO_TOP * 2).toInt(),
            (IBaseBlock.DIS_TO_TOP * 2).toInt()
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
                    val childHeight = child.measuredHeight + childLp.topMargin + childLp.bottomMargin
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
        mTopViewH = max(topViewMaxH + paddingTop + paddingBottom - IBaseBlock.DIS_TO_TOP, mTopViewH)
        centerViewH = if (centerViewH == 0) mTopViewH.toInt() else centerViewH
        var width = max(mTopViewW, centerMaxW + IBaseBlock.DIS_TO_LEFT).toInt()
        var height = centerViewH + mTopViewH.toInt() * 2 + IBaseBlock.DIS_TO_TOP.toInt()
        mChildRectF.left = IBaseBlock.DIS_TO_LEFT
        mChildRectF.top = mTopViewH
        mChildRectF.right = width - mChildRectF.left
        mChildRectF.bottom = mChildRectF.top + centerViewH

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
                    childL = IBaseBlock.DIS_TO_LEFT.toInt() + childLp.leftMargin
                    childT = centerT + childLp.topMargin
                    childR = childL + child.measuredWidth
                    childB = childT + child.measuredHeight
                    centerT += child.measuredHeight + childLp.topMargin + childLp.bottomMargin
                }
                ChildTag.TAG_BOTTOM -> {
                    // layout from right to left
                    childL = mTopViewW.toInt() - child.measuredWidth - childLp.rightMargin - paddingRight
                    childT = measuredHeight - (mTopViewH.toInt() - child.measuredHeight) / 2 - child.measuredHeight -
                            IBaseBlock.DIS_TO_TOP.toInt()
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
        path.lineTo(IBaseBlock.DIS_TO_LEFT, 0f)
        path.lineTo(IBaseBlock.DIS_TO_LEFT + IBaseBlock.DIS_TO_TOP, IBaseBlock.DIS_TO_TOP)
        path.lineTo(IBaseBlock.DIS_TO_LEFT + IBaseBlock.DIS_TO_TOP + IBaseBlock.LINE_LEN, IBaseBlock.DIS_TO_TOP)
        path.lineTo(IBaseBlock.DIS_TO_LEFT + IBaseBlock.DIS_TO_TOP * 2 + IBaseBlock.LINE_LEN, 0f)
        path.lineTo(mTopViewW, 0f)

        path.lineTo(mTopViewW, mTopViewH)
        path.lineTo(IBaseBlock.DIS_TO_LEFT * 2 + IBaseBlock.DIS_TO_TOP * 2 + IBaseBlock.LINE_LEN, mTopViewH)
        path.lineTo(
            IBaseBlock.DIS_TO_LEFT * 2 + IBaseBlock.DIS_TO_TOP + IBaseBlock.LINE_LEN,
            mTopViewH + IBaseBlock.DIS_TO_TOP
        )
        path.lineTo(IBaseBlock.DIS_TO_LEFT * 2 + IBaseBlock.DIS_TO_TOP, mTopViewH + IBaseBlock.DIS_TO_TOP)
        path.lineTo(IBaseBlock.DIS_TO_LEFT * 2, mTopViewH)
        path.lineTo(IBaseBlock.DIS_TO_LEFT, mTopViewH)
        // bottom
        path.lineTo(IBaseBlock.DIS_TO_LEFT, measuredH - mTopViewH - IBaseBlock.DIS_TO_TOP)
        path.lineTo(IBaseBlock.DIS_TO_LEFT * 2, measuredH - mTopViewH - IBaseBlock.DIS_TO_TOP)
        path.lineTo(IBaseBlock.DIS_TO_LEFT * 2 + IBaseBlock.DIS_TO_TOP, (measuredH - mTopViewH))
        path.lineTo(IBaseBlock.DIS_TO_LEFT * 2 + IBaseBlock.DIS_TO_TOP + IBaseBlock.LINE_LEN, (measuredH - mTopViewH))
        path.lineTo(
            IBaseBlock.DIS_TO_LEFT * 2 + IBaseBlock.DIS_TO_TOP * 2 + IBaseBlock.LINE_LEN,
            measuredH - mTopViewH - IBaseBlock.DIS_TO_TOP
        )
        path.lineTo(mTopViewW, measuredH - mTopViewH - IBaseBlock.DIS_TO_TOP)

        path.lineTo(mTopViewW, measuredH - IBaseBlock.DIS_TO_TOP)
        path.lineTo(
            IBaseBlock.DIS_TO_LEFT + IBaseBlock.DIS_TO_TOP * 2 + IBaseBlock.LINE_LEN,
            measuredH - IBaseBlock.DIS_TO_TOP
        )
        path.lineTo(IBaseBlock.DIS_TO_LEFT + IBaseBlock.DIS_TO_TOP + IBaseBlock.LINE_LEN, measuredH)
        path.lineTo(IBaseBlock.DIS_TO_LEFT + IBaseBlock.DIS_TO_TOP, measuredH)
        path.lineTo(IBaseBlock.DIS_TO_LEFT, measuredH - IBaseBlock.DIS_TO_TOP)
        path.lineTo(0f, measuredH - IBaseBlock.DIS_TO_TOP)
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


    override fun onDragEv(event: DragEvent?): Boolean {
        if (event == null) {
            LogUtil.e(msg = "v:$this event:$event")
            return false
        }
        val block = event.localState
        if (block !is BaseLogicBlockView && block !is BaseCalculateBlockView
            && block is IBaseBlock && block is View && block.getBlackOwn() is View) {
            val blackOwn = block.getBlackOwn() as View
            val oldBlockTag = block.tag
            val oldBlackOwnTag = blackOwn.tag
            block.tag = ChildTag.TAG_CHILD
            blackOwn.tag = ChildTag.TAG_CHILD
            for (index in 0 until childCount) {
                val child = getChildAt(index)
                // 传递给子View进行判断处理 排除掉阴影
                if (child != null
                    && child.tag == ChildTag.TAG_CHILD
                    && child is IBaseBlock
                    && child.getStatus() == IBaseBlock.Status.STATUS_DRAG
                    && child.onDragEv(event)) {
                    return true
                }
            }
            // 除了第一次没有子View会走下面逻辑以外，有子View的情况都不应该走以下逻辑
            when (event.action) {
                DragEvent.ACTION_DRAG_LOCATION -> {
                    val x = event.x - left
                    val y = event.y - top
                    if (isInChildRectF(x, y) && blackOwn.parent == null) {
                        addView(blackOwn)
                    } else {
                        removeView(blackOwn)
                    }
                }
                DragEvent.ACTION_DROP -> {
                    val x = event.x - left
                    val y = event.y - top
                    LogUtil.i(msg = "drop in child: x->$x y->$y l:$left t:$top")
                    if (isInChildRectF(x, y)) {
                        (block.parent as? ViewGroup)?.removeView(block)
                        removeView(blackOwn)
                        addView(block)
                        return true
                    }
                }
                else -> {
                    // 如果该ViewGroup不处理，应当将标识重置为之前的状态
                    block.tag = oldBlockTag
                    blackOwn.tag = oldBlackOwnTag
                }
            }
        }
        return super.onDragEv(event)
    }

    /**
     * 判断一个点是否在ChildRectF中
     *
     * @param x 相对于parent的x
     * @param y 相对于parent的y
     */
    private fun isInChildRectF(x: Float, y: Float): Boolean {
        val isIn = mChildRectF.contains(x, y)
        LogUtil.d(msg = "isIn:$isIn rectF:$mChildRectF x:$x y:$y")
        return isIn
    }

    fun onChildRun(role: IRoleView) {
        for (index in 0 until childCount) {
            val child = getChildAt(index)
            if (child != null && child.tag == ChildTag.TAG_CHILD && child is IBaseBlock) {
                child.onRun(role)
            }
        }
    }

    protected enum class ChildTag(tag: String) {
        TAG_TOP("tag_top"),
        TAG_BOTTOM("tag_bottom"),
        TAG_CHILD("tag_child")
    }
}