package com.minorpeng.happystudy.custom.blocks.control

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.CornerPathEffect
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.minorpeng.base.utils.DensityUtil
import com.minorpeng.base.utils.LogUtil
import com.minorpeng.happystudy.R
import com.minorpeng.happystudy.custom.base.BaseBlockViewGroup
import com.minorpeng.happystudy.custom.blocks.motion.MoveBlockView
import kotlin.math.max

/**
 *
 * @author MinorPeng
 * @date 2020/4/1 11:04
 */
class CirculationNumBlockView : BaseBlockViewGroup {

    private val mDis2Left = DensityUtil.dp2px(context, 10f).toFloat()
    private val mDis2Top = DensityUtil.dp2px(context, 4f).toFloat()
    private val mLineLen = DensityUtil.dp2px(context, 12f).toFloat()
    private var mTopChildH = 0
    private val mRadius = 6f
    private val mPaint = Paint()

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        this.setWillNotDraw(false)
        this.setPadding((mDis2Top * 2).toInt(), mDis2Top.toInt(), (mDis2Top * 2).toInt(), (mDis2Top * 2).toInt())
        initView()
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        val tvLp = MarginLayoutParams(MarginLayoutParams.WRAP_CONTENT, MarginLayoutParams.WRAP_CONTENT)
        val tv = TextView(context)
        tv.setText(R.string.circulation)
        tv.tag = ChildTag.TAG_TOP
        tv.setTextColor(ContextCompat.getColor(context, android.R.color.white))
        tv.layoutParams = tvLp
        addView(tv)

        val et = EditText(context)
        et.setText(R.string.ten)
        val etLp = MarginLayoutParams(MarginLayoutParams.WRAP_CONTENT, MarginLayoutParams.WRAP_CONTENT)
        etLp.leftMargin = mDis2Top.toInt() * 2
        etLp.rightMargin = mDis2Top.toInt() * 2
        et.layoutParams = etLp
        et.tag = ChildTag.TAG_TOP
        et.setBackgroundResource(R.drawable.bg_et_circle_whilte)
        addView(et)

        val tv1 = TextView(context)
        tv1.setText(R.string.count)
        val tvLp1 = MarginLayoutParams(MarginLayoutParams.WRAP_CONTENT, MarginLayoutParams.WRAP_CONTENT)
        tv1.layoutParams = tvLp1
        tv1.tag = ChildTag.TAG_TOP
        tv1.setTextColor(ContextCompat.getColor(context, android.R.color.white))
        addView(tv1)

        val mo = MoveBlockView(context)
        mo.layoutParams = tvLp
        mo.tag = ChildTag.TAG_CHILD
        addView(mo)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val sizeW = MeasureSpec.getSize(widthMeasureSpec)
        val modeW = MeasureSpec.getMode(widthMeasureSpec)
        val sizeH = MeasureSpec.getSize(heightMeasureSpec)
        val modeH = MeasureSpec.getMode(heightMeasureSpec)

        var width = 0
        var height = 0
        var topChildMaxH = 0
        for (index in 0 until childCount) {
            val child = getChildAt(index)
            measureChild(child, widthMeasureSpec, heightMeasureSpec)
            val childLp = child.layoutParams as MarginLayoutParams
            when(child.tag) {
                ChildTag.TAG_TOP -> {
                    val childWidth = child.measuredWidth + childLp.leftMargin + childLp.rightMargin
                    val childHeight = child.measuredHeight + childLp.topMargin + childLp.bottomMargin
                    width += childWidth
                    topChildMaxH = max(topChildMaxH, childHeight)
                }
                ChildTag.TAG_CHILD -> {
                    val childHeight = child.measuredHeight + childLp.topMargin + childLp.bottomMargin - mDis2Top.toInt()
                    height += childHeight
                }
                else -> {

                }
            }
        }
        mTopChildH = topChildMaxH + paddingTop + paddingBottom - mDis2Top.toInt()
        if (height == 0) {
            height = mTopChildH
        }
        width += paddingLeft + paddingRight
        height += mTopChildH * 2 + mDis2Top.toInt()

        width = if (modeW == MeasureSpec.EXACTLY) sizeW else width
        height = if (modeH == MeasureSpec.EXACTLY) sizeH else height
        setMeasuredDimension(width, height)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var left = paddingLeft
        var top = mTopChildH
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
                    childL = left + childLp.leftMargin
                    childT = (mTopChildH - child.measuredHeight) / 2 + childLp.topMargin
                    childR = childL + child.measuredWidth
                    childB = childT + child.measuredHeight
                    left += child.measuredWidth + childLp.leftMargin + childLp.rightMargin
                }
                ChildTag.TAG_CHILD -> {
                    childL = mDis2Left.toInt() + childLp.leftMargin
                    childT = top + childLp.topMargin
                    childR = childL + child.measuredWidth
                    childB = childT + child.measuredHeight
                    top += child.measuredHeight + childLp.topMargin + childLp.bottomMargin
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
        path.lineTo(measuredWidth.toFloat(), 0f)

        path.lineTo(measuredWidth.toFloat(), mTopChildH.toFloat())
        path.lineTo(mDis2Left * 2 + mDis2Top * 2 + mLineLen, mTopChildH.toFloat())
        path.lineTo(mDis2Left * 2 + mDis2Top + mLineLen, mTopChildH.toFloat() + mDis2Top)
        path.lineTo(mDis2Left * 2 + mDis2Top, mTopChildH.toFloat() + mDis2Top)
        path.lineTo(mDis2Left * 2, mTopChildH.toFloat())
        path.lineTo(mDis2Left, mTopChildH.toFloat())
        // bottom
        path.lineTo(mDis2Left, measuredHeight - mTopChildH - mDis2Top)
        path.lineTo(mDis2Left * 2, measuredHeight - mTopChildH - mDis2Top)
        path.lineTo(mDis2Left * 2 + mDis2Top, (measuredHeight - mTopChildH).toFloat())
        path.lineTo(mDis2Left * 2 + mDis2Top + mLineLen, (measuredHeight - mTopChildH).toFloat())
        path.lineTo(mDis2Left * 2 + mDis2Top * 2 + mLineLen, measuredHeight - mTopChildH - mDis2Top)
        path.lineTo(measuredWidth.toFloat(), measuredHeight - mTopChildH - mDis2Top)

        path.lineTo(measuredWidth.toFloat(), measuredHeight - mDis2Top)
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
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }

    override fun onRun(role: View) {

    }

    enum class ChildTag(tag: String) {
        TAG_TOP("tag_top"),
        TAG_CHILD("tag_child")
    }
}