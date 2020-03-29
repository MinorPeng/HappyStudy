package com.minorpeng.happystudy.custom.blocks.motion

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import com.minorpeng.happystudy.R
import com.minorpeng.happystudy.custom.base.BaseMotionBlockView

/**
 *
 * @author MinorPeng
 * @date 2020/3/28 19:09
 */
class AddYBlockView : BaseMotionBlockView {

    constructor(context: Context): this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr, 0) {
        LayoutInflater.from(context).inflate(R.layout.layout_add_y_block, this)
    }

    override fun onRun(role: View) {
    }
}