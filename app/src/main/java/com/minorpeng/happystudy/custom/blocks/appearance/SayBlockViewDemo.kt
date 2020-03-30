package com.minorpeng.happystudy.custom.blocks.appearance

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.minorpeng.base.utils.LogUtil
import com.minorpeng.happystudy.custom.base.BaseBlockViewGroup
import kotlin.math.max

/**
 *
 * @author MinorPeng
 * @date 2020/3/26 21:48
 */
class SayBlockViewDemo : BaseBlockViewGroup {

    private var mWidth: Int
    private var mHeight: Int
    private var mDis2Left = 8f * 4
    private var mDis2Top = 2f * 4
    private var mLineLen = 4f * 4
    private val mPaint: Paint

    constructor(context: Context): this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr, 0) {
        mWidth = 64
        mHeight = 20
        mPaint = Paint()
        // ViewGroup默认关闭绘制，需要手动开启
        setWillNotDraw(false)
        initView()
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        val tv = TextView(context)
        tv.text = "说"
        val tvLp = MarginLayoutParams(MarginLayoutParams.WRAP_CONTENT, MarginLayoutParams.WRAP_CONTENT)
        tvLp.leftMargin = mDis2Top.toInt()
        tv.layoutParams = tvLp
        addView(tv)

        val et = EditText(context)
        et.setText("hello")
        val etLp = MarginLayoutParams(MarginLayoutParams.WRAP_CONTENT, MarginLayoutParams.WRAP_CONTENT)
        etLp.leftMargin = mDis2Top.toInt()
        et.layoutParams = etLp
        addView(et)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val sizeW = MeasureSpec.getSize(widthMeasureSpec)
        val modeW = MeasureSpec.getMode(widthMeasureSpec)
        val sizeH = MeasureSpec.getSize(heightMeasureSpec)
        val modeH = MeasureSpec.getMode(heightMeasureSpec)

        var width = 0
        var height = 0
        for (index in 0 until childCount) {
            val child = getChildAt(index)
            measureChild(child, widthMeasureSpec, heightMeasureSpec)
            val childLp = child.layoutParams as MarginLayoutParams
            val childWidth = child.measuredWidth + childLp.leftMargin + childLp.rightMargin
            val childHeight = child.measuredHeight + childLp.topMargin + childLp.bottomMargin
            width += childWidth
            height = max(height, childHeight)
        }

        mWidth = if (modeW == MeasureSpec.EXACTLY) sizeW else max(width + paddingLeft + paddingRight, mWidth)
        mHeight = if (modeH == MeasureSpec.EXACTLY) sizeH else max(height + paddingTop + paddingBottom, mHeight)
        LogUtil.d("w:$mWidth, h:$mHeight")
        setMeasuredDimension(mWidth, mHeight + mDis2Top.toInt())
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var left = paddingLeft
        var top = paddingTop
        for (index in 0 until childCount) {
            val child = getChildAt(index)
            if (child.visibility == View.GONE) {
                continue
            }
            val childLp = child.layoutParams as MarginLayoutParams
            val lc = left + childLp.leftMargin
            // val tc = top + childLp.topMargin
            // 设置垂直居中
            val tc = top + (measuredHeight - child.measuredHeight) / 2
            val rc = lc + child.measuredWidth
            val bc = tc + child.measuredHeight
            child.layout(lc, tc, rc, bc)
            left += child.measuredWidth + childLp.leftMargin + childLp.rightMargin
        }
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.let {
            drawBackground(canvas)
        }
    }

    private fun drawBackground(canvas: Canvas) {
        val path = Path()
        path.moveTo(0f, 0f)
        path.lineTo(mDis2Left, 0f)
        path.lineTo(mDis2Left + mDis2Top, mDis2Top)
        path.lineTo(mDis2Left + mDis2Top + mLineLen, mDis2Top)
        path.lineTo(mDis2Left + mDis2Top * 2 + mLineLen, 0f)
        path.lineTo(mWidth.toFloat(), 0f)
        path.lineTo(mWidth.toFloat(), mHeight.toFloat())
        path.lineTo(mDis2Left + mDis2Top * 2 + mLineLen, mHeight.toFloat())
        path.lineTo(mDis2Left + mDis2Top + mLineLen, mHeight + mDis2Top)
        path.lineTo(mDis2Left + mDis2Top, mHeight + mDis2Top)
        path.lineTo(mDis2Left, mHeight.toFloat())
        path.lineTo(0f, mHeight.toFloat())
        path.lineTo(0f, 0f)
        mPaint.style = Paint.Style.FILL
        mPaint.color = ContextCompat.getColor(context, android.R.color.holo_purple)
        canvas.drawPath(path, mPaint)
    }

    override fun onRun(role: View) {

    }
}