package com.hesheng1024.happystudy.custom.blocks.calculate

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatEditText
import com.hesheng1024.happystudy.custom.base.IBaseBlock
import com.hesheng1024.happystudy.custom.base.IRoleView

/**
 *
 * @author hesheng1024
 * @date 2020/4/17 21:39
 */
class CalculateBgBlock : BaseCalculateBlockView {
    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        setBgColorId(android.R.color.white)
        setStatus(IBaseBlock.Status.STATUS_NONE)
        this.setPadding(0,0, 0, 0)
    }

    override suspend fun onRun(role: IRoleView) {

    }

    override fun clone(): IBaseBlock {
        val newObj = CalculateBgBlock(context)
        newObj.layoutParams = this.layoutParams
        when (val child = getChildAt(0)) {
            null -> {
                newObj.minimumWidth = measuredWidth
                newObj.minimumHeight = measuredHeight
            }
            is AppCompatEditText -> {
                val newEt = AppCompatEditText(context)
                newEt.setText(child.text.toString())
                newObj.addView(newEt)
            }
            is BaseCalculateBlockView -> {
                newObj.addView(child.clone() as BaseCalculateBlockView)
            }
        }
        return newObj
    }

    override fun calculateResult(): Float {
        return when(val child = getChildAt(0)) {
            null -> -1f
            is AppCompatEditText -> child.text.toString().toFloat()
            is BaseCalculateBlockView -> child.calculateResult()
            else -> -1f
        }
    }

    override fun addView(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        removeAllViews()
        super.addView(child, index, params)
    }

    override fun addViewInLayout(
        child: View?,
        index: Int,
        params: ViewGroup.LayoutParams?,
        preventRequestLayout: Boolean
    ): Boolean {
        removeAllViews()
        return super.addViewInLayout(child, index, params, preventRequestLayout)
    }
}