package com.minorpeng.happystudy.custom.blocks.event

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.minorpeng.base.utils.DensityUtil
import com.minorpeng.happystudy.R
import com.minorpeng.happystudy.custom.base.IRoleListener

/**
 *
 * @author MinorPeng
 * @date 2020/3/30 21:44
 */
class ClickRunBlockView : BaseEventBlockView {

    private val mDisTop = DensityUtil.dp2px(context, 16f).toFloat()

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr) {
        LayoutInflater.from(context).inflate(R.layout.layout_click_run_block, this)
    }

    override fun onRun(role: View) {

    }

}