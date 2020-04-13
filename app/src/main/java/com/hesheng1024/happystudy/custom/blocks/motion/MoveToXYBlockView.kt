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
 * @date 2020/3/28 19:04
 */
@SuppressLint("ViewConstructor")
class MoveToXYBlockView : BaseBgBlockView {

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
        val tvX = TextView(context)
        tvX.setText(R.string.move_x)
        tvX.setTextColor(whiteColor)
        addView(tvX)

        val lpX = generateDefaultLayoutParams() as MarginLayoutParams
        lpX.leftMargin = DensityUtil.dp2px(context, 8f)
        lpX.rightMargin = DensityUtil.dp2px(context, 8f)
        val etX = EditText(context)
        etX.minEms = 2
        etX.setText(R.string.ten)
        etX.setBackgroundResource(R.drawable.bg_et_circle_whilte)
        etX.inputType = InputType.TYPE_CLASS_NUMBER
        etX.gravity = Gravity.CENTER
        addView(etX, lpX)

        val tvY = TextView(context)
        tvY.setText(R.string.y)
        tvY.setTextColor(whiteColor)
        addView(tvY)

        val lpY = generateDefaultLayoutParams() as MarginLayoutParams
        lpY.leftMargin = DensityUtil.dp2px(context, 8f)
        val etY = EditText(context)
        etY.minEms = 2
        etY.setText(R.string.zero)
        etY.setBackgroundResource(R.drawable.bg_et_circle_whilte)
        etY.inputType = InputType.TYPE_CLASS_NUMBER
        etY.gravity = Gravity.CENTER
        addView(etY, lpY)
    }

    override fun onRun(role: View) {
    }

    override fun clone(): IBaseBlock {
        val newObj = MoveToXYBlockView(context)
        newObj.layoutParams = this.layoutParams
        newObj.minimumWidth = measuredWidth
        newObj.minimumHeight = measuredHeight
        return newObj
    }
}