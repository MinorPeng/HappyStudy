package com.minorpeng.happystudy.custom.blocks.motion

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.minorpeng.base.utils.DensityUtil
import com.minorpeng.happystudy.R
import com.minorpeng.happystudy.custom.base.BaseBgBlockView

/**
 *
 * @author MinorPeng
 * @date 2020/3/28 19:06
 */
class RightRotateBlockView : BaseBgBlockView {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr, 0) {
        initView()
    }

    private fun initView() {
        val whiteColor = ContextCompat.getColor(context, android.R.color.white)
        val tvRightRotate = TextView(context)
        tvRightRotate.setText(R.string.right_rotate)
        tvRightRotate.setTextColor(whiteColor)
        addView(tvRightRotate)

        val lp = generateDefaultLayoutParams() as MarginLayoutParams
        lp.leftMargin = DensityUtil.dp2px(context, 8f)
        lp.rightMargin = DensityUtil.dp2px(context, 8f)
        val et = EditText(context)
        et.minEms = 2
        et.setText(R.string.ten)
        et.setBackgroundResource(R.drawable.bg_et_circle_whilte)
        et.inputType = InputType.TYPE_CLASS_NUMBER
        et.gravity = Gravity.CENTER
        addView(et, lp)

        val tvDegree = TextView(context)
        tvDegree.setText(R.string.degree)
        tvDegree.setTextColor(whiteColor)
        addView(tvDegree)
    }

    override fun getBgColorId(): Int {
        return R.color.colorMotionBlue
    }

    override fun onRun(role: View) {
    }
}