package com.hesheng1024.happystudy.custom.blocks.control

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.custom.blocks.base.BaseLinearBlockView
import com.hesheng1024.happystudy.custom.blocks.base.IBaseBlock
import com.hesheng1024.happystudy.custom.blocks.calculate.BaseCalculateBlockView
import com.hesheng1024.happystudy.custom.blocks.calculate.CalculateBgBlock
import com.hesheng1024.happystudy.custom.role.IRoleView
import kotlinx.coroutines.delay

/**
 *
 * @author hesheng1024
 * @date 2020/4/1 20:28
 */
@SuppressLint("ViewConstructor")
class WaitBlockView : BaseLinearBlockView {

    private val mCalculateBg: CalculateBgBlock
    
    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        setBgColorId(R.color.colorControlOrange500)
        View.inflate(context, R.layout.layout_wait_block, this)
        mCalculateBg = findViewById(R.id.bg_wait_block_sec)
        initView()
    }

    private fun initView() {
    }

    override suspend fun onRun(role: IRoleView) {
        delay((mCalculateBg.calculateResult() * 1000).toLong())
    }

    override fun clone(): IBaseBlock {
        val newObj = WaitBlockView(context)
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