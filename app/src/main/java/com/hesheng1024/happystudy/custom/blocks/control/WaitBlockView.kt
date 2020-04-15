package com.hesheng1024.happystudy.custom.blocks.control

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.hesheng1024.base.utils.DensityUtil
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.custom.base.BaseBgBlockView
import com.hesheng1024.happystudy.custom.base.IBaseBlock
import com.hesheng1024.happystudy.custom.base.IRoleView

/**
 *
 * @author hesheng1024
 * @date 2020/4/1 20:28
 */
@SuppressLint("ViewConstructor")
class WaitBlockView : BaseBgBlockView {

    private val mEtSeconds: AppCompatEditText
    
    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        setBgColorId(R.color.colorControlYellow)
        mEtSeconds = AppCompatEditText(context)
        initView()
    }

    private fun initView() {
        val whiteColor = ContextCompat.getColor(context, android.R.color.white)
        val tvWait = TextView(context)
        tvWait.setText(R.string.wait)
        tvWait.setTextColor(whiteColor)
        addView(tvWait)

        val lpEtSeconds = generateDefaultLayoutParams() as MarginLayoutParams
        lpEtSeconds.leftMargin = DensityUtil.dp2px(context, 8f)
        lpEtSeconds.rightMargin = DensityUtil.dp2px(context, 8f)
        mEtSeconds.minEms = 2
        mEtSeconds.setText(R.string.ten)
        mEtSeconds.setBackgroundResource(R.drawable.bg_et_circle_whilte)
        mEtSeconds.gravity = Gravity.CENTER
        mEtSeconds.setOnDragListener { v, event ->
            return@setOnDragListener true
        }
        addView(mEtSeconds, lpEtSeconds)


        val tvSeconds = TextView(context)
        tvSeconds.setText(R.string.seconds)
        tvSeconds.setTextColor(whiteColor)
        addView(tvSeconds)
    }

    override fun onRun(role: IRoleView) {

    }

    override fun clone(): IBaseBlock {
        val newObj = WaitBlockView(context)
        newObj.layoutParams = this.layoutParams
        newObj.mEtSeconds.setText(this.mEtSeconds.text.toString())
        return newObj
    }
}