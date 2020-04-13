package com.hesheng1024.happystudy.custom.blocks.motion

import android.annotation.SuppressLint
import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.hesheng1024.base.utils.DensityUtil
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.custom.base.BaseBgBlockView
import com.hesheng1024.happystudy.custom.base.IBaseBlock

/**
 *
 * @author hesheng1024
 * @date 2020/3/28 19:05
 */
@SuppressLint("ViewConstructor")
class LeftRotateBlockView : BaseBgBlockView {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        setBgColorId(R.color.colorMotionBlue)
        initView()
    }

    private fun initView() {
        val whiteColor = ContextCompat.getColor(context, android.R.color.white)
        val tvLeftRotate = TextView(context)
        tvLeftRotate.setText(R.string.left_rotate)
        tvLeftRotate.setTextColor(whiteColor)
        addView(tvLeftRotate)

        val lp = generateDefaultLayoutParams() as MarginLayoutParams
        lp.leftMargin = DensityUtil.dp2px(context, 8f)
        lp.rightMargin = DensityUtil.dp2px(context, 8f)
        val etDegree = EditText(context)
        etDegree.minEms = 2
        etDegree.setText(R.string.ten)
        etDegree.setBackgroundResource(R.drawable.bg_et_circle_whilte)
        etDegree.inputType = InputType.TYPE_CLASS_NUMBER
        etDegree.gravity = Gravity.CENTER
        addView(etDegree, lp)

        val tvDegree = TextView(context)
        tvDegree.setText(R.string.degree)
        tvDegree.setTextColor(whiteColor)
        addView(tvDegree)
    }

    override fun onRun(role: View) {
    }

    override fun clone(): IBaseBlock {
        val newObj = LeftRotateBlockView(context)
        newObj.layoutParams = this.layoutParams
        newObj.minimumWidth = measuredWidth
        newObj.minimumHeight = measuredHeight
        return newObj
    }
}