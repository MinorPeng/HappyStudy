package com.minorpeng.happystudy.custom.blocks.calculate

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.minorpeng.base.utils.DensityUtil
import com.minorpeng.happystudy.R

/**
 *
 * @author MinorPeng
 * @date 2020/4/4 15:19
 */
class OrBlockView : BaseLogicBlockView {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        this.setPadding(mDis2Top.toInt(), mDis2Top.toInt(), mDis2Top.toInt(), mDis2Top.toInt())
        initView()
    }

    private fun initView() {
        val logicBlockViewLeft = LogicBgBlockView(context)
        addView(logicBlockViewLeft)

        val lpTvMoreThan = generateDefaultLayoutParams() as MarginLayoutParams
        lpTvMoreThan.leftMargin = DensityUtil.dp2px(context, 8f)
        lpTvMoreThan.rightMargin = DensityUtil.dp2px(context, 8f)
        val tvMoreThan = TextView(context)
        tvMoreThan.setText(R.string.or)
        tvMoreThan.setTextColor(ContextCompat.getColor(context, android.R.color.white))
        addView(tvMoreThan, lpTvMoreThan)

        val logicBlockViewRight = LogicBgBlockView(context)
        addView(logicBlockViewRight)
    }

    override fun onRun(role: View) {

    }
}