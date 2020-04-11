package com.minorpeng.happystudy.custom.blocks.calculate

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.minorpeng.base.utils.DensityUtil
import com.minorpeng.happystudy.R
import com.minorpeng.happystudy.custom.base.IBaseBlockBg

/**
 *
 * @author MinorPeng
 * @date 2020/4/4 14:20
 */
@SuppressLint("ViewConstructor")
class AndBlockView(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0) :
    BaseLogicBlockView(context, attrs, defStyleAttr, defStyleRes) {

    init {
        this.setPadding(
            IBaseBlockBg.sDis2Top.toInt(),
            IBaseBlockBg.sDis2Top.toInt(),
            IBaseBlockBg.sDis2Top.toInt(),
            IBaseBlockBg.sDis2Top.toInt()
        )
        initView()
    }

    private fun initView() {
        val logicBlockViewLeft = LogicBgBlockView(context)
        addView(logicBlockViewLeft)

        val lpTvMoreThan = generateDefaultLayoutParams() as MarginLayoutParams
        lpTvMoreThan.leftMargin = DensityUtil.dp2px(context, 8f)
        lpTvMoreThan.rightMargin = DensityUtil.dp2px(context, 8f)
        val tvMoreThan = TextView(context)
        tvMoreThan.setText(R.string.and)
        tvMoreThan.setTextColor(ContextCompat.getColor(context, android.R.color.white))
        addView(tvMoreThan, lpTvMoreThan)

        val logicBlockViewRight = LogicBgBlockView(context)
        addView(logicBlockViewRight)
    }

    override fun onRun(role: View) {

    }
}