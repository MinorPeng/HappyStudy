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
 * @date 2020/5/7 12:03
 */
class DrawSquareBlockView : BaseRelativeBlockView {

    private val mEtCx: BlockEditText
    private val mEtCy: BlockEditText
    private val mEtLen: BlockEditText
    private val mEtW: BlockEditText
    private val mEtRotation: BlockEditText
    private val mSpinnerStyle: DrawStyleSpinner
    private val mSpinnerColor: DrawColorSpinner

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0) {
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        setBgColorId(R.color.colorDrawRed500)
        View.inflate(context, R.layout.layout_draw_square_block, this)
        mEtCx = findViewById(R.id.et_draw_square_block_cx)
        mEtCy = findViewById(R.id.et_draw_square_block_cy)
        mEtLen = findViewById(R.id.et_draw_square_block_bl)
        mEtW = findViewById(R.id.et_draw_square_block_w)
        mEtRotation = findViewById(R.id.et_draw_square_block_rotation)
        mSpinnerStyle = findViewById(R.id.spinner_draw_square_block_style)
        mSpinnerColor = findViewById(R.id.spinner_draw_square_block_color)
    }

    override fun clone(): IBaseBlock {
        val newObj = DrawSquareBlockView(context)
        newObj.layoutParams = this.layoutParams
        newObj.minimumWidth = measuredWidth
        newObj.minimumHeight = measuredHeight
        newObj.mEtCx.setText(mEtCx.text.toString())
        newObj.mEtCy.setText(mEtCy.text.toString())
        newObj.mEtLen.setText(mEtLen.text.toString())
        newObj.mEtW.setText(mEtW.text.toString())
        newObj.mEtRotation.setText(mEtRotation.text.toString())
        newObj.mSpinnerStyle.selectedIndex = mSpinnerStyle.selectedIndex
        newObj.mSpinnerColor.selectedIndex = mSpinnerColor.selectedIndex
        return newObj
    }

    override suspend fun onRun(role: IRoleView) {
        GlobalScope.launch(Dispatchers.Main) {
            val cx = mEtCx.text.toString().toFloat()
            val cy = mEtCy.text.toString().toFloat()
            val len = mEtLen.text.toString().toFloat()
            val l = cx - len / 2
            val t = cy + len / 2
            val r = cx + len / 2
            val b = cy - len / 2
            val w = mEtW.text.toString().toFloat()
            val color = mSpinnerColor.getSelectedColor()
            val style = mSpinnerStyle.getSelectedStyle()
            val rotation = mEtRotation.text.toString().toFloat()
            role.drawRect(l, t, r, b, w, color, style, rotation)
        }
    }
}