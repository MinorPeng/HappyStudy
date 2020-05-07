package com.hesheng1024.happystudy.custom.blocks.calculate

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.hesheng1024.base.utils.dp2px
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.custom.blocks.BlockEditText
import com.hesheng1024.happystudy.custom.blocks.BlockTextView
import com.hesheng1024.happystudy.custom.blocks.base.IBaseBlock
import com.hesheng1024.happystudy.custom.role.IRoleView

/**
 *
 * @author hesheng1024
 * @date 2020/4/3 16:29
 */
@SuppressLint("ViewConstructor")
class LessThanBlockView : BaseLogicBlockView {

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
        val etLeft = BlockEditText(context)
        etLeft.setText(R.string.zero)
        mLeftCalculateBg.addView(etLeft)
        addView(mLeftCalculateBg)

        val lpTvMoreThan = generateDefaultLayoutParams() as MarginLayoutParams
        lpTvMoreThan.leftMargin = dp2px(context, 8f)
        lpTvMoreThan.rightMargin = dp2px(context, 8f)
        val tvMoreThan = BlockTextView(context)
        tvMoreThan.text = "<"
        addView(tvMoreThan, lpTvMoreThan)

        val etRight = BlockEditText(context)
        etRight.setText(R.string.fifty)
        mRightCalculateBg.addView(etRight)
        addView(mRightCalculateBg)
    }

    override suspend fun onRun(role: IRoleView) {

    }

    override fun clone(): IBaseBlock {
        val newObj = LessThanBlockView(context)
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

    override fun judgeResult(): Boolean = mLeftCalculateBg.calculateResult() < mRightCalculateBg.calculateResult()
}