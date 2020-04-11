package com.minorpeng.happystudy.custom.blocks.appearance

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import com.minorpeng.happystudy.R
import com.minorpeng.happystudy.custom.base.BaseBgBlockView
import com.minorpeng.happystudy.custom.base.BaseTextBlockView

/**
 *
 * @author MinorPeng
 * @date 2020/3/26 20:37
 */
class NextBgBlockView(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    BaseTextBlockView(context, attrs, defStyleAttr) {

    init {
        setText(R.string.next_bg)
    }

    override fun getBgColorId(): Int {
        return R.color.colorAppearancePurple
    }

    override fun onRun(role: View) {
    }
}