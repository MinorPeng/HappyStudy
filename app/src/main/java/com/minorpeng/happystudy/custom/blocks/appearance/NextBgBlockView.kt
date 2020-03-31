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
class NextBgBlockView : BaseTextBlockView {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr) {
        setText(R.string.next_bg)
    }

    override fun getBgColorId(): Int {
        return R.color.colorAppearancePurple
    }

    override fun onRun(role: View) {
    }
}