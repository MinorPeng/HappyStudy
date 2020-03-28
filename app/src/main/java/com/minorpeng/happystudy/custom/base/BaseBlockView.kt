package com.minorpeng.happystudy.custom.base

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout

/**
 *
 * @author MinorPeng
 * @date 2020/3/24 18:00
 */
abstract class BaseBlockView : FrameLayout, IRoleListener {

    private var mLastX: Float = 0f
    private var mLastY: Float = 0f

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(
        context,
        attrs,
        defStyleAttr,
        0
    )

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    mLastX = event.x
                    mLastY = event.y
                }
                MotionEvent.ACTION_MOVE -> {
                    //通过ViewParent去重新绘制子view
                    offsetLeftAndRight((event.x - mLastX).toInt())
                    offsetTopAndBottom((event.y - mLastY).toInt())
                }
            }
            return true
        }
        return super.onTouchEvent(event)
    }
}