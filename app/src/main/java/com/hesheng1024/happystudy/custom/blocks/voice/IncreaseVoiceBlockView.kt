package com.hesheng1024.happystudy.custom.blocks.voice

import android.annotation.SuppressLint
import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.view.View
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.custom.blocks.BlockEditText
import com.hesheng1024.happystudy.custom.blocks.base.BaseLinearBlockView
import com.hesheng1024.happystudy.custom.blocks.base.IBaseBlock
import com.hesheng1024.happystudy.custom.role.IRoleView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 *
 * @author hesheng1024
 * @date 2020/3/30 21:05
 */
@SuppressLint("ViewConstructor")
class IncreaseVoiceBlockView : BaseLinearBlockView {

    private val mEt: BlockEditText
    
    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        setBgColorId(R.color.colorVoicePurple500)
        View.inflate(context, R.layout.layout_increase_voice_block, this)
        mEt = findViewById(R.id.et_increase_voice_num)
        initView()
    }

    private fun initView() {
        mEt.inputType = InputType.TYPE_CLASS_NUMBER
    }

    override suspend fun onRun(role: IRoleView) {
        GlobalScope.launch(Dispatchers.Main) {
            role.increaseVolume(mEt.text.toString().toFloat())
        }
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