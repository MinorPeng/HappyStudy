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
import com.hesheng1024.happystudy.custom.blocks.calculate.BaseCalculateBlockView
import com.hesheng1024.happystudy.custom.blocks.calculate.CalculateBgBlock

/**
 *
 * @author hesheng1024
 * @date 2020/4/1 20:28
 */
@SuppressLint("ViewConstructor")
class WaitBlockView : BaseBgBlockView {

    private val mCalculateBg: CalculateBgBlock
    
    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        setBgColorId(R.color.colorControlYellow)
        mCalculateBg = CalculateBgBlock(context)
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
        val etSeconds = AppCompatEditText(context)
        etSeconds.minEms = 2
        etSeconds.setText(R.string.ten)
        etSeconds.setBackgroundResource(R.drawable.bg_et_circle_whilte)
        etSeconds.gravity = Gravity.CENTER
        etSeconds.setOnDragListener { v, event ->
            return@setOnDragListener true
        }
        mCalculateBg.addView(etSeconds)
        addView(mCalculateBg, lpEtSeconds)


        val tvSeconds = TextView(context)
        tvSeconds.setText(R.string.seconds)
        tvSeconds.setTextColor(whiteColor)
        addView(tvSeconds)
    }

    override fun onRun(role: IRoleView) {
        Thread.sleep(mCalculateBg.calculateResult().toLong())
    }

    override fun clone(): IBaseBlock {
        val newObj = WaitBlockView(context)
        newObj.layoutParams = this.layoutParams
        newObj.minimumWidth = measuredWidth
        newObj.minimumHeight = measuredHeight
        when (val child = mCalculateBg.getChildAt(0)) {
            is AppCompatEditText -> {
                if (newObj.mCalculateBg.getChildAt(0) is AppCompatEditText) {
                    (newObj.mCalculateBg.getChildAt(0) as AppCompatEditText).setText(child.text.toString())
                } else {
                    val newEt = AppCompatEditText(context)
                    newEt.setText(child.text.toString())
                    newObj.mCalculateBg.addView(newEt, 0)
                }
            }
            is BaseCalculateBlockView -> {
                newObj.mCalculateBg.addView(child.clone() as BaseCalculateBlockView)
            }
        }
        return newObj
    }
}