package com.minorpeng.happystudy.custom.blocks.control

import android.annotation.SuppressLint
import android.content.Context
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
 * @date 2020/4/1 20:28
 */
@SuppressLint("ViewConstructor")
class WaitBlockView(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0) :
    BaseBgBlockView(context, attrs, defStyleAttr, defStyleRes) {

    init {
        setBgColorId(R.color.colorControlYellow)
        initView()
    }

    private fun initView() {
        val whiteColor = ContextCompat.getColor(context, android.R.color.white)
        val tvWait = TextView(context)
        tvWait.setText(R.string.wait)
        tvWait.setTextColor(whiteColor)
        addView(tvWait)

        val lpEtSeconds = generateDefaultLayoutParams() as MarginLayoutParams
        lpEtSeconds.leftMargin = DensityUtil.dp2px(context, 8f)
        lpEtSeconds.rightMargin = DensityUtil.dp2px(context, 8f)
        val etSeconds = EditText(context)
        etSeconds.minEms = 2
        etSeconds.setText(R.string.ten)
        etSeconds.setBackgroundResource(R.drawable.bg_et_circle_whilte)
        etSeconds.gravity = Gravity.CENTER
        addView(etSeconds, lpEtSeconds)


        val tvSeconds = TextView(context)
        tvSeconds.setText(R.string.seconds)
        tvSeconds.setTextColor(whiteColor)
        addView(tvSeconds)
    }

    override fun onRun(role: View) {

    }
}