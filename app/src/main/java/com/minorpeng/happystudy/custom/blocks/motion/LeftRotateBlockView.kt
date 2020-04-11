package com.minorpeng.happystudy.custom.blocks.motion

import android.annotation.SuppressLint
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
 * @date 2020/3/28 19:05
 */
@SuppressLint("ViewConstructor")
class LeftRotateBlockView(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0) :
    BaseBgBlockView(context, attrs, defStyleAttr, defStyleRes) {

    init {
        initView()
    }

    private fun initView() {
        val whiteColor = ContextCompat.getColor(context, android.R.color.white)
        val tvLeftRotate = TextView(context)
        tvLeftRotate.setText(R.string.left_rotate)
        tvLeftRotate.setTextColor(whiteColor)
        addView(tvLeftRotate)

        val lp = generateDefaultLayoutParams() as MarginLayoutParams
        lp.leftMargin = DensityUtil.dp2px(context, 8f)
        lp.rightMargin = DensityUtil.dp2px(context, 8f)
        val etDegree = EditText(context)
        etDegree.minEms = 2
        etDegree.setText(R.string.ten)
        etDegree.setBackgroundResource(R.drawable.bg_et_circle_whilte)
        etDegree.inputType = InputType.TYPE_CLASS_NUMBER
        etDegree.gravity = Gravity.CENTER
        addView(etDegree, lp)

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