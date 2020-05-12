package com.hesheng1024.happystudy.custom.blocks.calculate

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.VariableMap
import com.hesheng1024.happystudy.custom.blocks.BlockEditText
import com.hesheng1024.happystudy.custom.blocks.base.IBaseBlock
import com.hesheng1024.happystudy.custom.role.IRoleView

/**
 *
 * @author hesheng1024
 * @date 2020/5/12 10:03
 */
class VarEqualBlockView : BaseCalculateBlockView {

    private val mVarName: BlockEditText
    private val mRightCalculateBg: CalculateBgBlock

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        View.inflate(context, R.layout.layout_variable_equal_block, this)
        mVarName = findViewById(R.id.et_variable_equal_block_name)
        mRightCalculateBg = findViewById(R.id.bg_variable_equal_block_right)
        initView()
    }

    private fun initView() {

    }

    override fun clone(): IBaseBlock {
        val newObj = VarEqualBlockView(context)
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
            if (mRightCalculateBg.getChildAt(0) is AppCompatEditText) {
                (mRightCalculateBg.getChildAt(0) as AppCompatEditText).setText(it.toString())
            }
            return it
        }
        val value = mRightCalculateBg.calculateResult()
        VariableMap[key] = value
        return value
    }
}