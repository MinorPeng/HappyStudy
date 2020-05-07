package com.hesheng1024.happystudy.custom.blocks.control

import android.annotation.SuppressLint
import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatEditText
import com.hesheng1024.base.utils.dp2px
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.custom.blocks.BlockEditText
import com.hesheng1024.happystudy.custom.blocks.BlockTextView
import com.hesheng1024.happystudy.custom.blocks.base.IBaseBlock
import com.hesheng1024.happystudy.custom.role.IRoleView
import com.hesheng1024.happystudy.custom.blocks.calculate.BaseCalculateBlockView
import com.hesheng1024.happystudy.custom.blocks.calculate.CalculateBgBlock

/**
 *
 * @author hesheng1024
 * @date 2020/4/1 11:04
 */
@SuppressLint("ViewConstructor")
class CirculationNumBlockView : BaseControlBlockView {

    private val mCalculateBg: CalculateBgBlock

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        mCalculateBg = CalculateBgBlock(context)
        initView()
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        val tvCirculation = BlockTextView(context)
        tvCirculation.setText(R.string.circulation)
        tvCirculation.tag = ChildTag.TAG_TOP
        addView(tvCirculation)

        val lpBgBlock = generateDefaultLayoutParams() as MarginLayoutParams
        lpBgBlock.leftMargin = dp2px(context, 4f)
        lpBgBlock.rightMargin =  dp2px(context, 4f)
        val etCount = BlockEditText(context)
        etCount.inputType = InputType.TYPE_CLASS_NUMBER
        etCount.setText(R.string.ten)
        mCalculateBg.addView(etCount)
        mCalculateBg.tag = ChildTag.TAG_TOP
        addView(mCalculateBg, lpBgBlock)


        val tvCount = BlockTextView(context)
        tvCount.setText(R.string.count)
        tvCount.tag = ChildTag.TAG_TOP
        addView(tvCount)


        val ivCirculation = ImageView(context)
        ivCirculation.setImageResource(R.drawable.ic_circulation_16)
        ivCirculation.tag = ChildTag.TAG_BOTTOM
        addView(ivCirculation)
    }

    override suspend fun onRun(role: IRoleView) {
        for (i in 1..mCalculateBg.calculateResult().toInt()) {
            onChildRun(role)
        }
    }

    override fun clone(): IBaseBlock {
        val newObj = CirculationNumBlockView(context)
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