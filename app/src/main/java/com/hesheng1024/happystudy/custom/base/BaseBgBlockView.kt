package com.hesheng1024.happystudy.custom.base

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.DragEvent
import android.view.Gravity
import android.widget.LinearLayout
import androidx.core.content.ContextCompat

/**
 *
 * @author hesheng1024
 * @date 2020/3/29 17:39
 */
abstract class BaseBgBlockView : LinearLayout, IBaseBlock {

    private val mPaint = Paint()
    private val mPath = Path()

    /**
     * 积木的背景色
     */
    private var mBgColor = this.getBgColor()
    @Volatile
    private var mStatus = IBaseBlock.Status.STATUS_CLONE
    private var mBlackOwn: IBaseBlock? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0) {

    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        this.setWillNotDraw(false)
        this.setPadding(
            (IBaseBlock.DIS_TO_TOP * 2).toInt(),
            IBaseBlock.DIS_TO_TOP.toInt(),
            (IBaseBlock.DIS_TO_TOP * 2).toInt(),
            (IBaseBlock.DIS_TO_TOP * 2).toInt()
        )
        gravity = Gravity.CENTER
        this.setOnTouchListener(this)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            drawBackground(canvas, mPaint, mPath, measuredWidth.toFloat(), measuredHeight.toFloat())
        }
    }

    /**
     * 背景颜色
     * 建议使用 {@link #setBgColorId(colorId: Int)} 进行设置而非重写该方法
     *
     * @see setBgColorId
     */
    override fun getBgColor(): Int {
        return mBgColor
    }

    override fun setStatus(status: IBaseBlock.Status) {
        this.mStatus = status
    }

    override fun getStatus(): IBaseBlock.Status {
        return mStatus
    }

    final override fun setBgColorId(colorId: Int) {
        this.mBgColor = ContextCompat.getColor(context, colorId)
    }

    override fun setBgColor(color: Int) {
        this.mBgColor = color
    }

    @Synchronized
    override fun getBlackOwn(): IBaseBlock {
        if (mBlackOwn == null) {
            mBlackOwn = clone()
            if (mBlackOwn is BaseBgBlockView) {
                val own = mBlackOwn as BaseBgBlockView
                own.minimumWidth = minimumWidth
                own.minimumHeight = minimumHeight
                own.setBgColor(getBgBorderColor())
                own.setStatus(IBaseBlock.Status.STATUS_NONE)
                own.removeAllViews()
            }
        }
        return mBlackOwn!!
    }

    override fun inTopRectF(x: Float, y: Float): Boolean {
        return (x <= left + measuredWidth && x >= left
                && y < top + measuredHeight / 3 && y >= top - measuredHeight / 3 * 4)
    }

    override fun inBottomRectF(x: Float, y: Float): Boolean {
        return (x <= left + measuredWidth && x >= left
                && y <= bottom + measuredHeight / 3 * 4 && y > bottom - measuredHeight / 3)
    }
}