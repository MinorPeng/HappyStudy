package com.hesheng1024.happystudy.custom.blocks.voice

import android.annotation.SuppressLint
import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.hesheng1024.base.utils.DensityUtil
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.custom.base.BaseBgBlockView
import com.hesheng1024.happystudy.custom.base.IBaseBlock
import com.hesheng1024.happystudy.custom.base.IRoleView

/**
 *
 * @author hesheng1024
 * @date 2020/3/30 21:05
 */
@SuppressLint("ViewConstructor")
class IncreaseVoiceBlockView : BaseBgBlockView {

    private val mEt: AppCompatEditText
    
    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        setBgColorId(R.color.colorVoicePurple)
        mEt = AppCompatEditText(context)
        initView()
    }

    private fun initView() {
        val tv = TextView(context)
        tv.setText(R.string.increase_voice)
        tv.setTextColor(ContextCompat.getColor(context, android.R.color.white))
        addView(tv)

        val lp = generateDefaultLayoutParams() as MarginLayoutParams
        lp.leftMargin = DensityUtil.dp2px(context, 8f)
        val mEt = EditText(context)
        mEt.minEms = 2
        mEt.setText(R.string.ten)
        mEt.setBackgroundResource(R.drawable.bg_et_circle_whilte)
        mEt.inputType = InputType.TYPE_CLASS_NUMBER
        mEt.setOnDragListener { v, event ->
            return@setOnDragListener true
        }
        addView(mEt, lp)
    }

    override suspend fun onRun(role: IRoleView) {
        role.increaseVolume(mEt.text.toString().toFloat())
    }

    override fun clone(): IBaseBlock {
        val newObj = IncreaseVoiceBlockView(context)
        newObj.layoutParams = this.layoutParams
        newObj.minimumWidth = measuredWidth
        newObj.minimumHeight = measuredHeight
        newObj.mEt.setText(this.mEt.text.toString())
        return newObj
    }
}