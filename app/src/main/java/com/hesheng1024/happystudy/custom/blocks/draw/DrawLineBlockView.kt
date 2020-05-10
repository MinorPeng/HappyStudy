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
 * @date 2020/5/10 17:35
 */
class DrawLineBlockView : BaseRelativeBlockView {

    private val mEtStartX: BlockEditText
    private val mEtStartY: BlockEditText
    private val mEtEndX: BlockEditText
    private val mEtEndY: BlockEditText
    private val mEtW: BlockEditText
    private val mEtRotation: BlockEditText
    private val mSpinnerColor: DrawColorSpinner

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0) {
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        setBgColorId(R.color.colorDrawRed500)
        View.inflate(context, R.layout.layout_draw_line_block, this)
        mEtStartX = findViewById(R.id.et_draw_line_block_start_x)
        mEtStartY = findViewById(R.id.et_draw_line_block_start_y)
        mEtEndX = findViewById(R.id.et_draw_line_block_end_x)
        mEtEndY = findViewById(R.id.et_draw_line_block_end_y)
        mEtW = findViewById(R.id.et_draw_line_block_w)
        mEtRotation = findViewById(R.id.et_draw_line_block_rotation)
        mSpinnerColor = findViewById(R.id.spinner_draw_line_block_color)
    }

    override fun clone(): IBaseBlock {
        val newObj = DrawLineBlockView(context)
        newObj.layoutParams = this.layoutParams
        newObj.minimumWidth = measuredWidth
        newObj.minimumHeight = measuredHeight
        newObj.mEtStartX.setText(mEtStartX.text.toString())
        newObj.mEtStartY.setText(mEtStartY.text.toString())
        newObj.mEtEndX.setText(mEtEndX.text.toString())
        newObj.mEtEndY.setText(mEtEndX.text.toString())
        newObj.mEtW.setText(mEtW.text.toString())
        newObj.mEtRotation.setText(mEtRotation.text.toString())
        newObj.mSpinnerColor.selectedIndex = mSpinnerColor.selectedIndex
        return newObj
    }

    override suspend fun onRun(role: IRoleView) {
        GlobalScope.launch(Dispatchers.Main) {
            val startX = mEtStartX.text.toString().toFloat()
            val startY = mEtStartY.text.toString().toFloat()
            val endX = mEtEndX.text.toString().toFloat()
            val endY = mEtEndY.text.toString().toFloat()
            val w = mEtW.text.toString().toFloat()
            val color = mSpinnerColor.getSelectedColor()
            val rotation = mEtRotation.text.toString().toFloat()
            role.drawLine(startX, startY, endX, endY, w, color, rotation)
        }
    }
}