package com.minorpeng.happystudy.custom.blocks.appearance

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
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
 * @date 2020/3/29 19:34
 */
class SayBlockView : BaseBgBlockView {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr, 0) {
        initView()
    }

    private fun initView() {
        val tv = TextView(context)
        tv.setText(R.string.say)
        tv.setTextColor(ContextCompat.getColor(context, android.R.color.white))
        addView(tv)

        val lp = generateDefaultLayoutParams() as MarginLayoutParams
        lp.leftMargin = DensityUtil.dp2px(context, 8f)
        val et = EditText(context)
        et.minEms = 2
        et.setText(R.string.hello)
        et.setBackgroundResource(R.drawable.bg_et_circle_whilte)
        addView(et, lp)
    }

    override fun getBgColorId(): Int {
        return R.color.colorAppearancePurple
    }

    override fun onRun(role: View) {

    }
}