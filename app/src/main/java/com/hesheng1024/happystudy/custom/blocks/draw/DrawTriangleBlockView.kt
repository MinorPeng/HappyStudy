package com.hesheng1024.happystudy.custom.blocks.draw

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.custom.blocks.base.BaseRelativeBlockView
import com.hesheng1024.happystudy.custom.blocks.base.IBaseBlock
import com.hesheng1024.happystudy.custom.blocks.calculate.CalculateBgBlock
import com.hesheng1024.happystudy.custom.role.IRoleView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 *
 * @author hesheng1024
 * @date 2020/5/7 12:04
 */
class DrawTriangleBlockView : BaseRelativeBlockView {
    private val mCalBgX1: CalculateBgBlock
    private val mCalBgY1: CalculateBgBlock
    private val mCalBgX2: CalculateBgBlock
    private val mCalBgY2: CalculateBgBlock
    private val mCalBgX3: CalculateBgBlock
    private val mCalBgY3: CalculateBgBlock
    private val mCalBgW: CalculateBgBlock
    private val mCalBgRotation: CalculateBgBlock
    private val mSpinnerStyle: DrawStyleSpinner
    private val mSpinnerColor: DrawColorSpinner

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0) {
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        setBgColorId(R.color.colorDrawRed500)
        View.inflate(context, R.layout.layout_draw_triangle_block, this)
        mCalBgX1 = findViewById(R.id.cal_bg_draw_triangle_block_x1)
        mCalBgY1 = findViewById(R.id.cal_bg_draw_triangle_block_y1)
        mCalBgX2 = findViewById(R.id.cal_bg_draw_triangle_block_x2)
        mCalBgY2 = findViewById(R.id.cal_bg_draw_triangle_block_y2)
        mCalBgX3 = findViewById(R.id.cal_bg_draw_triangle_block_x3)
        mCalBgY3 = findViewById(R.id.cal_bg_draw_triangle_block_y3)
        mCalBgW = findViewById(R.id.cal_bg_draw_triangle_block_w)
        mCalBgRotation = findViewById(R.id.cal_bg_draw_triangle_block_rotation)
        mSpinnerStyle = findViewById(R.id.spinner_draw_triangle_block_style)
        mSpinnerColor = findViewById(R.id.spinner_draw_triangle_block_color)
    }

    override fun clone(): IBaseBlock {
        val newObj = DrawTriangleBlockView(context)
        newObj.layoutParams = this.layoutParams
        newObj.minimumWidth = measuredWidth
        newObj.minimumHeight = measuredHeight
        newObj.mCalBgX1.clone(mCalBgX1)
        newObj.mCalBgY1.clone(mCalBgY1)
        newObj.mCalBgX2.clone(mCalBgX2)
        newObj.mCalBgY2.clone(mCalBgY2)
        newObj.mCalBgX3.clone(mCalBgX3)
        newObj.mCalBgY3.clone(mCalBgY3)
        newObj.mCalBgW.clone(mCalBgW)
        newObj.mCalBgRotation.clone(mCalBgRotation)
        newObj.mSpinnerStyle.clone(mSpinnerStyle)
        newObj.mSpinnerColor.clone(mSpinnerColor)
        return newObj
    }

    override suspend fun onRun(role: IRoleView) {
        GlobalScope.launch(Dispatchers.Main) {
            val x1 = mCalBgX1.calculateResult()
            val y1 = mCalBgY1.calculateResult()
            val x2 = mCalBgX2.calculateResult()
            val y2 = mCalBgY2.calculateResult()
            val x3 = mCalBgX3.calculateResult()
            val y3 = mCalBgY3.calculateResult()
            val w = mCalBgW.calculateResult()
            val color = mSpinnerColor.getSelectedColor()
            val style = mSpinnerStyle.getSelectedStyle()
            val rotation = mCalBgRotation.calculateResult()
            role.drawTriangle(x1, y1, x2, y2, x3, y3, w, color, style, rotation)
        }
    }
}