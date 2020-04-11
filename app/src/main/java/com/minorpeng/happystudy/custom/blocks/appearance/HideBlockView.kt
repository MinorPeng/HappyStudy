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
class HideBlockView : BaseTextBlockView {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

    }

    init {
        setBgColorId(R.color.colorAppearancePurple)
        setText(R.string.hide)
    }

    override fun onRun(role: View) {

    }
}