package com.hesheng1024.happystudy.custom.blocks.calculate

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
import com.hesheng1024.happystudy.custom.base.IBaseBlock
import com.hesheng1024.happystudy.custom.base.IRoleView

/**
 *
 * @author hesheng1024
 * @date 2020/4/3 16:46
 */
@SuppressLint("ViewConstructor")
class AddBlockView: BaseCalculateBlockView {
    
    private val mLeftBgBlock: CalculateBgBlock
    private val mRightBgBlock: CalculateBgBlock

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        mLeftBgBlock = CalculateBgBlock(context)
        mRightBgBlock = CalculateBgBlock(context)
        initView()
    }

    private fun initView() {
        val etLeft = AppCompatEditText(context)
        etLeft.setBackgroundResource(R.drawable.bg_et_circle_whilte)
        etLeft.gravity = Gravity.CENTER
        etLeft.inputType = InputType.TYPE_CLASS_NUMBER
        etLeft.minEms = 2
        etLeft.setOnDragListener { v, event ->
            return@setOnDragListener true
        }
        mLeftBgBlock.addView(etLeft, 0)
        addView(mLeftBgBlock, 0)

        val lpTvAdd = generateDefaultLayoutParams() as MarginLayoutParams
        lpTvAdd.leftMargin = DensityUtil.dp2px(context, 8f)
        lpTvAdd.rightMargin = DensityUtil.dp2px(context, 8f)
        val tvAdd = TextView(context)
        tvAdd.setText(R.string.add)
        tvAdd.setTextColor(ContextCompat.getColor(context, android.R.color.white))
        addView(tvAdd,1, lpTvAdd)

        val etRight = AppCompatEditText(context)
        etRight.setBackgroundResource(R.drawable.bg_et_circle_whilte)
        etRight.gravity = Gravity.CENTER
        etRight.inputType = InputType.TYPE_CLASS_NUMBER
        etRight.setText(R.string.fifty)
        etRight.minEms = 2
        etRight.setOnDragListener { v, event ->
            return@setOnDragListener true
        }
        mRightBgBlock.addView(etRight, 0)
        addView(mRightBgBlock, 2)
    }

    override fun onRun(role: IRoleView) {

    }

    override fun clone(): IBaseBlock {
        val newObj = AddBlockView(context)
        newObj.layoutParams = this.layoutParams
        if (newObj.layoutParams.width <= 0 || newObj.layoutParams.height <= 0) {
            newObj.layoutParams.width = measuredWidth
            newObj.layoutParams.height = measuredHeight
        }
        return newObj
    }

    override fun calculateResult(): Float {
        val left = when (val leftBlock = mLeftBgBlock.getChildAt(0)) {
            is AppCompatEditText -> {
                leftBlock.text.toString().toFloat()
            }
            is BaseCalculateBlockView -> {
                leftBlock.calculateResult()
            }
            else -> {
                -1f
            }
        }
        val right = when (val rightBlock = mRightBgBlock.getChildAt(0)) {
            is AppCompatEditText -> {
                rightBlock.text.toString().toFloat()
            }
            is BaseCalculateBlockView -> {
                rightBlock.calculateResult()
            }
            else -> {
                -1f
            }
        }
        return left + right
    }
}