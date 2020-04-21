package com.hesheng1024.happystudy.custom.blocks.motion

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import com.hesheng1024.base.utils.dp2px
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.custom.BlockEditText
import com.hesheng1024.happystudy.custom.BlockTextView
import com.hesheng1024.happystudy.custom.base.BaseBgBlockView
import com.hesheng1024.happystudy.custom.base.IBaseBlock
import com.hesheng1024.happystudy.custom.base.IRoleView

/**
 *
 * @author hesheng1024
 * @date 2020/3/24 18:12
 */
@SuppressLint("ViewConstructor")
class MoveBlockView : BaseBgBlockView {

    private val mEt: BlockEditText
    
    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        setBgColorId(R.color.colorMotionBlue)
        mEt = BlockEditText(context)
        initView()
    }

    private fun initView() {
        val tv = BlockTextView(context)
        tv.setText(R.string.move)
        addView(tv)

        val lp = generateDefaultLayoutParams() as MarginLayoutParams
        lp.leftMargin = dp2px(context, 4f)
        lp.rightMargin = dp2px(context, 4f)
        addView(mEt, lp)

        val tvStep = BlockTextView(context)
        tvStep.setText(R.string.step)
        addView(tvStep)
    }

    override suspend fun onRun(role: IRoleView) {
        role.moveStep(mEt.text.toString().toInt())
    }

    override fun clone(): IBaseBlock {
        val newObj = MoveBlockView(context)
        newObj.layoutParams = this.layoutParams
        newObj.minimumWidth = measuredWidth
        newObj.minimumHeight = measuredHeight
        newObj.mEt.setText(this.mEt.text.toString())
        return newObj
    }
}