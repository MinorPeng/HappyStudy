package com.hesheng1024.happystudy.custom.blocks.calculate

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.hesheng1024.base.utils.DensityUtil
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.custom.base.IBaseBlock

/**
 *
 * @author hesheng1024
 * @date 2020/4/4 15:19
 */
@SuppressLint("ViewConstructor")
class OrBlockView : BaseLogicBlockView {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        this.setPadding(
            IBaseBlock.sDis2Top.toInt(),
            IBaseBlock.sDis2Top.toInt(),
            IBaseBlock.sDis2Top.toInt(),
            IBaseBlock.sDis2Top.toInt()
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
        tvMoreThan.setText(R.string.or)
        tvMoreThan.setTextColor(ContextCompat.getColor(context, android.R.color.white))
        addView(tvMoreThan, lpTvMoreThan)

        val logicBlockViewRight = LogicBgBlockView(context)
        addView(logicBlockViewRight)
    }

    override fun onRun(role: View) {

    }

    override fun clone(): IBaseBlock {
        val newObj = OrBlockView(context)
        newObj.layoutParams = this.layoutParams
        return newObj
    }
}