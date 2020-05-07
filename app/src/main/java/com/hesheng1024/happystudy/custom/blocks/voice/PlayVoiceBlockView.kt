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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.angmarch.views.NiceSpinner

/**
 *
 * @author hesheng1024
 * @date 2020/3/30 21:03
 */
@SuppressLint("ViewConstructor")
class PlayVoiceBlockView : BaseLinearBlockView {

    private var mSelectPos = 0

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        setBgColorId(R.color.colorVoicePurple500)
        View.inflate(context, R.layout.layout_play_voice_block, this)
        initView()
    }

    private fun initView() {
        val spinner = findViewById<NiceSpinner>(R.id.spinner_play_voice_block)

        spinner.setLines(1)
        spinner.setArrowDrawable(R.drawable.ic_spinner_white_16)
        spinner.setTextColor(ContextCompat.getColor(context, android.R.color.white))
        spinner.setArrowTintColor(ContextCompat.getColor(context, android.R.color.white))
        spinner.setBackgroundResource(R.drawable.bg_spinner_circle_purple)
        spinner.setOnSpinnerItemSelectedListener { parent, view, position, id ->
            mSelectPos = position
        }
    }

    override suspend fun onRun(role: IRoleView) {
        val rawId = when(mSelectPos) {
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
        return newObj
    }
}