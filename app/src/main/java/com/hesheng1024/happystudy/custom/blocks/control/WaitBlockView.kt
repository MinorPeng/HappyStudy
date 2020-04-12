package com.hesheng1024.happystudy.custom.blocks.control

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.hesheng1024.base.utils.DensityUtil
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.custom.base.BaseBgBlockView

/**
 *
 * @author hesheng1024
 * @date 2020/4/1 20:28
 */
@SuppressLint("ViewConstructor")
class WaitBlockView : BaseBgBlockView {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        setBgColorId(R.color.colorControlYellow)
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
        val etSeconds = EditText(context)
        etSeconds.minEms = 2
        etSeconds.setText(R.string.ten)
        etSeconds.setBackgroundResource(R.drawable.bg_et_circle_whilte)
        etSeconds.gravity = Gravity.CENTER
        addView(etSeconds, lpEtSeconds)


        val tvSeconds = TextView(context)
        tvSeconds.setText(R.string.seconds)
        tvSeconds.setTextColor(whiteColor)
        addView(tvSeconds)
    }

    override fun onRun(role: View) {

    }
}