package com.hesheng1024.happystudy.custom.blocks.control

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.CornerPathEffect
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.TEXT_SIZE_BLOCK_12
import com.hesheng1024.happystudy.custom.blocks.base.IBaseBlock
import com.hesheng1024.happystudy.custom.role.IRoleView

/**
 *
 * @author hesheng1024
 * @date 2020/4/1 11:02
 */
@SuppressLint("ViewConstructor")
class DeathCirculationBlockView : BaseControlBlockView {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        initView()
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        val tvCirculation = AppCompatTextView(context)
        tvCirculation.setText(R.string.circulation)
        tvCirculation.textSize = TEXT_SIZE_BLOCK_12
        tvCirculation.setTextColor(ContextCompat.getColor(context, android.R.color.white))
        tvCirculation.tag = ChildTag.TAG_TOP
        addView(tvCirculation)

        val ivCirculation = ImageView(context)
        ivCirculation.setImageResource(R.drawable.ic_circulation_16)
        ivCirculation.tag = ChildTag.TAG_BOTTOM
        addView(ivCirculation)
    }

    override fun drawBackground(canvas: Canvas, paint: Paint, path: Path, measuredW: Float, measuredH: Float) {
        path.reset()
        // top
        path.moveTo(0f, 0f)
        path.lineTo(IBaseBlock.DIS_TO_LEFT, 0f)
        path.lineTo(IBaseBlock.DIS_TO_LEFT + IBaseBlock.DIS_TO_TOP, IBaseBlock.DIS_TO_TOP)
        path.lineTo(IBaseBlock.DIS_TO_LEFT + IBaseBlock.DIS_TO_TOP + IBaseBlock.LINE_LEN, IBaseBlock.DIS_TO_TOP)
        path.lineTo(IBaseBlock.DIS_TO_LEFT + IBaseBlock.DIS_TO_TOP * 2 + IBaseBlock.LINE_LEN, 0f)
        path.lineTo(getTopViewW(), 0f)

        path.lineTo(getTopViewW(), getTopViewH())
        path.lineTo(IBaseBlock.DIS_TO_LEFT * 2 + IBaseBlock.DIS_TO_TOP * 2 + IBaseBlock.LINE_LEN, getTopViewH())
        path.lineTo(
            IBaseBlock.DIS_TO_LEFT * 2 + IBaseBlock.DIS_TO_TOP + IBaseBlock.LINE_LEN,
            getTopViewH() + IBaseBlock.DIS_TO_TOP
        )
        path.lineTo(IBaseBlock.DIS_TO_LEFT * 2 + IBaseBlock.DIS_TO_TOP, getTopViewH() + IBaseBlock.DIS_TO_TOP)
        path.lineTo(IBaseBlock.DIS_TO_LEFT * 2, getTopViewH())
        path.lineTo(IBaseBlock.DIS_TO_LEFT, getTopViewH())
        // bottom
        path.lineTo(IBaseBlock.DIS_TO_LEFT, measuredH - getTopViewH() - IBaseBlock.DIS_TO_TOP)
        path.lineTo(IBaseBlock.DIS_TO_LEFT * 2, measuredH - getTopViewH() - IBaseBlock.DIS_TO_TOP)
        path.lineTo(IBaseBlock.DIS_TO_LEFT * 2 + IBaseBlock.DIS_TO_TOP, (measuredH - getTopViewH()))
        path.lineTo(
            IBaseBlock.DIS_TO_LEFT * 2 + IBaseBlock.DIS_TO_TOP + IBaseBlock.LINE_LEN,
            (measuredH - getTopViewH())
        )
        path.lineTo(
            IBaseBlock.DIS_TO_LEFT * 2 + IBaseBlock.DIS_TO_TOP * 2 + IBaseBlock.LINE_LEN,
            measuredH - getTopViewH() - IBaseBlock.DIS_TO_TOP
        )
        path.lineTo(getTopViewW(), measuredH - getTopViewH() - IBaseBlock.DIS_TO_TOP)

        path.lineTo(getTopViewW(), measuredH)
        path.lineTo(0f, measuredH)
        path.close()

        paint.style = Paint.Style.FILL
        paint.color = getBgColor()
        paint.pathEffect = CornerPathEffect(IBaseBlock.sRadius)
        canvas.drawPath(path, paint)
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = IBaseBlock.sStrokeW
        paint.color = getBgBorderColor()
        canvas.drawPath(path, paint)
    }

    override suspend fun onRun(role: IRoleView) {
        while (true) {
            onChildRun(role)
        }
    }

    override fun clone(): IBaseBlock {
        val newObj = DeathCirculationBlockView(context)
        newObj.layoutParams = this.layoutParams
        newObj.minimumWidth = measuredWidth
        newObj.minimumHeight = measuredHeight
        return newObj
    }
}