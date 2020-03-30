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
        // initView(
        LayoutInflater.from(context).inflate(R.layout.layout_say_block, this)
    }

    private fun initView() {
        val tv = TextView(context)
        tv.textSize = DensityUtil.sp2px(context, 14f).toFloat()
        tv.setText(R.string.say)
        tv.setTextColor(ContextCompat.getColor(context, android.R.color.white))
        addView(tv)

        val et = EditText(context)
        et.textSize = DensityUtil.sp2px(context, 14f).toFloat()
        et.setText(R.string.hello)
        et.setBackgroundResource(R.drawable.bg_et_circle_whilte)
        tv.setTextColor(ContextCompat.getColor(context, android.R.color.black))
        val etLp = MarginLayoutParams(MarginLayoutParams.WRAP_CONTENT, MarginLayoutParams.WRAP_CONTENT)
        etLp.leftMargin = DensityUtil.dp2px(context, 18f)
        et.layoutParams = etLp
        addView(et)
    }

    override fun getBgColorId(): Int {
        return R.color.colorAppearancePurple
    }

    override fun onRun(role: View) {

    }
}