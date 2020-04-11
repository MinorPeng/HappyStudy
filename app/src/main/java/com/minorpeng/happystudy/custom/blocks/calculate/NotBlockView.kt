package com.minorpeng.happystudy.custom.blocks.calculate

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
 * @date 2020/4/4 14:21
 */
@SuppressLint("ViewConstructor")
class NotBlockView(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0) :
    BaseLogicBlockView(context, attrs, defStyleAttr, defStyleRes) {

    init {
        this.setPadding(sDis2Top.toInt(), sDis2Top.toInt(), sDis2Top.toInt(), sDis2Top.toInt())
        initView()
    }

    private fun initView() {
        val logicBlockView = LogicBgBlockView(context)
        addView(logicBlockView)

        val lpTvMoreThan = generateDefaultLayoutParams() as MarginLayoutParams
        lpTvMoreThan.leftMargin = DensityUtil.dp2px(context, 8f)
        lpTvMoreThan.rightMargin = sDisLeft.toInt()
        val tvMoreThan = TextView(context)
        tvMoreThan.setText(R.string.not)
        tvMoreThan.setTextColor(ContextCompat.getColor(context, android.R.color.white))
        addView(tvMoreThan, lpTvMoreThan)
    }

    override fun onRun(role: View) {

    }
}