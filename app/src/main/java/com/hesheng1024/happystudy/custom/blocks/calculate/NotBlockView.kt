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
 * @date 2020/4/4 14:21
 */
@SuppressLint("ViewConstructor")
class NotBlockView : BaseLogicBlockView {

    private val mLogicBg: LogicBgBlockView

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
        View.inflate(context, R.layout.layout_not_block, this)
        mLogicBg = findViewById(R.id.bg_not_block_left)
        initView()
    }

    private fun initView() {
    }

    override suspend fun onRun(role: IRoleView) {

    }

    override fun clone(): IBaseBlock {
        val newObj = NotBlockView(context)
        newObj.layoutParams = this.layoutParams
        newObj.minimumWidth = measuredWidth
        newObj.minimumHeight = measuredHeight
        newObj.mLogicBg.clone(mLogicBg)
        return newObj
    }

    override fun judgeResult(): Boolean {
        return !mLogicBg.judgeResult()
    }
}