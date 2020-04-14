package com.hesheng1024.happystudy.custom.blocks.appearance

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.hesheng1024.base.utils.LogUtil
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.custom.base.BaseTextBlockView
import com.hesheng1024.happystudy.custom.base.IBaseBlock

/**
 *
 * @author hesheng1024
 * @date 2020/3/30 20:59
 */
class HideBlockView : BaseTextBlockView {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        setBgColorId(R.color.colorAppearancePurple)
        setText(R.string.hide)
    }

    override fun onRun(role: View) {
        role.visibility = View.INVISIBLE
    }

    override fun clone(): IBaseBlock {
        val newObj = HideBlockView(context)
        newObj.layoutParams = this.layoutParams
        return newObj
    }
}