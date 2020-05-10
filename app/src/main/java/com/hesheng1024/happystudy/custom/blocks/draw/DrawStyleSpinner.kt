package com.hesheng1024.happystudy.custom.blocks.draw

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.hesheng1024.happystudy.R
import org.angmarch.views.NiceSpinner

/**
 *
 * @author hesheng1024
 * @date 2020/5/7 10:27
 */
class DrawStyleSpinner : NiceSpinner {

    private val mStyleMap = LinkedHashMap<String, Paint.Style>(4)
    private var mSelectStyle: Paint.Style? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init()
    }

    private fun init() {
        initData()
        initView()
    }

    private fun initData() {
        mStyleMap["描边"] = Paint.Style.STROKE
        mStyleMap["填充"] = Paint.Style.FILL
        mStyleMap["填充和描边"] = Paint.Style.FILL_AND_STROKE
    }

    private fun initView() {
        val list = mStyleMap.keys.toList()
        mSelectStyle = mStyleMap[list[0]]
        this.attachDataSource(list)
        this.setOnSpinnerItemSelectedListener { parent, view, position, id ->
            val style = mStyleMap[parent.getItemAtPosition(position)]
            mSelectStyle = style
        }
        this.setLines(1)
        this.setArrowDrawable(R.drawable.ic_spinner_white_16)
        this.setTextColor(ContextCompat.getColor(context, android.R.color.white))
        this.setArrowTintColor(ContextCompat.getColor(context, android.R.color.white))
        this.setBackgroundResource(R.drawable.bg_spinner_circle_red)
    }

    fun getSelectedStyle(): Paint.Style = mSelectStyle ?: Paint.Style.STROKE
}