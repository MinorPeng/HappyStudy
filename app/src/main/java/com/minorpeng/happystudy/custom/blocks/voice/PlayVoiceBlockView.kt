package com.minorpeng.happystudy.custom.blocks.voice

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.minorpeng.base.utils.DensityUtil
import com.minorpeng.happystudy.R
import com.minorpeng.happystudy.custom.base.BaseBgBlockView

/**
 *
 * @author MinorPeng
 * @date 2020/3/30 21:03
 */
@SuppressLint("ViewConstructor")
class PlayVoiceBlockView(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0) :
    BaseBgBlockView(context, attrs, defStyleAttr, defStyleRes) {

    init {
        setBgColorId(R.color.colorVoicePurple)
        initView()
    }

    private fun initView() {
        val whiteColor = ContextCompat.getColor(context, android.R.color.white)
        val tv = TextView(context)
        tv.setText(R.string.when_str)
        tv.setTextColor(whiteColor)
        addView(tv)

        val lp = generateDefaultLayoutParams() as MarginLayoutParams
        lp.leftMargin = DensityUtil.dp2px(context, 8f)
        val spinner = Spinner(context)
        spinner.setBackgroundResource(R.drawable.bg_spinner_circle_purple)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (view is TextView) {
                    view.setTextColor(whiteColor)
                    view.gravity = Gravity.CENTER
                }
            }
        }
        spinner.adapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, listOf("song", "喵", "汪"))
        addView(spinner, lp)
    }

    override fun onRun(role: View) {

    }
}