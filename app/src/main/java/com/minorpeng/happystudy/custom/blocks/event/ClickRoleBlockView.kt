package com.minorpeng.happystudy.custom.blocks.event

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.minorpeng.base.utils.DensityUtil
import com.minorpeng.happystudy.custom.base.IRoleListener

/**
 *
 * @author MinorPeng
 * @date 2020/3/30 21:44
 */
class ClickRoleBlockView : AppCompatTextView, IRoleListener {

    private val mDis2Left: Float
    private val mDis2Top: Float
    private val mLineLen: Float
    private val mPaint = Paint()

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mDis2Left = DensityUtil.dp2px(context, 8f).toFloat()
        mDis2Top = DensityUtil.dp2px(context, 4f).toFloat()
        mLineLen = DensityUtil.dp2px(context, 10f).toFloat()
        gravity = Gravity.CENTER
        this.setPadding((mDis2Top * 2).toInt(), (mDis2Top * 2).toInt(), (mDis2Top * 2).toInt(), (mDis2Top * 3).toInt())
        this.setTextColor(ContextCompat.getColor(context, android.R.color.white))
    }

    override fun onRun(role: View) {

    }

}