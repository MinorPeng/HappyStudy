package com.hesheng1024.happystudy.custom.blocks.control

import android.annotation.SuppressLint
import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.hesheng1024.base.utils.dp2px
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.custom.BlockEditText
import com.hesheng1024.happystudy.custom.BlockTextView
import com.hesheng1024.happystudy.custom.base.BaseBgBlockView
import com.hesheng1024.happystudy.custom.base.IBaseBlock
import com.hesheng1024.happystudy.custom.base.IRoleView
import com.hesheng1024.happystudy.custom.blocks.calculate.BaseCalculateBlockView
import com.hesheng1024.happystudy.custom.blocks.calculate.CalculateBgBlock
import kotlinx.coroutines.delay

/**
 *
 * @author hesheng1024
 * @date 2020/4/1 20:28
 */
@SuppressLint("ViewConstructor")
class WaitBlockView : BaseBgBlockView {

    private val mCalculateBg: CalculateBgBlock
    
    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        setBgColorId(R.color.colorControlYellow)
        mCalculateBg = CalculateBgBlock(context)
        initView()
    }

    private fun initView() {
        val tvWait = BlockTextView(context)
        tvWait.setText(R.string.wait)
        addView(tvWait)

        val lpEtSeconds = generateDefaultLayoutParams() as MarginLayoutParams
        lpEtSeconds.leftMargin = dp2px(context, 4f)
        lpEtSeconds.rightMargin = dp2px(context, 4f)
        val etSeconds = BlockEditText(context)
        etSeconds.inputType = InputType.TYPE_CLASS_NUMBER
        mCalculateBg.addView(etSeconds)
        addView(mCalculateBg, lpEtSeconds)


        val tvSeconds = BlockTextView(context)
        tvSeconds.setText(R.string.seconds)
        addView(tvSeconds)
    }

    override suspend fun onRun(role: IRoleView) {
        delay(mCalculateBg.calculateResult().toLong() * 1000)
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