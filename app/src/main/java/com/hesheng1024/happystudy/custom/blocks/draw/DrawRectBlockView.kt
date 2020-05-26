package com.hesheng1024.happystudy.custom.blocks.draw

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.view.View
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.custom.blocks.BlockEditText
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
 * @date 2020/5/7 15:57
 */
class DrawRectBlockView : BaseRelativeBlockView {

    private val mCalBgX1: CalculateBgBlock
    private val mCalBgY1: CalculateBgBlock
    private val mCalBgX2: CalculateBgBlock
    private val mCalBgY2: CalculateBgBlock
    private val mCalBgW: CalculateBgBlock
    private val mCalBgRotation: CalculateBgBlock
    private val mEtName: BlockEditText
    private val mSpinnerStyle: DrawStyleSpinner
    private val mSpinnerColor: DrawColorSpinner

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0) {
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        setBgColorId(R.color.colorDrawRed500)
        View.inflate(context, R.layout.layout_draw_rect_block, this)
        mCalBgX1 = findViewById(R.id.cal_bg_draw_rect_block_x1)
        mCalBgY1 = findViewById(R.id.cal_bg_draw_rect_block_y1)
        mCalBgX2 = findViewById(R.id.cal_bg_draw_rect_block_x2)
        mCalBgY2 = findViewById(R.id.cal_bg_draw_rect_block_y2)
        mCalBgW = findViewById(R.id.cal_bg_draw_rect_block_w)
        mCalBgRotation = findViewById(R.id.cal_bg_draw_rect_block_rotation)
        mEtName = findViewById(R.id.et_draw_rect_block_name)
        mSpinnerStyle = findViewById(R.id.spinner_draw_rect_block_style)
        mSpinnerColor = findViewById(R.id.spinner_draw_rect_block_color)
        initView()
    }

    private fun initView() {
        mEtName.inputType = InputType.TYPE_CLASS_TEXT
    }

    override fun clone(): IBaseBlock {
        val newObj = DrawRectBlockView(context)
        newObj.layoutParams = this.layoutParams
        newObj.minimumWidth = measuredWidth
        newObj.minimumHeight = measuredHeight
        newObj.mCalBgX1.clone(mCalBgX1)
        newObj.mCalBgY1.clone(mCalBgY1)
        newObj.mCalBgX2.clone(mCalBgX2)
        newObj.mCalBgY2.clone(mCalBgY2)
        newObj.mCalBgW.clone(mCalBgW)
        newObj.mCalBgRotation.clone(mCalBgRotation)
        newObj.mEtName.setText(mEtName.text.toString())
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
            val w = mCalBgW.calculateResult()
            val rotation = mCalBgRotation.calculateResult()
            val name = mEtName.text.toString()
            val color = mSpinnerColor.getSelectedColor()
            val style = mSpinnerStyle.getSelectedStyle()
            role.drawRect(x1, y1, x2, y2, w, color, style, name, rotation)
        }
    }
}