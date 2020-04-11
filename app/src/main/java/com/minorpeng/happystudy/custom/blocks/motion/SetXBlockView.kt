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
 * @date 2020/3/28 19:07
 */
@SuppressLint("ViewConstructor")
class SetXBlockView(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0) :
    BaseBgBlockView(context, attrs, defStyleAttr, defStyleRes) {

    init {
        initView()
    }

    private fun initView() {
        val whiteColor = ContextCompat.getColor(context, android.R.color.white)
        val tvX = TextView(context)
        tvX.setText(R.string.set_x)
        tvX.setTextColor(whiteColor)
        addView(tvX)

        val lp = generateDefaultLayoutParams() as MarginLayoutParams
        lp.leftMargin = DensityUtil.dp2px(context, 8f)
        val et = EditText(context)
        et.minEms = 2
        et.setText(R.string.ten)
        et.setBackgroundResource(R.drawable.bg_et_circle_whilte)
        et.inputType = InputType.TYPE_CLASS_NUMBER
        et.gravity = Gravity.CENTER
        addView(et, lp)
    }

    override fun getBgColorId(): Int {
        return R.color.colorMotionBlue
    }

    override fun onRun(role: View) {
    }
}