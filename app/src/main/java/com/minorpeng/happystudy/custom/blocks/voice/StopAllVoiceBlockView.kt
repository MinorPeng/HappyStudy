package com.minorpeng.happystudy.custom.blocks.voice

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.minorpeng.happystudy.R
import com.minorpeng.happystudy.custom.base.BaseTextBlockView

/**
 *
 * @author MinorPeng
 * @date 2020/3/30 21:02
 */
class StopAllVoiceBlockView(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    BaseTextBlockView(context, attrs, defStyleAttr) {

    init {
        setBgColorId(R.color.colorVoicePurple)
        setText(R.string.stop_all_voice)
    }

    override fun onRun(role: View) {

    }
}