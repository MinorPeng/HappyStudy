package com.minorpeng.happystudy.custom.blocks.control

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.minorpeng.happystudy.custom.base.BaseBlockViewGroup

/**
 *
 * @author MinorPeng
 * @date 2020/4/1 11:02
 */
class DeathWhileBlockView : BaseBlockViewGroup {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {

    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

    }

    override fun onRun(role: View) {

    }
}