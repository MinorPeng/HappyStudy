package com.hesheng1024.happystudy.custom.blocks.draw

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.custom.blocks.BlockEditText
import com.hesheng1024.happystudy.custom.blocks.base.BaseRelativeBlockView
import com.hesheng1024.happystudy.custom.blocks.base.IBaseBlock
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

    private val mEtX1: BlockEditText
    private val mEtY1: BlockEditText
    private val mEtX2: BlockEditText
    private val mEtY2: BlockEditText
    private val mEtW: BlockEditText
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
        mEtX1 = findViewById(R.id.et_draw_rect_block_x1)
        mEtY1 = findViewById(R.id.et_draw_rect_block_y1)
        mEtX2 = findViewById(R.id.et_draw_rect_block_x2)
        mEtY2 = findViewById(R.id.et_draw_rect_block_y2)
        mEtW = findViewById(R.id.et_draw_rect_block_w)
        mSpinnerStyle = findViewById(R.id.spinner_draw_rect_block_style)
        mSpinnerColor = findViewById(R.id.spinner_draw_rect_block_color)
    }

    override fun clone(): IBaseBlock {
        val newObj = DrawRectBlockView(context)
        newObj.layoutParams = this.layoutParams
        newObj.minimumWidth = measuredWidth
        newObj.minimumHeight = measuredHeight
        newObj.mEtX1.setText(mEtX1.text.toString())
        newObj.mEtY1.setText(mEtY1.text.toString())
        newObj.mEtX2.setText(mEtX2.text.toString())
        newObj.mEtY2.setText(mEtX2.text.toString())
        newObj.mEtW.setText(mEtW.text.toString())
        newObj.mSpinnerStyle.selectedIndex = mSpinnerStyle.selectedIndex
        newObj.mSpinnerColor.selectedIndex = mSpinnerColor.selectedIndex
        return newObj
    }

    override suspend fun onRun(role: IRoleView) {
        GlobalScope.launch(Dispatchers.Main) {
            val x1 = mEtX1.text.toString().toFloat()
            val y1 = mEtY1.text.toString().toFloat()
            val x2 = mEtX2.text.toString().toFloat()
            val y2 = mEtY2.text.toString().toFloat()
            val w = mEtW.text.toString().toFloat()
            val color = mSpinnerColor.getSelectedColor()
            val style = mSpinnerStyle.getSelectedStyle()
            role.drawRect(x1, y1, x2, y2, w, color, style)
        }
    }
}