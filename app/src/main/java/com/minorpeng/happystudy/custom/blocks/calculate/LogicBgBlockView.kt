package com.minorpeng.happystudy.custom.blocks.calculate

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.minorpeng.happystudy.R

/**
 *
 * @author MinorPeng
 * @date 2020/4/4 15:22
 */
class LogicBgBlockView : BaseLogicBlockView {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        this.setPadding(mDisLeft.toInt(), mDis2Top.toInt(), mDisLeft.toInt(), mDis2Top.toInt())
        minimumWidth = (mDisLeft * 3).toInt()
        minimumHeight = (mDisLeft * 2.3).toInt()
    }

    override fun getBgColorId(): Int {
        return R.color.colorCalculateGreenDark
    }

    override fun onRun(role: View) {

    }
}