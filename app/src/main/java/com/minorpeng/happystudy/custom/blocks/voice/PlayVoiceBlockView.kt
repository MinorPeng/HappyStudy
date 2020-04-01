package com.minorpeng.happystudy.custom.blocks.voice

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.minorpeng.happystudy.R
import com.minorpeng.happystudy.custom.base.BaseBgBlockView

/**
 *
 * @author MinorPeng
 * @date 2020/3/30 21:03
 */
class PlayVoiceBlockView : BaseBgBlockView {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr, 0) {
        LayoutInflater.from(context).inflate(R.layout.layout_play_voice_block, this)
        initView()
    }

    private fun initView() {
        val spinner = findViewById<Spinner>(R.id.spinner_play_voice)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (view is TextView) {
                    view.setTextColor(ContextCompat.getColor(context, android.R.color.white))
                    view.gravity = Gravity.CENTER
                }
            }

        }
    }

    override fun getBgColorId(): Int {
        return R.color.colorVoicePurple
    }

    override fun onRun(role: View) {

    }
}