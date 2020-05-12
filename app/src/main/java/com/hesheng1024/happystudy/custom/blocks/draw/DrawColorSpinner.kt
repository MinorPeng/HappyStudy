package com.hesheng1024.happystudy.custom.blocks.draw

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.TextView
import com.hesheng1024.happystudy.R
import com.jaredrummler.materialspinner.MaterialSpinner

/**
 *
 * @author hesheng1024
 * @date 2020/5/7 10:08
 */
class DrawColorSpinner : MaterialSpinner {

    private val mColorMap = LinkedHashMap<String, Int>(8)
    private var mSelectColor: Int? = null

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
        mColorMap["黑色"] = Color.BLACK
        mColorMap["红色"] = Color.RED
        mColorMap["蓝色"] = Color.BLUE
        mColorMap["绿色"] = Color.GREEN
        mColorMap["黄色"] = Color.YELLOW
        mColorMap["灰色"] = Color.GRAY
    }

    private fun initView() {
        val list = mColorMap.keys.toList()
        mSelectColor = mColorMap[list[0]]
        this.setItems(list)
        this.setOnItemSelectedListener { view, position, id, item ->
            val color = mColorMap[item]
            if (view is TextView && color != null) {
                this.setTextColor(color)
                this.setArrowColor(color)
                mSelectColor = color
            }
        }
        this.textSize = 10f
        this.setLines(1)
        this.setBackgroundResource(R.drawable.bg_spinner_circle_white)
        mSelectColor?.let {
            this.setTextColor(it)
            this.setArrowColor(it)
        }
    }

    fun getSelectedColor(): Int = mSelectColor ?: Color.BLACK

    fun clone(other: DrawColorSpinner) {
        this.selectedIndex = other.selectedIndex
        this.setTextColor(other.getSelectedColor())
        this.setArrowColor(other.getSelectedColor())
    }
}