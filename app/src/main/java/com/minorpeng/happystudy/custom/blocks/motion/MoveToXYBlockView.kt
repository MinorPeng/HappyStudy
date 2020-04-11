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
 * @date 2020/3/28 19:04
 */
@SuppressLint("ViewConstructor")
class MoveToXYBlockView(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0) :
    BaseBgBlockView(context, attrs, defStyleAttr, defStyleRes) {

    init {
        initView()
    }

    private fun initView() {
        val whiteColor = ContextCompat.getColor(context, android.R.color.white)
        val tvX = TextView(context)
        tvX.setText(R.string.move_x)
        tvX.setTextColor(whiteColor)
        addView(tvX)

        val lpX = generateDefaultLayoutParams() as MarginLayoutParams
        lpX.leftMargin = DensityUtil.dp2px(context, 8f)
        lpX.rightMargin = DensityUtil.dp2px(context, 8f)
        val etX = EditText(context)
        etX.minEms = 2
        etX.setText(R.string.ten)
        etX.setBackgroundResource(R.drawable.bg_et_circle_whilte)
        etX.inputType = InputType.TYPE_CLASS_NUMBER
        etX.gravity = Gravity.CENTER
        addView(etX, lpX)

        val tvY = TextView(context)
        tvY.setText(R.string.y)
        tvY.setTextColor(whiteColor)
        addView(tvY)

        val lpY = generateDefaultLayoutParams() as MarginLayoutParams
        lpY.leftMargin = DensityUtil.dp2px(context, 8f)
        val etY = EditText(context)
        etY.minEms = 2
        etY.setText(R.string.zero)
        etY.setBackgroundResource(R.drawable.bg_et_circle_whilte)
        etY.inputType = InputType.TYPE_CLASS_NUMBER
        etY.gravity = Gravity.CENTER
        addView(etY, lpY)
    }

    override fun getBgColorId(): Int {
        return R.color.colorMotionBlue
    }

    override fun onRun(role: View) {
    }
}