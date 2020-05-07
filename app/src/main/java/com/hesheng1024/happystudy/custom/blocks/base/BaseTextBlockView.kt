package com.hesheng1024.happystudy.custom.blocks.base

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.hesheng1024.base.utils.logD
import com.hesheng1024.happystudy.custom.blocks.BlockTextView

/**
 *
 * @author hesheng1024
 * @date 2020/3/29 19:40
 */
abstract class BaseTextBlockView : BlockTextView, IBaseBlock {

    private val mPaint = Paint()
    private val mPath = Path()

    /**
     * 积木的背景色
     */
    @Volatile
    private var mBgColor = -1

    @Volatile
    private var mStatus = IBaseBlock.Status.STATUS_CLONE
    private var mBlackOwn: IBaseBlock? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, android.R.attr.textViewStyle)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        this.setPadding(
            (IBaseBlock.DIS_TO_TOP * 2).toInt(),
            (IBaseBlock.DIS_TO_TOP * 2).toInt(),
            (IBaseBlock.DIS_TO_TOP * 2).toInt(),
            (IBaseBlock.DIS_TO_TOP * 2).toInt()
        )
        this.setOnTouchListener(this)
        this.setOnLongClickListener(this)
        this.setOnClickListener(this)
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.let {
            drawBackground(canvas, mPaint, mPath, measuredWidth.toFloat(), measuredHeight.toFloat())
        }
        super.onDraw(canvas)
    }

    final override fun setBgColorId(colorId: Int) {
        this.mBgColor = ContextCompat.getColor(context, colorId)
    }

    override fun setBgColor(color: Int) {
        this.mBgColor = color
    }

    override fun getBgColor(): Int = mBgColor

    override fun setStatus(status: IBaseBlock.Status) {
        this.mStatus = status
    }

    override fun getStatus(): IBaseBlock.Status = mStatus

    @Synchronized
    override fun getBlackOwn(): IBaseBlock {
        if (mBlackOwn == null) {
            mBlackOwn = clone()
            if (mBlackOwn is BaseTextBlockView) {
                val own = mBlackOwn as BaseTextBlockView
                own.minimumWidth = minimumWidth
                own.minimumHeight = minimumHeight
                own.setBgColor(getBgBorderColor())
                own.setStatus(IBaseBlock.Status.STATUS_NONE)
                own.text = ""
            }
        }
        return mBlackOwn!!
    }

    override fun inTopRectF(x: Float, y: Float): Boolean {
        val isIn = (x < right && x >= left
                && y < top + measuredHeight / 3 && y >= top - measuredHeight / 3 * 4)
        logD(msg = "top isIn:$isIn l:$left t:$top r:$right b:$bottom x:$x y:$y")
        return isIn
    }

    override fun inBottomRectF(x: Float, y: Float): Boolean {
        val isIn = (x < right && x >= left
                && y <= bottom + measuredHeight / 3 * 4 && y > bottom - measuredHeight / 3)
        logD(msg = "bottom isIn:$isIn l:$left t:$top r:$right b:$bottom x:$x y:$y")
        return isIn
    }
}