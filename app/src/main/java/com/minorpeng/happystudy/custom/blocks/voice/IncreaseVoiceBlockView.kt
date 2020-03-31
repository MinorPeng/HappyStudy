package com.minorpeng.happystudy.custom.blocks.voice

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import com.minorpeng.happystudy.R
import com.minorpeng.happystudy.custom.base.BaseBgBlockView

/**
 *
 * @author MinorPeng
 * @date 2020/3/30 21:05
 */
class IncreaseVoiceBlockView : BaseBgBlockView {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr, 0) {
        LayoutInflater.from(context).inflate(R.layout.layout_increase_voice_block, this)
    }

    override fun getBgColorId(): Int {
        return R.color.colorVoicePurple
    }

    override fun onRun(role: View) {

    }
}