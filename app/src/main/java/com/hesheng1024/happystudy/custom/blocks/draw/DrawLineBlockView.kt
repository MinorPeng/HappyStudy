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
 * @date 2020/5/10 17:35
 */
class DrawLineBlockView : BaseRelativeBlockView {

    private val mCalBgStartX: CalculateBgBlock
    private val mCalBgStartY: CalculateBgBlock
    private val mCalBgEndX: CalculateBgBlock
    private val mCalBgEndY: CalculateBgBlock
    private val mCalBgW: CalculateBgBlock
    private val mCalBgRotation: CalculateBgBlock
    private val mSpinnerColor: DrawColorSpinner

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0) {
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        setBgColorId(R.color.colorDrawRed500)
        View.inflate(context, R.layout.layout_draw_line_block, this)
        mCalBgStartX = findViewById(R.id.cal_bg_draw_line_block_start_x)
        mCalBgStartY = findViewById(R.id.cal_bg_draw_line_block_start_y)
        mCalBgEndX = findViewById(R.id.cal_bg_draw_line_block_end_x)
        mCalBgEndY = findViewById(R.id.cal_bg_draw_line_block_end_y)
        mCalBgW = findViewById(R.id.cal_bg_draw_line_block_w)
        mCalBgRotation = findViewById(R.id.cal_bg_draw_line_block_rotation)
        mSpinnerColor = findViewById(R.id.spinner_draw_line_block_color)
    }

    override fun clone(): IBaseBlock {
        val newObj = DrawLineBlockView(context)
        newObj.layoutParams = this.layoutParams
        newObj.minimumWidth = measuredWidth
        newObj.minimumHeight = measuredHeight
        newObj.mCalBgStartX.clone(mCalBgStartX)
        newObj.mCalBgStartY.clone(mCalBgStartY)
        newObj.mCalBgEndX.clone(mCalBgEndX)
        newObj.mCalBgEndY.clone(mCalBgEndX)
        newObj.mCalBgW.clone(mCalBgW)
        newObj.mCalBgRotation.clone(mCalBgRotation)
        newObj.mSpinnerColor.selectedIndex = mSpinnerColor.selectedIndex
        return newObj
    }

    override suspend fun onRun(role: IRoleView) {
        GlobalScope.launch(Dispatchers.Main) {
            val startX = mCalBgStartX.calculateResult()
            val startY = mCalBgStartY.calculateResult()
            val endX = mCalBgEndX.calculateResult()
            val endY = mCalBgEndY.calculateResult()
            val w = mCalBgW.calculateResult()
            val color = mSpinnerColor.getSelectedColor()
            val rotation = mCalBgRotation.calculateResult()
            role.drawLine(startX, startY, endX, endY, w, color, rotation)
        }
    }
}