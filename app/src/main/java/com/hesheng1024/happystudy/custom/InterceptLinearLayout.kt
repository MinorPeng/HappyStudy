package com.hesheng1024.happystudy.custom

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.LinearLayout

/**
 *
 * @author hesheng1024
 * @date 2020/5/22 19:05
 */
class InterceptLinearLayout : LinearLayout {

    private var mIsIntercept = false

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes)

    fun setIntercept(intercept: Boolean) {
        mIsIntercept = intercept
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return if (mIsIntercept) true else super.onInterceptTouchEvent(ev)
    }
}