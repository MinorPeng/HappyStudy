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
class StopAllVoiceBlockView : BaseTextBlockView {

    constructor(context: Context): this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        setText(R.string.stop_all_voice)
    }

    override fun getBgColorId(): Int {
        return R.color.colorVoicePurple
    }

    override fun onRun(role: View) {

    }
}