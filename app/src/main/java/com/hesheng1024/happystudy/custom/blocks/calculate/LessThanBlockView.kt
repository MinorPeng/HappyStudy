package com.hesheng1024.happystudy.custom.blocks.calculate

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.hesheng1024.happystudy.R
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
        View.inflate(context, R.layout.layout_less_than_block, this)
        mLeftCalculateBg = findViewById(R.id.bg_less_than_block_left)
        mRightCalculateBg = findViewById(R.id.bg_less_than_block_right)
        initView()
    }

    private fun initView() {
    }

    override suspend fun onRun(role: IRoleView) {

    }

    override fun clone(): IBaseBlock {
        val newObj = LessThanBlockView(context)
        newObj.layoutParams = this.layoutParams
        newObj.minimumWidth = measuredWidth
        newObj.minimumHeight = measuredHeight
        newObj.mLeftCalculateBg.clone(mLeftCalculateBg)
        newObj.mRightCalculateBg.clone(mRightCalculateBg)
        return newObj
    }

    override suspend fun judgeResult(): Boolean = mLeftCalculateBg.calculateResult() < mRightCalculateBg.calculateResult()
}