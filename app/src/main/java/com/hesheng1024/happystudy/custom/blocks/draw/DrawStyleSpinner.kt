package com.hesheng1024.happystudy.custom.blocks.draw

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.TEXT_SIZE_BLOCK_10
import com.hesheng1024.spinner.MaterialSpinner

/**
 *
 * @author hesheng1024
 * @date 2020/5/7 10:27
 */
class DrawStyleSpinner : MaterialSpinner {

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
        this.setItems(list)
        this.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(view: MaterialSpinner?, position: Int, id: Long, item: Any) {
                mSelectStyle = mStyleMap[item]
            }
        })
        this.textSize = TEXT_SIZE_BLOCK_10
        this.setTextColor(ContextCompat.getColor(context, android.R.color.white))
        this.setArrowColor(ContextCompat.getColor(context, android.R.color.white))
        this.setBackgroundResource(R.drawable.bg_spinner_circle_red)
    }

    fun getSelectedStyle(): Paint.Style = mSelectStyle ?: Paint.Style.STROKE

    fun clone(other: DrawStyleSpinner) {
        this.setSelectedIndex(other.getSelectedIndex())
    }
}