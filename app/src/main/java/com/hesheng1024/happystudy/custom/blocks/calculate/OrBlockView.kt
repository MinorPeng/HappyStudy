package com.hesheng1024.happystudy.custom.blocks.calculate

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.hesheng1024.base.utils.DensityUtil
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.custom.base.IBaseBlock
import com.hesheng1024.happystudy.custom.base.IRoleView

/**
 *
 * @author hesheng1024
 * @date 2020/4/4 15:19
 */
@SuppressLint("ViewConstructor")
class OrBlockView : BaseLogicBlockView {

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
        lpTvMoreThan.leftMargin = DensityUtil.dp2px(context, 8f)
        lpTvMoreThan.rightMargin = DensityUtil.dp2px(context, 8f)
        val tvMoreThan = TextView(context)
        tvMoreThan.setText(R.string.or)
        tvMoreThan.setTextColor(ContextCompat.getColor(context, android.R.color.white))
        addView(tvMoreThan, lpTvMoreThan)

        addView(mRightLogicBg)
    }

    override fun onRun(role: IRoleView) {

    }

    override fun clone(): IBaseBlock {
        val newObj = OrBlockView(context)
        newObj.layoutParams = this.layoutParams
        if (newObj.layoutParams.width <= 0 || newObj.layoutParams.height <= 0) {
            newObj.layoutParams.width = measuredWidth
            newObj.layoutParams.height = measuredHeight
        }
        return newObj
    }

    override fun judgeResult(): Boolean {
        return mLeftLogicBg.judgeResult() && mRightLogicBg.judgeResult()
    }
}