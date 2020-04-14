package com.hesheng1024.happystudy.custom.blocks.appearance

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.hesheng1024.base.utils.DensityUtil
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.custom.base.BaseBgBlockView
import com.hesheng1024.happystudy.custom.base.IBaseBlock

/**
 *
 * @author hesheng1024
 * @date 2020/3/29 19:34
 */
@SuppressLint("ViewConstructor")
class SayBlockView : BaseBgBlockView {

    private val mEtContent: AppCompatEditText
    private val mSayLayout: View

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        setBgColorId(R.color.colorAppearancePurple)
        mEtContent = AppCompatEditText(context)
        mSayLayout = LayoutInflater.from(context).inflate(R.layout.activity_test, null)
        initView()
    }

    private fun initView() {
        val tv = TextView(context)
        tv.setText(R.string.say)
        tv.setTextColor(ContextCompat.getColor(context, android.R.color.white))
        addView(tv, 0)

        val lp = generateDefaultLayoutParams() as MarginLayoutParams
        lp.leftMargin = DensityUtil.dp2px(context, 8f)
        mEtContent.minEms = 2
        mEtContent.setText(R.string.hello)
        mEtContent.setBackgroundResource(R.drawable.bg_et_circle_whilte)
        addView(mEtContent, 1, lp)
    }

    override fun onRun(role: View) {
        val lp = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT)
        lp.leftMargin = role.right - 8
        lp.topMargin = role.top - mSayLayout.measuredHeight
        (role.parent as? ViewGroup)?.addView(mSayLayout, lp)
    }

    override fun clone(): IBaseBlock {
        val newObj = SayBlockView(context)
        newObj.layoutParams = this.layoutParams
        newObj.mEtContent.setText(this.mEtContent.text.toString())
        return newObj
    }
}