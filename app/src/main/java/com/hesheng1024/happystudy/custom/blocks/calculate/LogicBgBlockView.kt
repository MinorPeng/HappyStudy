package com.hesheng1024.happystudy.custom.blocks.calculate

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.custom.base.IBaseBlock
import com.hesheng1024.happystudy.custom.base.IRoleView

/**
 *
 * @author hesheng1024
 * @date 2020/4/4 15:22
 */
@SuppressLint("ViewConstructor")
class LogicBgBlockView : BaseLogicBlockView {
    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        setBgColorId(R.color.colorCalculateGreenDark)
        this.setPadding(
            sDisLeft.toInt(),
            IBaseBlock.sDis2Top.toInt(),
            sDisLeft.toInt(),
            IBaseBlock.sDis2Top.toInt()
        )
        minimumWidth = (sDisLeft * 3).toInt()
        minimumHeight = (sDisLeft * 2.3).toInt()
    }

    override fun onRun(role: IRoleView) {

    }

    override fun clone(): IBaseBlock {
        val newObj = LogicBgBlockView(context)
        newObj.layoutParams = this.layoutParams
        return newObj
    }
}