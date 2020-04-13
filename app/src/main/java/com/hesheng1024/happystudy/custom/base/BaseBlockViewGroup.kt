package com.hesheng1024.happystudy.custom.base

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.hesheng1024.base.utils.LogUtil

/**
 *
 * @author hesheng1024
 * @date 2020/3/24 18:08
 */
abstract class BaseBlockViewGroup : ViewGroup, IRoleListener, IBaseBlock {

    private val mPaint = Paint()
    private val mPath = Path()

    /**
     * 积木的背景色
     */
    private var mBgColor = this.getBgColor()
    @Volatile
    private var mStatus = IBaseBlock.Status.STATUS_CLONE
    @Volatile
    private var mInRectF = false
    private var mBlackOwn: IBaseBlock? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            drawBackground(canvas, mPaint, mPath, measuredWidth.toFloat(), measuredHeight.toFloat())
        }
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }

    override fun generateDefaultLayoutParams(): LayoutParams {
        return MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
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

    override fun setBgColorId(colorId: Int) {
        this.mBgColor = ContextCompat.getColor(context, colorId)
    }

    override fun setBgColor(color: Int) {
        this.mBgColor = color
    }

    @Synchronized
    override fun getBlackOwn(): IBaseBlock {
        if (mBlackOwn == null) {
            mBlackOwn = clone()
            mBlackOwn?.setBgColor(getBgBorderColor())
            mBlackOwn?.setStatus(IBaseBlock.Status.STATUS_NONE)
            if (mBlackOwn is BaseBlockViewGroup) {
                (mBlackOwn as BaseBlockViewGroup).removeAllViews()
            }
        }
        return mBlackOwn!!
    }

    override fun inTopRectF(x: Float, y: Float): Boolean {
        val isIn = if (mInRectF) {
            x < measuredWidth / 2 && x > 0 && y < measuredHeight / 2 && y > 0
        } else {
            x < left + measuredWidth / 2 && x > left && y < top && y > top - measuredHeight / 2 * 3
        }
        LogUtil.d(msg = "isIn:$isIn")
        return isIn
    }

    override fun inBottomRectF(x: Float, y: Float): Boolean {
        val isIn = if (mInRectF) {
            x < measuredWidth / 2 && x > 0 && y < measuredHeight && y > measuredHeight / 2
        } else {
            x < left + measuredWidth / 2 && x > left && y < top + measuredHeight / 2 * 3 && y > top
        }
        LogUtil.d(msg = "isIn:$isIn")
        return isIn
    }

    override fun isInRectF(inRectF: Boolean) {
        this.mInRectF = inRectF
    }
}