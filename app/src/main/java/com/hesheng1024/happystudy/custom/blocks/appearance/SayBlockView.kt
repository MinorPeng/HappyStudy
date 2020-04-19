package com.hesheng1024.happystudy.custom.blocks.appearance

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.hesheng1024.base.utils.DensityUtil
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.custom.base.BaseBgBlockView
import com.hesheng1024.happystudy.custom.base.IBaseBlock
import com.hesheng1024.happystudy.custom.base.IRoleListener
import com.hesheng1024.happystudy.custom.base.IRoleView

/**
 *
 * @author hesheng1024
 * @date 2020/3/29 19:34
 */
@SuppressLint("ViewConstructor")
class SayBlockView : BaseBgBlockView, IRoleListener {

    private val mEtContent: AppCompatEditText

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        setBgColorId(R.color.colorAppearancePurple)
        mEtContent = AppCompatEditText(context)
        initView()
    }

    private fun initView() {
        val tv = AppCompatTextView(context)
        tv.setText(R.string.say)
        tv.setTextColor(ContextCompat.getColor(context, android.R.color.white))
        addView(tv, 0)

        val lp = generateDefaultLayoutParams() as MarginLayoutParams
        lp.leftMargin = DensityUtil.dp2px(context, 8f)
        mEtContent.minEms = 2
        mEtContent.setLines(1)
        mEtContent.setText(R.string.hello)
        mEtContent.setBackgroundResource(R.drawable.bg_et_circle_whilte)
        addView(mEtContent, 1, lp)
    }

    override fun onRun(role: IRoleView) {
        role.say(mEtContent.text.toString())
    }

    override fun clone(): IBaseBlock {
        val newObj = SayBlockView(context)
        newObj.layoutParams = this.layoutParams
        newObj.minimumWidth = measuredWidth
        newObj.minimumHeight = measuredHeight
        newObj.mEtContent.setText(this.mEtContent.text.toString())
        return newObj
    }
}