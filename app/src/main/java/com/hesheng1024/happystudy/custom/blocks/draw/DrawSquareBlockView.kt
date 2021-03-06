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
 * @date 2020/5/7 12:03
 */
class DrawSquareBlockView : BaseRelativeBlockView {

    private val mCalBgCx: CalculateBgBlock
    private val mCalBgCy: CalculateBgBlock
    private val mCalBgLen: CalculateBgBlock
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
        View.inflate(context, R.layout.layout_draw_square_block, this)
        mCalBgCx = findViewById(R.id.cal_bg_draw_square_block_cx)
        mCalBgCy = findViewById(R.id.cal_bg_draw_square_block_cy)
        mCalBgLen = findViewById(R.id.cal_bg_draw_square_block_bl)
        mCalBgW = findViewById(R.id.cal_bg_draw_square_block_w)
        mCalBgRotation = findViewById(R.id.cal_bg_draw_square_block_rotation)
        mEtName = findViewById(R.id.et_draw_square_block_name)
        mSpinnerStyle = findViewById(R.id.spinner_draw_square_block_style)
        mSpinnerColor = findViewById(R.id.spinner_draw_square_block_color)
        initView()
    }

    private fun initView() {
        mEtName.inputType = InputType.TYPE_CLASS_TEXT
    }

    override fun clone(): IBaseBlock {
        val newObj = DrawSquareBlockView(context)
        newObj.layoutParams = this.layoutParams
        newObj.minimumWidth = measuredWidth
        newObj.minimumHeight = measuredHeight
        newObj.mCalBgCx.clone(mCalBgCx)
        newObj.mCalBgCy.clone(mCalBgCy)
        newObj.mCalBgLen.clone(mCalBgLen)
        newObj.mCalBgW.clone(mCalBgW)
        newObj.mEtName.setText(mEtName.text.toString())
        newObj.mCalBgRotation.clone(mCalBgRotation)
        newObj.mSpinnerStyle.clone(mSpinnerStyle)
        newObj.mSpinnerColor.clone(mSpinnerColor)
        return newObj
    }

    override suspend fun onRun(role: IRoleView) {
        GlobalScope.launch(Dispatchers.Main) {
            val cx = mCalBgCx.calculateResult()
            val cy = mCalBgCy.calculateResult()
            val len = mCalBgLen.calculateResult()
            val l = cx - len / 2
            val t = cy + len / 2
            val r = cx + len / 2
            val b = cy - len / 2
            val w = mCalBgW.calculateResult()
            val color = mSpinnerColor.getSelectedColor()
            val style = mSpinnerStyle.getSelectedStyle()
            val rotation = mCalBgRotation.calculateResult()
            val name = mEtName.text.toString()
            role.drawRect(l, t, r, b, w, color, style, name, rotation)
        }
    }
}