package com.hesheng1024.happystudy.custom.blocks.appearance

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.custom.base.BaseTextBlockView
import com.hesheng1024.happystudy.custom.base.IBaseBlock
import com.hesheng1024.happystudy.custom.base.IRoleListener
import com.hesheng1024.happystudy.custom.base.IRoleView

/**
 *
 * @author hesheng1024
 * @date 2020/3/30 20:56
 */
class ShowBlockView : BaseTextBlockView, IRoleListener {
    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        setBgColorId(R.color.colorAppearancePurple)
        setText(R.string.show)
    }

    override fun onRun(role: IRoleView) {
        role.show()
    }

    override fun clone(): IBaseBlock {
        val newObj = ShowBlockView(context)
        newObj.layoutParams = this.layoutParams
        return newObj
    }
}