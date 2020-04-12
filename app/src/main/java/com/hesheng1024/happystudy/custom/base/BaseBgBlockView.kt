package com.hesheng1024.happystudy.custom.base

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.Gravity
import android.view.MotionEvent
import android.widget.LinearLayout
import androidx.core.content.ContextCompat

/**
 *
 * @author hesheng1024
 * @date 2020/3/29 17:39
 */
abstract class BaseBgBlockView : LinearLayout, IRoleListener, IBaseBlockBg {

    private val mPaint = Paint()
    private val mPath = Path()

    /**
     * 积木的背景色
     */
    private var mBgColor = this.getBgColor()
    private var mLastX: Float = 0f
    private var mLastY: Float = 0f
    private var mCanMove = false
    private var mStatus = IBaseBlockBg.Status.STATUS_CLONE

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0) {

    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        this.setWillNotDraw(false)
        this.setPadding(
            (IBaseBlockBg.sDis2Top * 2).toInt(),
            IBaseBlockBg.sDis2Top.toInt(),
            (IBaseBlockBg.sDis2Top * 2).toInt(),
            (IBaseBlockBg.sDis2Top * 2).toInt()
        )
        gravity = Gravity.CENTER
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            drawBackground(canvas, mPaint, mPath, measuredWidth.toFloat(), measuredHeight.toFloat())
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    mLastX = event.x
                    mLastY = event.y
                    return true
                }
                MotionEvent.ACTION_MOVE -> {
                    if (mCanMove) {
                        //通过ViewParent去重新绘制子view
                        offsetLeftAndRight((event.x - mLastX).toInt())
                        offsetTopAndBottom((event.y - mLastY).toInt())
                        return true
                    }
                }
            }
            return true
        }
        return super.onTouchEvent(event)
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

    override fun getBgBorderColor(): Int {
        return ContextCompat.getColor(context, android.R.color.darker_gray)
    }

    override fun setStatus(status: IBaseBlockBg.Status) {
        this.mStatus = status
    }

    override fun getStatus(): IBaseBlockBg.Status {
        return mStatus
    }

    override fun clone(): IBaseBlockBg {
        return this
    }

    fun setBgColorId(colorId: Int) {
        this.mBgColor = ContextCompat.getColor(context, colorId)
    }

    fun setBgColor(color: Int) {
        this.mBgColor = color
    }

    fun setMoved(moved: Boolean) {
        this.mCanMove = moved
    }
}