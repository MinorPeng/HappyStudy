package com.minorpeng.happystudy.custom.blocks.calculate

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.minorpeng.happystudy.R

/**
 *
 * @author MinorPeng
 * @date 2020/4/4 15:22
 */
@SuppressLint("ViewConstructor")
class LogicBgBlockView(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0) :
    BaseLogicBlockView(context, attrs, defStyleAttr, defStyleRes) {

    init {
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