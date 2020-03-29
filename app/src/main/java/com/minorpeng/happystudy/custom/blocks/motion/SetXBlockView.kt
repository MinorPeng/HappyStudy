package com.minorpeng.happystudy.custom.blocks.motion

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import com.minorpeng.base.utils.DensityUtil
import com.minorpeng.happystudy.R
import com.minorpeng.happystudy.custom.base.BaseBlockView
import com.minorpeng.happystudy.custom.base.BaseMotionBlockView

/**
 *
 * @author MinorPeng
 * @date 2020/3/28 19:07
 */
class SetXBlockView : BaseMotionBlockView {

    constructor(context: Context): this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr, 0) {
        LayoutInflater.from(context).inflate(R.layout.layout_set_x_block, this)
    }

    override fun onRun(role: View) {
    }
}