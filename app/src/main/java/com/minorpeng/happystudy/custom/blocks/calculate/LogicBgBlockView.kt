package com.minorpeng.happystudy.custom.blocks.calculate

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.minorpeng.happystudy.R
import com.minorpeng.happystudy.custom.base.IBaseBlockBg

/**
 *
 * @author MinorPeng
 * @date 2020/4/4 15:22
 */
@SuppressLint("ViewConstructor")
class LogicBgBlockView(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0) :
    BaseLogicBlockView(context, attrs, defStyleAttr, defStyleRes) {

    init {
        setBgColorId(R.color.colorCalculateGreenDark)
        this.setPadding(
            sDisLeft.toInt(),
            IBaseBlockBg.sDis2Top.toInt(),
            sDisLeft.toInt(),
            IBaseBlockBg.sDis2Top.toInt()
        )
        minimumWidth = (sDisLeft * 3).toInt()
        minimumHeight = (sDisLeft * 2.3).toInt()
    }

    override fun onRun(role: View) {

    }
}