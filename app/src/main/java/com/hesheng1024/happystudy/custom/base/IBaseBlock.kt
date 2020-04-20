package com.hesheng1024.happystudy.custom.base

import android.content.ClipData
import android.content.ClipDescription
import android.graphics.Canvas
import android.graphics.CornerPathEffect
import android.graphics.Paint
import android.graphics.Path
import android.view.*
import androidx.core.content.ContextCompat
import com.hesheng1024.base.utils.*
import com.hesheng1024.happystudy.custom.blocks.calculate.BaseCalculateBlockView
import com.hesheng1024.happystudy.custom.blocks.calculate.BaseLogicBlockView

/**
 * the base of a block
 *
 * @author hesheng1024
 * @date 2020/4/11 10:57
 */
interface IBaseBlock : IRoleListener, View.OnTouchListener {

    companion object {
        // 直接定义到接口中，可能会被实现类给覆盖重写，所以不规范，应当定义为静态
        const val sRadius = 6f
        const val sStrokeW = 2f
        val DIS_TO_LEFT = dp2px(ContextHolder.getMainContext(), 10f).toFloat()
        val DIS_TO_TOP = dp2px(ContextHolder.getMainContext(), 4f).toFloat()
        val LINE_LEN = dp2px(ContextHolder.getMainContext(), 12f).toFloat()
    }

    fun drawBackground(canvas: Canvas, paint: Paint, path: Path, measuredW: Float, measuredH: Float) {
        path.reset()
        path.moveTo(0f, 0f)
        path.lineTo(DIS_TO_LEFT, 0f)
        path.lineTo(DIS_TO_LEFT + DIS_TO_TOP, DIS_TO_TOP)
        path.lineTo(DIS_TO_LEFT + DIS_TO_TOP + LINE_LEN, DIS_TO_TOP)
        path.lineTo(DIS_TO_LEFT + DIS_TO_TOP * 2 + LINE_LEN, 0f)
        path.lineTo(measuredW, 0f)
        path.lineTo(measuredW, measuredH - DIS_TO_TOP)
        path.lineTo(DIS_TO_LEFT + DIS_TO_TOP * 2 + LINE_LEN, measuredH - DIS_TO_TOP)
        path.lineTo(DIS_TO_LEFT + DIS_TO_TOP + LINE_LEN, measuredH)
        path.lineTo(DIS_TO_LEFT + DIS_TO_TOP, measuredH)
        path.lineTo(DIS_TO_LEFT, measuredH - DIS_TO_TOP)
        path.lineTo(0f, measuredH - DIS_TO_TOP)
        path.lineTo(0f, 0f)
        paint.style = Paint.Style.FILL
        paint.color = getBgColor()
        paint.pathEffect = CornerPathEffect(sRadius)
        canvas.drawPath(path, paint)
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = sStrokeW
        paint.color = getBgBorderColor()
        canvas.drawPath(path, paint)
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        if (v != null && event != null) {
            return when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    //通过ViewParent去重新绘制子view
                    // offsetLeftAndRight((event.x - mLastX).toInt())
                    // offsetTopAndBottom((event.y - mLastY).toInt())
                    when (getStatus()) {
                        Status.STATUS_CLONE -> {
                            // 从左边拖动需要clone一个
                            val newObj = clone()
                            newObj.setStatus(Status.STATUS_DRAG)
                            val shadowBuilder = View.DragShadowBuilder(v)
                            v.startDrag(null, shadowBuilder, newObj, 0)
                            //震动反馈
                            v.performHapticFeedback(
                                HapticFeedbackConstants.LONG_PRESS, HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING
                            )
                            true
                        }
                        Status.STATUS_DRAG -> {
                            // drag本身
                            val shadowBuilder = View.DragShadowBuilder(v)
                            v.startDrag(null, shadowBuilder, v, 0)
                            //震动反馈
                            v.performHapticFeedback(
                                HapticFeedbackConstants.LONG_PRESS, HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING
                            )
                            true
                        }
                        Status.STATUS_NONE -> {
                            false
                        }
                    }
                }
                else -> false
            }
        }
        return false
    }

    /**
     * 子View处理DragEvent
     * 用于处理父View分发的DragEvent，是parent的DragEvent
     *
     * @param event DragEvent
     * @return true 子View处理该事件 false 子View不处理
     */
    fun onDragEv(event: CustomDragEvent): Boolean {
        val v = this
        if (v !is View || v.parent !is ViewGroup) {
            logE(this::class.java.simpleName, msg = "v:$v event:$event")
            return false
        }
        val block = event.localState
        if (block !is IBaseBlock
            || block.getStatus() != Status.STATUS_DRAG
            || block !is View
            || block is BaseCalculateBlockView
            || block is BaseLogicBlockView
            || block.getBlackOwn() !is View) {
            logE(this::class.java.simpleName, msg = "block error return false: $block")
            return false
        }
        // 因为积木有缺角，统一在bottom margin减去
        (block.layoutParams as ViewGroup.MarginLayoutParams).bottomMargin = -DIS_TO_TOP.toInt()
        val px = event.x
        val py = event.y
        val parent = v.parent as ViewGroup
        val blackOwn = block.getBlackOwn() as View
        return when (event.action) {
            DragEvent.ACTION_DRAG_LOCATION -> {
                // 自由drag进入了该view才会有position
                logI(this::class.java.simpleName, msg = "view position: x->$px y->$py tag:${block.tag} " +
                        "bTag:${blackOwn.tag}")
                when {
                    inTopRectF(px, py) -> {
                        // in the top
                        logD(this::class.java.simpleName, msg = "location in the top")
                        if (blackOwn.parent == null) {
                            logD(this::class.java.simpleName, msg = "top add index:${parent.indexOfChild(v)}")
                            parent.addView(blackOwn, parent.indexOfChild(v), block.layoutParams)
                        }
                    }
                    inBottomRectF(px, py) -> {
                        // in the bottom
                        // TODO 适配不同layout
                        logD(this::class.java.simpleName, msg = "location in the bottom")
                        if (blackOwn.parent == null) {
                            logD(this::class.java.simpleName, msg = "bottom add index:${parent.indexOfChild(v)}")
                            parent.addView(blackOwn, parent.indexOfChild(v) + 1, block.layoutParams)
                        }
                    }
                    else -> {
                        // 未在附近，移除阴影
                        logD(this::class.java.simpleName, msg = "location in other")
                        (blackOwn.parent as? ViewGroup)?.removeView(blackOwn)
                    }
                }
                false
            }
            DragEvent.ACTION_DROP -> {
                logI(this::class.java.simpleName, msg = "release dragging view x:$px y:$py")
                when {
                    inTopRectF(px, py) -> {
                        // in the top
                        logD(this::class.java.simpleName, msg = "drop in the top index:${parent.indexOfChild(v)}")
                        (blackOwn.parent as? ViewGroup)?.removeView(blackOwn)
                        (block.parent as? ViewGroup)?.removeView(block)
                        block.setStatus(Status.STATUS_DRAG)
                        parent.addView(block, parent.indexOfChild(v), block.layoutParams)
                        return true
                    }
                    inBottomRectF(px, py) -> {
                        // in the bottom
                        logD(this::class.java.simpleName, msg = "drop in the bottom index:${parent.indexOfChild(v)}")
                        (blackOwn.parent as? ViewGroup)?.removeView(blackOwn)
                        (block.parent as? ViewGroup)?.removeView(block)
                        block.setStatus(Status.STATUS_DRAG)
                        parent.addView(block, parent.indexOfChild(v) + 1, block.layoutParams)
                        return true
                    }
                    else -> {
                        // 未在附近，不处理
                        logD(this::class.java.simpleName, msg = "drop in other")
                        parent.removeView(blackOwn)
                    }
                }
                false
            }
            else -> {
                // 避免快速向外拖动导致监听不到坐标，就无法remove阴影
                (blackOwn.parent as? ViewGroup)?.removeView(blackOwn)
                false
            }
        }
    }

    /**
     * 背景颜色
     * 建议使用 {@link #setBgColorId(colorId: Int)} 进行设置而非重写该方法
     *
     * @see setBgColorId
     */
    fun getBgColor(): Int

    fun getBgBorderColor(): Int = ContextCompat.getColor(ContextHolder.getMainContext(), android.R.color.darker_gray)

    fun setBgColorId(colorId: Int)

    fun setBgColor(color: Int)

    fun setStatus(status: Status)

    fun getStatus(): Status

    fun clone(): IBaseBlock

    /**
     * 获取一个等大小的灰色的外形
     */
    fun getBlackOwn(): IBaseBlock

    /**
     * 判断是否在积木上方附近
     */
    fun inTopRectF(x: Float, y: Float): Boolean

    /**
     * 判断是否在积木下方附近
     */
    fun inBottomRectF(x: Float, y: Float): Boolean

    /**
     * 积木状态
     */
    enum class Status {
        /**
         * 既drag又clone自身
         */
        STATUS_CLONE,

        /**
         * 只drag不clone
         */
        STATUS_DRAG,

        /**
         * 阴影状态
         */
        STATUS_NONE
    }

    /**
     * copy a DragEvent to change x, y
     */
    data class CustomDragEvent(val x: Float, val y: Float, val action: Int, val localState: Any? = null,
                               val clipData: ClipData? = null, val clipDescription: ClipDescription? = null,
                               val result: Boolean)
}