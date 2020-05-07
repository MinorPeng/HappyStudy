package com.hesheng1024.happystudy.custom.blocks.event

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.hesheng1024.base.utils.dp2px
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.custom.blocks.BlockTextView
import com.hesheng1024.happystudy.custom.blocks.base.IBaseBlock
import com.hesheng1024.happystudy.custom.role.IRoleView

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
        val tv = BlockTextView(context)
        tv.setText(R.string.when_str)
        tv.compoundDrawablePadding = dp2px(context, 4f)
        val drawable = ContextCompat.getDrawable(context, R.drawable.ic_run_16)
        drawable?.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
        tv.setCompoundDrawables(null, null, drawable, null)
        addView(tv)

        val lp = generateDefaultLayoutParams() as MarginLayoutParams
        lp.leftMargin = dp2px(context, 4f)
        val tvClick = BlockTextView(context)
        tvClick.setText(R.string.click)
        addView(tvClick, lp)
    }

    override suspend fun onRun(role: IRoleView) {

    }

    override fun clone(): IBaseBlock {
        val newObj = ClickRunBlockView(context)
        newObj.layoutParams = this.layoutParams
        newObj.minimumWidth = measuredWidth
        newObj.minimumHeight = measuredHeight
        return newObj
    }

}