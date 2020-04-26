package com.hesheng1024.happystudy.custom.blocks.calculate

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.DragEvent
import android.view.ViewGroup
import com.hesheng1024.base.utils.dp2px
import com.hesheng1024.base.utils.logI
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.custom.BlockTextView
import com.hesheng1024.happystudy.custom.base.IBaseBlock
import com.hesheng1024.happystudy.custom.base.IRoleView

/**
 *
 * @author hesheng1024
 * @date 2020/4/4 14:20
 */
@SuppressLint("ViewConstructor")
class AndBlockView : BaseLogicBlockView {

    private val mLeftLogicBg: LogicBgBlockView
    private val mRightLogicBg: LogicBgBlockView

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        this.setPadding(
            IBaseBlock.DIS_TO_TOP.toInt(),
            IBaseBlock.DIS_TO_TOP.toInt(),
            IBaseBlock.DIS_TO_TOP.toInt(),
            IBaseBlock.DIS_TO_TOP.toInt()
        )
        mLeftLogicBg = LogicBgBlockView(context)
        mRightLogicBg = LogicBgBlockView(context)
        initView()
    }

    private fun initView() {
        addView(mLeftLogicBg)

        val lpTvMoreThan = generateDefaultLayoutParams() as MarginLayoutParams
        lpTvMoreThan.leftMargin = dp2px(context, 4f)
        lpTvMoreThan.rightMargin = dp2px(context, 4f)
        val tvMoreThan = BlockTextView(context)
        tvMoreThan.setText(R.string.and)
        addView(tvMoreThan, lpTvMoreThan)

        addView(mRightLogicBg)
    }

    override suspend fun onRun(role: IRoleView) {

    }

    override fun clone(): IBaseBlock {
        val newObj = AndBlockView(context)
        newObj.layoutParams = this.layoutParams
        newObj.minimumWidth = measuredWidth
        newObj.minimumHeight = measuredHeight
        var child = mLeftLogicBg.getChildAt(0)
        if ( child is BaseLogicBlockView) {
            newObj.mLeftLogicBg.addView(child.clone() as BaseLogicBlockView)
        }
        child = mRightLogicBg.getChildAt(0)
        if (child is BaseLogicBlockView) {
            newObj.mRightLogicBg.addView(child.clone() as BaseLogicBlockView)
        }
        return newObj
    }

    override fun judgeResult(): Boolean = mLeftLogicBg.judgeResult() && mRightLogicBg.judgeResult()
}