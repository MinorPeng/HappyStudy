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
 * @date 2020/4/3 16:29
 */
@SuppressLint("ViewConstructor")
class EqualBlockView : BaseLogicBlockView {

    private val mLeftCalculateBg: CalculateBgBlock
    private val mRightCalculateBg: CalculateBgBlock

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        mLeftCalculateBg = CalculateBgBlock(context)
        mRightCalculateBg = CalculateBgBlock(context)
        initView()
    }

    private fun initView() {
        val etLeft = AppCompatEditText(context)
        etLeft.setBackgroundResource(R.drawable.bg_et_circle_whilte)
        etLeft.gravity = Gravity.CENTER
        etLeft.inputType = InputType.TYPE_NUMBER_FLAG_DECIMAL
        etLeft.minEms = 2
        etLeft.setLines(1)
        etLeft.setOnDragListener { v, event ->
            return@setOnDragListener true
        }
        mLeftCalculateBg.addView(etLeft)
        addView(mLeftCalculateBg)

        val lpTvMoreThan = generateDefaultLayoutParams() as MarginLayoutParams
        lpTvMoreThan.leftMargin = DensityUtil.dp2px(context, 8f)
        lpTvMoreThan.rightMargin = DensityUtil.dp2px(context, 8f)
        val tvMoreThan = TextView(context)
        tvMoreThan.text = "="
        tvMoreThan.setTextColor(ContextCompat.getColor(context, android.R.color.white))
        addView(tvMoreThan, lpTvMoreThan)

        val etRight = AppCompatEditText(context)
        etRight.setBackgroundResource(R.drawable.bg_et_circle_whilte)
        etRight.gravity = Gravity.CENTER
        etRight.inputType = InputType.TYPE_NUMBER_FLAG_DECIMAL
        etRight.setText(R.string.fifty)
        etRight.minEms = 2
        etRight.setLines(1)
        etRight.setOnDragListener { v, event ->
            return@setOnDragListener true
        }
        mRightCalculateBg.addView(etRight)
        addView(mRightCalculateBg)
    }

    override suspend fun onRun(role: IRoleView) {

    }

    override fun clone(): IBaseBlock {
        val newObj = EqualBlockView(context)
        newObj.layoutParams = this.layoutParams
        newObj.minimumWidth = measuredWidth
        newObj.minimumHeight = measuredHeight
        when (val child = mLeftCalculateBg.getChildAt(0)) {
            is AppCompatEditText -> {
                if (newObj.mLeftCalculateBg.getChildAt(0) is AppCompatEditText) {
                    (newObj.mLeftCalculateBg.getChildAt(0) as AppCompatEditText).setText(child.text.toString())
                } else {
                    val newEt = AppCompatEditText(context)
                    newEt.setText(child.text.toString())
                    newObj.mLeftCalculateBg.addView(newEt, 0)
                }
            }
            is BaseCalculateBlockView -> {
                newObj.mLeftCalculateBg.addView(child.clone() as BaseCalculateBlockView)
            }
        }
        when (val child = mRightCalculateBg.getChildAt(0)) {
            is AppCompatEditText -> {
                if (newObj.mRightCalculateBg.getChildAt(0) is AppCompatEditText) {
                    (newObj.mRightCalculateBg.getChildAt(0) as AppCompatEditText).setText(child.text.toString())
                } else {
                    val newEt = AppCompatEditText(context)
                    newEt.setText(child.text.toString())
                    newObj.mRightCalculateBg.addView(newEt, 0)
                }
            }
            is BaseCalculateBlockView -> {
                newObj.mRightCalculateBg.addView(child.clone() as BaseCalculateBlockView)
            }
        }
        return newObj
    }

    override fun judgeResult(): Boolean = mLeftCalculateBg.calculateResult() == mRightCalculateBg.calculateResult()
}