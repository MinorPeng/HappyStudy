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
 * @date 2020/3/28 19:04
 */
@SuppressLint("ViewConstructor")
class MoveToXYBlockView : BaseBgBlockView {

    private val mEtX: AppCompatEditText
    private val mEtY: AppCompatEditText

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        setBgColorId(R.color.colorMotionBlue)
        mEtX = AppCompatEditText(context)
        mEtY = AppCompatEditText(context)
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
        mEtX.minEms = 2
        mEtX.setText(R.string.ten)
        mEtX.setBackgroundResource(R.drawable.bg_et_circle_whilte)
        mEtX.inputType = InputType.TYPE_NUMBER_FLAG_DECIMAL
        mEtX.gravity = Gravity.CENTER
        mEtX.setLines(1)
        mEtX.setOnDragListener { v, event ->
            return@setOnDragListener true
        }
        addView(mEtX, lpX)

        val tvY = TextView(context)
        tvY.setText(R.string.y)
        tvY.setTextColor(whiteColor)
        addView(tvY)

        val lpY = generateDefaultLayoutParams() as MarginLayoutParams
        lpY.leftMargin = DensityUtil.dp2px(context, 8f)
        mEtY.minEms = 2
        mEtY.setText(R.string.zero)
        mEtY.setBackgroundResource(R.drawable.bg_et_circle_whilte)
        mEtY.inputType = InputType.TYPE_NUMBER_FLAG_DECIMAL
        mEtY.setLines(1)
        mEtY.gravity = Gravity.CENTER
        mEtY.setOnDragListener { v, event ->
            return@setOnDragListener true
        }
        addView(mEtY, lpY)
    }

    override suspend fun onRun(role: IRoleView) {
        role.moveToXY(mEtX.text.toString().toFloat(), mEtY.text.toString().toFloat())
    }

    override fun clone(): IBaseBlock {
        val newObj = MoveToXYBlockView(context)
        newObj.layoutParams = this.layoutParams
        newObj.minimumWidth = measuredWidth
        newObj.minimumHeight = measuredHeight
        newObj.mEtX.setText(this.mEtX.text.toString())
        newObj.mEtY.setText(this.mEtY.text.toString())
        return newObj
    }
}