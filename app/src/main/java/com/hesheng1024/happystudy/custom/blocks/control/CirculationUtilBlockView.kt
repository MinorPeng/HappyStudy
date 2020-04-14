package com.hesheng1024.happystudy.custom.blocks.control

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.hesheng1024.base.utils.DensityUtil
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.custom.base.IBaseBlock
import com.hesheng1024.happystudy.custom.blocks.calculate.LogicBgBlockView
import com.hesheng1024.happystudy.custom.blocks.motion.MoveBlockView

/**
 *
 * @author hesheng1024
 * @date 2020/4/4 17:11
 */
@SuppressLint("ViewConstructor")
class CirculationUtilBlockView : BaseControlBlockView {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        initView()
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        val whiteColor = ContextCompat.getColor(context, android.R.color.white)
        val tvCirculation = TextView(context)
        tvCirculation.setText(R.string.circulation_until)
        tvCirculation.tag = ChildTag.TAG_TOP
        tvCirculation.setTextColor(whiteColor)
        addView(tvCirculation)

        val lp = generateDefaultLayoutParams() as MarginLayoutParams
        lp.leftMargin = DensityUtil.dp2px(context, 8f)
        val logicBlockView = LogicBgBlockView(context)
        logicBlockView.setBgColorId(R.color.colorControlYellowDark)
        logicBlockView.tag = ChildTag.TAG_TOP
        addView(logicBlockView, lp)

        val ivCirculation = ImageView(context)
        ivCirculation.setImageResource(R.drawable.ic_circulation_16)
        ivCirculation.tag = ChildTag.TAG_BOTTOM
        addView(ivCirculation)

        // TODO test
        val mo = MoveBlockView(context)
        mo.tag = ChildTag.TAG_CHILD
        addView(mo)
    }

    override fun onRun(role: View) {

    }

    override fun clone(): IBaseBlock {
        val newObj = CirculationUtilBlockView(context)
        newObj.layoutParams = this.layoutParams
        newObj.minimumWidth = measuredWidth
        newObj.minimumHeight = measuredHeight
        return newObj
    }
}