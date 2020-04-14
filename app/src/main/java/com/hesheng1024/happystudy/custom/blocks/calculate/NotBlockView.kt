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
 * @date 2020/4/4 14:21
 */
@SuppressLint("ViewConstructor")
class NotBlockView : BaseLogicBlockView {

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
        val logicBlockView = LogicBgBlockView(context)
        addView(logicBlockView)

        val lpTvMoreThan = generateDefaultLayoutParams() as MarginLayoutParams
        lpTvMoreThan.leftMargin = DensityUtil.dp2px(context, 8f)
        lpTvMoreThan.rightMargin = sDisLeft.toInt()
        val tvMoreThan = TextView(context)
        tvMoreThan.setText(R.string.not)
        tvMoreThan.setTextColor(ContextCompat.getColor(context, android.R.color.white))
        addView(tvMoreThan, lpTvMoreThan)
    }

    override fun onRun(role: View) {

    }

    override fun clone(): IBaseBlock {
        val newObj = NotBlockView(context)
        newObj.layoutParams = this.layoutParams
        newObj.minimumWidth = measuredWidth
        newObj.minimumHeight = measuredHeight
        return newObj
    }
}