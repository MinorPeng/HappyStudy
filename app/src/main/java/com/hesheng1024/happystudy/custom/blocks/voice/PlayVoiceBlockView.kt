package com.hesheng1024.happystudy.custom.blocks.voice

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.custom.blocks.base.BaseLinearBlockView
import com.hesheng1024.happystudy.custom.blocks.base.IBaseBlock
import com.hesheng1024.happystudy.custom.role.IRoleView
import com.jaredrummler.materialspinner.MaterialSpinner
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 *
 * @author hesheng1024
 * @date 2020/3/30 21:03
 */
@SuppressLint("ViewConstructor")
class PlayVoiceBlockView : BaseLinearBlockView {

    private val mSpinner: MaterialSpinner

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        setBgColorId(R.color.colorVoicePurple500)
        View.inflate(context, R.layout.layout_play_voice_block, this)
        mSpinner = findViewById(R.id.spinner_play_voice_block)
        initView()
    }

    private fun initView() {
        mSpinner.setLines(1)
        mSpinner.setItems(context.resources.getStringArray(R.array.voices).toList())
        mSpinner.setTextColor(ContextCompat.getColor(context, android.R.color.white))
        mSpinner.setArrowColor(ContextCompat.getColor(context, android.R.color.white))
        mSpinner.setBackgroundColor(ContextCompat.getColor(context, R.color.colorVoicePurple700))
        mSpinner.setBackgroundResource(R.drawable.bg_spinner_circle_purple)
        mSpinner.selectedIndex = 0
    }

    override suspend fun onRun(role: IRoleView) {
        val rawId = when (mSpinner.selectedIndex) {
            0 -> R.raw.voice1
            1 -> R.raw.voice2
            2 -> R.raw.voice3
            else -> R.raw.voice1
        }
        GlobalScope.launch(Dispatchers.Main) {
            role.playVoice(rawId)
        }
    }

    override fun clone(): IBaseBlock {
        val newObj = PlayVoiceBlockView(context)
        newObj.layoutParams = this.layoutParams
        newObj.minimumWidth = measuredWidth
        newObj.minimumHeight = measuredHeight
        newObj.mSpinner.selectedIndex = mSpinner.selectedIndex
        return newObj
    }
}