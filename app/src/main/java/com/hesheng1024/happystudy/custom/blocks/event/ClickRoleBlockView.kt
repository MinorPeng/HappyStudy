package com.hesheng1024.happystudy.custom.blocks.event

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import com.hesheng1024.base.utils.DensityUtil
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.custom.base.BaseTextBlockView
import com.hesheng1024.happystudy.custom.base.IBaseBlock
import com.hesheng1024.happystudy.custom.base.IRoleView

/**
 *
 * @author hesheng1024
 * @date 2020/3/30 21:44
 */
class ClickRoleBlockView : BaseTextBlockView {

    private val mDisTop = DensityUtil.dp2px(context, 16f).toFloat()

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        setBgColorId(R.color.colorEventYellow)
        this.setPadding(
            (IBaseBlock.DIS_TO_TOP * 2).toInt(),
            (IBaseBlock.DIS_TO_TOP * 2 + mDisTop).toInt(),
            (IBaseBlock.DIS_TO_TOP * 2).toInt(),
            (IBaseBlock.DIS_TO_TOP * 3).toInt()
        )
        setText(R.string.click_role)
    }

    override fun drawBackground(canvas: Canvas, paint: Paint, path: Path, measuredW: Float, measuredH: Float) {
        val rectF = RectF(-mDisTop, 0f, measuredWidth - IBaseBlock.LINE_LEN, measuredWidth - IBaseBlock.LINE_LEN + mDisTop * 2)
        path.reset()
        path.moveTo(0f, mDisTop)
        path.arcTo(rectF, -138f, 90f)
        path.lineTo(measuredW, mDisTop)
        path.lineTo(measuredW, measuredHeight - IBaseBlock.DIS_TO_TOP)
        path.lineTo(IBaseBlock.DIS_TO_LEFT + IBaseBlock.DIS_TO_TOP * 2 + IBaseBlock.LINE_LEN, measuredHeight - IBaseBlock.DIS_TO_TOP)
        path.lineTo(IBaseBlock.DIS_TO_LEFT + IBaseBlock.DIS_TO_TOP + IBaseBlock.LINE_LEN, measuredH)
        path.lineTo(IBaseBlock.DIS_TO_LEFT + IBaseBlock.DIS_TO_TOP, measuredH)
        path.lineTo(IBaseBlock.DIS_TO_LEFT, measuredH - IBaseBlock.DIS_TO_TOP)
        path.lineTo(0f, measuredH - IBaseBlock.DIS_TO_TOP)
        path.lineTo(0f, IBaseBlock.DIS_TO_TOP)
        paint.style = Paint.Style.FILL
        paint.color = getBgColor()
        paint.pathEffect = CornerPathEffect(IBaseBlock.sRadius)
        canvas.drawPath(path, paint)
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = IBaseBlock.sStrokeW
        paint.color = getBgBorderColor()
        canvas.drawPath(path, paint)
    }

    override fun onRun(role: IRoleView) {

    }

    override fun clone(): IBaseBlock {
        val newObj = ClickRoleBlockView(context)
        newObj.layoutParams = this.layoutParams
        newObj.minimumWidth = measuredWidth
        newObj.minimumHeight = measuredHeight
        return newObj
    }
}