package com.minorpeng.happystudy.custom.blocks.appearance

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.minorpeng.happystudy.R
import com.minorpeng.happystudy.custom.base.BaseTextBlockView

/**
 *
 * @author MinorPeng
 * @date 2020/3/30 20:59
 */
class HideBlockView(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    BaseTextBlockView(context, attrs, defStyleAttr) {

    init {
        setBgColorId(R.color.colorAppearancePurple)
        setText(R.string.hide)
    }

    override fun onRun(role: View) {

    }
}