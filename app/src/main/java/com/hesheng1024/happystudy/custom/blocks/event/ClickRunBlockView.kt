package com.hesheng1024.happystudy.custom.blocks.event

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.hesheng1024.base.utils.DensityUtil
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.custom.base.IBaseBlock

/**
 *
 * @author hesheng1024
 * @date 2020/3/30 21:44
 */
@SuppressLint("ViewConstructor")
class ClickRunBlockView : BaseEventBlockView {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        initView()
    }

    private fun initView() {
        val whiteColor = ContextCompat.getColor(context, android.R.color.white)
        val tv = TextView(context)
        tv.setText(R.string.when_str)
        tv.setTextColor(whiteColor)
        tv.compoundDrawablePadding = DensityUtil.dp2px(context, 8f)
        val drawable = ContextCompat.getDrawable(context, R.drawable.ic_run_16)
        drawable?.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
        tv.setCompoundDrawables(null, null, drawable, null)
        addView(tv)

        val lp = generateDefaultLayoutParams() as MarginLayoutParams
        lp.leftMargin = DensityUtil.dp2px(context, 8f)
        val tvClick = TextView(context)
        tvClick.setText(R.string.click)
        tvClick.setTextColor(whiteColor)
        addView(tvClick, lp)
    }

    override fun onRun(role: View) {

    }

    override fun clone(): IBaseBlock {
        val newObj = ClickRunBlockView(context)
        newObj.layoutParams = this.layoutParams
        return newObj
    }

}