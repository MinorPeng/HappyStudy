package com.minorpeng.happystudy.custom.base

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.*
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.minorpeng.base.utils.LogUtil
import com.minorpeng.happystudy.custom.blocks.appearance.NextBgBlockView
import kotlinx.android.synthetic.main.activity_test.*

/**
 *
 * @author MinorPeng
 * @date 2020/3/29 19:40
 */
abstract class BaseTextBlockView : AppCompatTextView, IRoleListener, IBaseBlockBg, View.OnDragListener {

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

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        gravity = Gravity.CENTER
        this.setPadding(
            (IBaseBlockBg.sDis2Top * 2).toInt(),
            (IBaseBlockBg.sDis2Top * 2).toInt(),
            (IBaseBlockBg.sDis2Top * 2).toInt(),
            (IBaseBlockBg.sDis2Top * 2).toInt()
        )
        this.setTextColor(ContextCompat.getColor(context, android.R.color.white))
        this.setOnDragListener(this::onDrag)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    mLastX = event.x
                    mLastY = event.y
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    // if (mCanMove) {
                    //     //通过ViewParent去重新绘制子view
                    //     offsetLeftAndRight((event.x - mLastX).toInt())
                    //     offsetTopAndBottom((event.y - mLastY).toInt())
                    //     return true
                    // }
                    when (mStatus) {
                        IBaseBlockBg.Status.STATUS_CLONE -> {
                            // 从左边拖动需要clone一个
                            val newObj = clone() as BaseTextBlockView
                            // TODO 直接设置宽高有问题，另外一边获取不到
                            newObj.minWidth = measuredWidth
                            newObj.minHeight = measuredHeight
                            LogUtil.d(msg = "w:${newObj.minWidth} h:${newObj.height} mw:$measuredWidth")
                            val shadowBuilder = DragShadowBuilder(this)
                            this.startDrag(null, shadowBuilder, newObj, 0)
                            //震动反馈
                            this.performHapticFeedback(
                                HapticFeedbackConstants.LONG_PRESS, HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING
                            )
                            true
                        }
                        IBaseBlockBg.Status.STATUS_DRAG -> {
                            // drag本身
                            val shadowBuilder = DragShadowBuilder(this)
                            this.startDrag(null, shadowBuilder, this, 0)
                            //震动反馈
                            this.performHapticFeedback(
                                HapticFeedbackConstants.LONG_PRESS, HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING
                            )
                            true
                        }
                    }
                }
                else -> super.onTouchEvent(event)
            }
        }
        return super.onTouchEvent(event)
    }

    override fun onDrag(v: View?, event: DragEvent?): Boolean {
         event?.let {
             val block = event.localState as BaseTextBlockView
             when (event.action) {
                 DragEvent.ACTION_DRAG_STARTED -> {
                     LogUtil.i(msg = "start")
                     block.visibility = View.VISIBLE
                 }
                 DragEvent.ACTION_DRAG_ENDED -> {
                     LogUtil.i(msg = "end")
                     block.visibility = View.VISIBLE
                 }
                 DragEvent.ACTION_DRAG_ENTERED -> {
                     LogUtil.i(msg = "view in dragging in frame")
                 }
                 DragEvent.ACTION_DRAG_EXITED -> {
                     LogUtil.i(msg = "view in dragging out frame")
                 }
                 DragEvent.ACTION_DRAG_LOCATION -> {
                     LogUtil.i(msg = "view pos in frame: x->${event.x} y->${event.y}")
                 }
                 DragEvent.ACTION_DROP -> {
                     LogUtil.i(msg = "release dragging view x:${event.x} y:${event.y}")
                     if (event.x > left && event.x < right && event.y >= bottom && event.y < bottom + 4) {
                         (block.parent as? ViewGroup)?.removeView(block)
                         block.setStatus(IBaseBlockBg.Status.STATUS_DRAG)
                         // TODO parent绘制到该View旁边
                     } else {
                         // 未在附近，不处理
                     }
                 }
                 else -> super.onDragEvent(event)
             }
         }
        //是否响应拖拽事件，true响应，返回false只能接受到ACTION_DRAG_STARTED事件，后续事件不会收到
        return true
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.let {
            drawBackground(canvas, mPaint, mPath, measuredWidth.toFloat(), measuredHeight.toFloat())
        }
        super.onDraw(canvas)
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