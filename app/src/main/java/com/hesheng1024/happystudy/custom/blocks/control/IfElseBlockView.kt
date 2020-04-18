package com.hesheng1024.happystudy.custom.blocks.control

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.hesheng1024.base.utils.DensityUtil
import com.hesheng1024.base.utils.LogUtil
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.custom.base.BaseBlockViewGroup
import com.hesheng1024.happystudy.custom.base.IBaseBlock
import com.hesheng1024.happystudy.custom.base.IRoleView
import com.hesheng1024.happystudy.custom.blocks.calculate.BaseCalculateBlockView
import com.hesheng1024.happystudy.custom.blocks.calculate.BaseLogicBlockView
import com.hesheng1024.happystudy.custom.blocks.calculate.LogicBgBlockView
import com.hesheng1024.happystudy.custom.blocks.motion.MoveBlockView
import kotlin.math.max

/**
 *
 * @author hesheng1024
 * @date 2020/4/1 11:03
 */
@SuppressLint("ViewConstructor")
class IfElseBlockView : BaseBlockViewGroup {

    private val mChildIfRectF: RectF = RectF()
    private val mChildElseRectF: RectF = RectF()
    private val mLogicBgView: LogicBgBlockView
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
            (IBaseBlock.DIS_TO_TOP * 2).toInt(),
            IBaseBlock.DIS_TO_TOP.toInt(),
            (IBaseBlock.DIS_TO_TOP * 2).toInt(),
            (IBaseBlock.DIS_TO_TOP * 2).toInt()
        )
        mLogicBgView = LogicBgBlockView(context)
        initView()
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        val whiteColor = ContextCompat.getColor(context, android.R.color.white)

        val tvIf = TextView(context)
        tvIf.setText(R.string.if_str)
        tvIf.tag = ChildTag.TAG_TOP
        tvIf.setTextColor(whiteColor)
        addView(tvIf)

        val lp = generateDefaultLayoutParams() as MarginLayoutParams
        lp.leftMargin = DensityUtil.dp2px(context, 8f)
        lp.rightMargin = DensityUtil.dp2px(context, 8f)
        mLogicBgView.setBgColorId(R.color.colorControlYellowDark)
        mLogicBgView.tag = ChildTag.TAG_TOP
        addView(mLogicBgView, lp)

        val tvThen = TextView(context)
        tvThen.setText(R.string.then)
        tvThen.tag = ChildTag.TAG_TOP
        tvThen.setTextColor(whiteColor)
        addView(tvThen)

        val tvElse = TextView(context)
        tvElse.setText(R.string.else_str)
        tvElse.tag = ChildTag.TAG_CENTER
        tvElse.setTextColor(whiteColor)
        addView(tvElse)

        val waitBlockView = WaitBlockView(context)
        waitBlockView.tag = ChildTag.TAG_CHILD_ELSE
        addView(waitBlockView)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val sizeW = MeasureSpec.getSize(widthMeasureSpec)
        val modeW = MeasureSpec.getMode(widthMeasureSpec)
        val sizeH = MeasureSpec.getSize(heightMeasureSpec)
        val modeH = MeasureSpec.getMode(heightMeasureSpec)

        var topViewW = 0
        var topViewMaxH = 0
        var centerViewW = 0
        var bottomViewW = 0

        var childMaxW = 0
        var childIfH = 0
        var childElseH = 0
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
                ChildTag.TAG_CHILD_IF -> {
                    val childWidth = child.measuredWidth + childLp.leftMargin + childLp.rightMargin
                    val childHeight = child.measuredHeight + childLp.topMargin + childLp.bottomMargin
                    // if view max width
                    childMaxW = max(childMaxW, childWidth)
                    // if view height sum
                    childIfH += childHeight
                }
                ChildTag.TAG_CENTER -> {
                    val childWidth = child.measuredWidth + childLp.leftMargin + childLp.rightMargin
                    val childHeight = child.measuredHeight + childLp.topMargin + childLp.bottomMargin
                    // center view width sum
                    centerViewW += childWidth
                    // center view max height
                    topViewMaxH = max(topViewMaxH, childHeight)
                }
                ChildTag.TAG_CHILD_ELSE -> {
                    val childWidth = child.measuredWidth + childLp.leftMargin + childLp.rightMargin
                    val childHeight = child.measuredHeight + childLp.topMargin + childLp.bottomMargin
                    // else view max width
                    childMaxW = max(childMaxW, childWidth)
                    // else view height sum
                    childElseH += childHeight
                }
                ChildTag.TAG_BOTTOM -> {
                    val childWidth = child.measuredWidth + childLp.leftMargin + childLp.rightMargin
                    val childHeight = child.measuredHeight + childLp.topMargin + childLp.bottomMargin
                    // bottom view width sum
                    bottomViewW += childWidth
                    // bottom view max height
                    topViewMaxH = max(topViewMaxH, childHeight)
                }
                else -> {

                }
            }
        }
        mTopViewW = max(max(topViewW, max(centerViewW, bottomViewW)) + paddingLeft + paddingRight + 0f, mTopViewW)
        mTopViewH = max(topViewMaxH + paddingTop + paddingBottom - IBaseBlock.DIS_TO_TOP, mTopViewH)

        childIfH = if (childIfH == 0) mTopViewH.toInt() else childIfH
        childElseH = if (childElseH == 0) mTopViewH.toInt() else childElseH
        var width = max(mTopViewW, childMaxW + IBaseBlock.DIS_TO_LEFT).toInt()
        var height = childIfH + childElseH + mTopViewH.toInt() * 3 + IBaseBlock.DIS_TO_TOP.toInt()
        mChildIfRectF.left = IBaseBlock.DIS_TO_LEFT
        mChildIfRectF.top = mTopViewH
        mChildIfRectF.right = width - mChildIfRectF.left
        mChildIfRectF.bottom = mChildIfRectF.top + childIfH

        mChildElseRectF.left = IBaseBlock.DIS_TO_LEFT
        mChildElseRectF.top = mChildIfRectF.bottom + mTopViewH
        mChildElseRectF.right = width - mChildElseRectF.left
        mChildElseRectF.bottom = height - mChildElseRectF.top - IBaseBlock.DIS_TO_TOP.toInt()

        width = if (modeW == MeasureSpec.EXACTLY) sizeW else width
        height = if (modeH == MeasureSpec.EXACTLY) sizeH else height
        setMeasuredDimension(width, height)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var topL = paddingLeft
        var ifT = mTopViewH.toInt()
        var centerL = paddingLeft
        var elseT = (mChildIfRectF.height() + mTopViewH * 2).toInt()
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
                    // layout from left to right
                    childL = topL + childLp.leftMargin
                    childT = (mTopViewH.toInt() - child.measuredHeight) / 2 + childLp.topMargin
                    childR = childL + child.measuredWidth
                    childB = childT + child.measuredHeight
                    topL += child.measuredWidth + childLp.leftMargin + childLp.rightMargin
                }
                ChildTag.TAG_CHILD_IF -> {
                    childL = IBaseBlock.DIS_TO_LEFT.toInt() + childLp.leftMargin
                    childT = ifT + childLp.topMargin
                    childR = childL + child.measuredWidth
                    childB = childT + child.measuredHeight
                    ifT += child.measuredHeight + childLp.topMargin + childLp.bottomMargin
                }
                ChildTag.TAG_CENTER -> {
                    childL = centerL + childLp.leftMargin
                    childT = (mTopViewH + mChildIfRectF.height() +
                            (mTopViewH - child.measuredHeight) / 2 + childLp.topMargin).toInt()
                    childR = childL + child.measuredWidth
                    childB = childT + child.measuredHeight
                    centerL += child.measuredWidth + childLp.leftMargin + childLp.rightMargin
                }
                ChildTag.TAG_CHILD_ELSE -> {
                    childL = IBaseBlock.DIS_TO_LEFT.toInt() + childLp.leftMargin
                    childT = elseT + childLp.topMargin
                    childR = childL + child.measuredWidth
                    childB = childT + child.measuredHeight
                    elseT += child.measuredHeight + childLp.topMargin + childLp.bottomMargin
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
        // center
        val centerH = mChildIfRectF.height() + mTopViewH
        path.lineTo(IBaseBlock.DIS_TO_LEFT, centerH)
        path.lineTo(IBaseBlock.DIS_TO_LEFT * 2, centerH)
        path.lineTo(IBaseBlock.DIS_TO_LEFT * 2 + IBaseBlock.DIS_TO_TOP, centerH + IBaseBlock.DIS_TO_TOP)
        path.lineTo(
            IBaseBlock.DIS_TO_LEFT * 2 + IBaseBlock.DIS_TO_TOP + IBaseBlock.LINE_LEN,
            centerH + IBaseBlock.DIS_TO_TOP
        )
        path.lineTo(IBaseBlock.DIS_TO_LEFT * 2 + IBaseBlock.DIS_TO_TOP * 2 + IBaseBlock.LINE_LEN, centerH)
        path.lineTo(mTopViewW, centerH)

        path.lineTo(mTopViewW, centerH + mTopViewH)
        path.lineTo(IBaseBlock.DIS_TO_LEFT * 2 + IBaseBlock.DIS_TO_TOP * 2 + IBaseBlock.LINE_LEN, centerH + mTopViewH)
        path.lineTo(
            IBaseBlock.DIS_TO_LEFT * 2 + IBaseBlock.DIS_TO_TOP + IBaseBlock.LINE_LEN,
            centerH + mTopViewH + IBaseBlock.DIS_TO_TOP
        )
        path.lineTo(IBaseBlock.DIS_TO_LEFT * 2 + IBaseBlock.DIS_TO_TOP, centerH + mTopViewH + IBaseBlock.DIS_TO_TOP)
        path.lineTo(IBaseBlock.DIS_TO_LEFT * 2, centerH + mTopViewH)
        path.lineTo(IBaseBlock.DIS_TO_LEFT, centerH + mTopViewH)
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
            
        }
        return super.onDragEv(event)
    }

    /**
     * 判断一个点是否在ChildIfRectF中
     *
     * @param x 相对于parent的x
     * @param y 相对于parent的y
     */
    private fun isInIfRectF(x: Float, y: Float): Boolean = mChildIfRectF.contains(x, y)

    private fun isInElseRectF(x: Float, y: Float): Boolean = mChildElseRectF.contains(x, y)

    override fun onRun(role: IRoleView) {
        if (mLogicBgView.judgeResult()) {
            onChildIfRun(role)
        } else {
            onChildElseRun(role)
        }
    }

    private fun onChildIfRun(role: IRoleView) {
        for (index in 0 until childCount) {
            val child = getChildAt(0)
            if (child != null && child.tag == ChildTag.TAG_CHILD_IF && child is IBaseBlock) {
                child.onRun(role)
            }
        }
    }

    private fun onChildElseRun(role: IRoleView) {
        for (index in 0 until childCount) {
            val child = getChildAt(0)
            if (child != null && child.tag == ChildTag.TAG_CHILD_ELSE && child is IBaseBlock) {
                child.onRun(role)
            }
        }
    }

    override fun clone(): IBaseBlock {
        val newObj = IfElseBlockView(context)
        newObj.layoutParams = this.layoutParams
        newObj.minimumWidth = measuredWidth
        newObj.minimumHeight = measuredHeight
        val child = mLogicBgView.getChildAt(0)
        if (child != null && child is BaseLogicBlockView) {
            newObj.mLogicBgView.addView(child.clone() as BaseLogicBlockView)
        }
        return newObj
    }

    private enum class ChildTag(tag: String) {
        TAG_TOP("tag_top"),
        TAG_CENTER("tag_center"),
        TAG_BOTTOM("tag_bottom"),
        TAG_CHILD_IF("tag_child_if"),
        TAG_CHILD_ELSE("tag_child_if_else")
    }
}