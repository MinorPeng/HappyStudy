package com.hesheng1024.happystudy.custom.blocks.calculate

import android.content.Context
import android.util.AttributeSet
import com.hesheng1024.happystudy.custom.base.IBaseBlock
import com.hesheng1024.happystudy.custom.base.IRoleView

/**
 *
 * @author hesheng1024
 * @date 2020/4/17 21:39
 */
class CalculateBgBlock : BaseCalculateBlockView {
    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        setBgColorId(android.R.color.white)
    }

    override fun onRun(role: IRoleView) {

    }

    override fun clone(): IBaseBlock {
        val newObj = LogicBgBlockView(context)
        newObj.layoutParams = this.layoutParams
        if (newObj.layoutParams.width <= 0 || newObj.layoutParams.height <= 0) {
            newObj.layoutParams.width = measuredWidth
            newObj.layoutParams.height = measuredHeight
        }
        return newObj
    }

    override fun calculateResult(): Float {
        val child = getChildAt(0)
        if (child is BaseCalculateBlockView) {
            return child.calculateResult()
        }
        return -1f
    }
}