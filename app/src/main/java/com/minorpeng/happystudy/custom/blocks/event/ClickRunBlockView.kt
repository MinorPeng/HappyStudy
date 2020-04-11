package com.minorpeng.happystudy.custom.blocks.event

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.minorpeng.base.utils.DensityUtil
import com.minorpeng.happystudy.R

/**
 *
 * @author MinorPeng
 * @date 2020/3/30 21:44
 */
@SuppressLint("ViewConstructor")
class ClickRunBlockView(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0) :
    BaseEventBlockView(context, attrs, defStyleAttr, defStyleRes) {

    init {
        initView()
    }

    private fun initView() {
        val whiteColor = ContextCompat.getColor(context, android.R.color.white)
        val tv = TextView(context)
        tv.setText(R.string.when_str)
        tv.setTextColor(whiteColor)
        tv.compoundDrawablePadding = DensityUtil.dp2px(context, 8f)
        val drawable = ContextCompat.getDrawable(context, R.drawable.ic_run_16)
        drawable?.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
        tv.setCompoundDrawables(null, null, drawable, null)
        addView(tv)

        val lp = generateDefaultLayoutParams() as MarginLayoutParams
        lp.leftMargin = DensityUtil.dp2px(context, 8f)
        val tvClick = TextView(context)
        tvClick.setText(R.string.click)
        tvClick.setTextColor(whiteColor)
        addView(tvClick, lp)
    }

    override fun onRun(role: View) {

    }

}