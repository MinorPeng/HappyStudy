package com.hesheng1024.happystudy.custom.blocks.calculate

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.VariableMap
import com.hesheng1024.happystudy.custom.blocks.BlockEditText
import com.hesheng1024.happystudy.custom.blocks.base.IBaseBlock
import com.hesheng1024.happystudy.custom.role.IRoleView

/**
 *
 * @author hesheng1024
 * @date 2020/5/12 11:00
 */
class VarAddBlockView : BaseCalculateBlockView {

    private val mVarName: BlockEditText
    private val mRightCalculateBg: CalculateBgBlock

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        View.inflate(context, R.layout.layout_variable_add_block, this)
        mVarName = findViewById(R.id.et_variable_add_block_name)
        mRightCalculateBg = findViewById(R.id.bg_variable_add_block_right)
        initView()
    }

    private fun initView() {

    }

    override fun clone(): IBaseBlock {
        val newObj = VarAddBlockView(context)
        newObj.layoutParams = this.layoutParams
        newObj.minimumWidth = measuredWidth
        newObj.minimumHeight = measuredHeight
        newObj.mVarName.setText(mVarName.text.toString())
        newObj.mRightCalculateBg.clone(mRightCalculateBg)
        return newObj
    }

    override suspend fun onRun(role: IRoleView) {

    }

    override fun calculateResult(): Float {
        val key = mVarName.text.toString()
        VariableMap[key]?.let {
            val value = it + mRightCalculateBg.calculateResult()
            VariableMap[key] = value
            return value
        }
        VariableMap[key] = 0f
        return 0f
    }
}