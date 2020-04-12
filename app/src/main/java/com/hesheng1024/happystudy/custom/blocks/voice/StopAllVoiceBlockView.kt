package com.hesheng1024.happystudy.custom.blocks.voice

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.custom.base.BaseTextBlockView

/**
 *
 * @author hesheng1024
 * @date 2020/3/30 21:02
 */
class StopAllVoiceBlockView : BaseTextBlockView {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        setBgColorId(R.color.colorVoicePurple)
        setText(R.string.stop_all_voice)
    }

    override fun onRun(role: View) {

    }
}