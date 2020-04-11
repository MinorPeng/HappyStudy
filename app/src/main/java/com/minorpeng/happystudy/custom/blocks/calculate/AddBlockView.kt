package com.minorpeng.happystudy.custom.blocks.calculate

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

/**
 *
 * @author MinorPeng
 * @date 2020/4/3 16:46
 */
@SuppressLint("ViewConstructor")
class AddBlockView(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0) :
    BaseCalculateBlockView(context, attrs, defStyleAttr, defStyleRes) {

    init {
        initView()
    }

    private fun initView() {
        val etLeft = EditText(context)
        etLeft.setBackgroundResource(R.drawable.bg_et_circle_whilte)
        etLeft.gravity = Gravity.CENTER
        etLeft.inputType = InputType.TYPE_CLASS_NUMBER
        etLeft.minEms = 2
        addView(etLeft)

        val lpTvAdd = generateDefaultLayoutParams() as MarginLayoutParams
        lpTvAdd.leftMargin = DensityUtil.dp2px(context, 8f)
        lpTvAdd.rightMargin = DensityUtil.dp2px(context, 8f)
        val tvAdd = TextView(context)
        tvAdd.setText(R.string.add)
        tvAdd.setTextColor(ContextCompat.getColor(context, android.R.color.white))
        addView(tvAdd, lpTvAdd)

        val etRight = EditText(context)
        etRight.setBackgroundResource(R.drawable.bg_et_circle_whilte)
        etRight.gravity = Gravity.CENTER
        etRight.inputType = InputType.TYPE_CLASS_NUMBER
        etRight.setText(R.string.fifty)
        etRight.minEms = 2
        addView(etRight)
    }

    override fun onRun(role: View) {

    }
}