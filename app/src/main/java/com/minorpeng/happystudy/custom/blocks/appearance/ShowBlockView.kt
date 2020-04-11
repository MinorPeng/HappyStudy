package com.minorpeng.happystudy.custom.blocks.appearance

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.minorpeng.happystudy.R
import com.minorpeng.happystudy.custom.base.BaseTextBlockView

/**
 *
 * @author MinorPeng
 * @date 2020/3/30 20:56
 */
class ShowBlockView(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    BaseTextBlockView(context, attrs, defStyleAttr) {

    init {
        setText(R.string.show)
    }

    override fun getBgColorId(): Int {
        return R.color.colorAppearancePurple
    }

    override fun onRun(role: View) {

    }
}