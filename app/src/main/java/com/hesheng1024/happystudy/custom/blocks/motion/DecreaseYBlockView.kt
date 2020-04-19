package com.hesheng1024.happystudy.custom.blocks.motion

import android.annotation.SuppressLint
import android.content.Context
import android.text.InputType
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
 * @date 2020/3/28 19:10
 */
@SuppressLint("ViewConstructor")
class DecreaseYBlockView : BaseBgBlockView {

    private val mEt: AppCompatEditText
    
    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        setBgColorId(R.color.colorMotionBlue)
        mEt = AppCompatEditText(context)
        initView()
    }

    private fun initView() {
        val whiteColor = ContextCompat.getColor(context, android.R.color.white)
        val tvY = TextView(context)
        tvY.setText(R.string.decrease_y)
        tvY.setTextColor(whiteColor)
        addView(tvY)

        val lp = generateDefaultLayoutParams() as MarginLayoutParams
        lp.leftMargin = DensityUtil.dp2px(context, 8f)
        mEt.minEms = 2
        mEt.setText(R.string.ten)
        mEt.setBackgroundResource(R.drawable.bg_et_circle_whilte)
        mEt.inputType = InputType.TYPE_NUMBER_FLAG_DECIMAL
        mEt.gravity = Gravity.CENTER
        mEt.setLines(1)
        mEt.setOnDragListener { v, event ->
            return@setOnDragListener true
        }
        addView(mEt, lp)
    }

    override fun onRun(role: IRoleView) {
        role.decreaseY(mEt.text.toString().toFloat())
    }

    override fun clone(): IBaseBlock {
        val newObj = DecreaseYBlockView(context)
        newObj.layoutParams = this.layoutParams
        newObj.minimumWidth = measuredWidth
        newObj.minimumHeight = measuredHeight
        newObj.mEt.setText(this.mEt.text.toString())
        return newObj
    }
}