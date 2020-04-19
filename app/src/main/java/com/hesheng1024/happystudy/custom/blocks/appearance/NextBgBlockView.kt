package com.hesheng1024.happystudy.custom.blocks.appearance

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import com.hesheng1024.base.utils.LogUtil
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.custom.base.BaseTextBlockView
import com.hesheng1024.happystudy.custom.base.IBaseBlock
import com.hesheng1024.happystudy.custom.base.IRoleListener
import com.hesheng1024.happystudy.custom.base.IRoleView

/**
 *
 * @author hesheng1024
 * @date 2020/3/26 20:37
 */
class NextBgBlockView : BaseTextBlockView, IRoleListener {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet? = null) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
            : super(context, attrs, defStyleAttr)

    init {
        setBgColorId(R.color.colorAppearancePurple)
        setText(R.string.next_bg)
    }

    override suspend fun onRun(role: IRoleView) {
        role.nextBackground()
    }

    override fun clone(): IBaseBlock {
        val newObj = NextBgBlockView(context)
        newObj.layoutParams = this.layoutParams
        newObj.minimumWidth = measuredWidth
        newObj.minimumHeight = measuredHeight
        LogUtil.d(msg = "clone0 mw:$measuredWidth mh:$measuredHeight w:${newObj.layoutParams.width} " +
                "h:${newObj.layoutParams.height} lp:${layoutParams}")
        return newObj
    }
}