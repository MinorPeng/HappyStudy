package com.hesheng1024.happystudy.custom.blocks.control

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import com.hesheng1024.base.utils.dp2px
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.TEXT_SIZE_BLOCK_12
import com.hesheng1024.happystudy.custom.blocks.base.IBaseBlock
import com.hesheng1024.happystudy.custom.blocks.calculate.BaseLogicBlockView
import com.hesheng1024.happystudy.custom.blocks.calculate.LogicBgBlockView
import com.hesheng1024.happystudy.custom.role.IRoleView

/**
 *
 * @author hesheng1024
 * @date 2020/4/4 17:11
 */
@SuppressLint("ViewConstructor")
class CirculationUtilBlockView : BaseControlBlockView {

    private val mLogicBg: LogicBgBlockView

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        mLogicBg = LogicBgBlockView(context)
        initView()
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        val tvCirculation = AppCompatTextView(context)
        tvCirculation.setText(R.string.circulation_until)
        tvCirculation.textSize = TEXT_SIZE_BLOCK_12
        tvCirculation.setTextColor(Color.WHITE)
        tvCirculation.tag = ChildTag.TAG_TOP
        addView(tvCirculation, 0)

        val lp = generateDefaultLayoutParams() as MarginLayoutParams
        lp.leftMargin = dp2px(context, 4f)
        mLogicBg.setBgColorId(R.color.colorControlOrange700)
        mLogicBg.tag = ChildTag.TAG_TOP
        addView(mLogicBg, 1, lp)

        val ivCirculation = ImageView(context)
        ivCirculation.setImageResource(R.drawable.ic_circulation_16)
        ivCirculation.tag = ChildTag.TAG_BOTTOM
        addView(ivCirculation, 2)
    }

    override suspend fun onRun(role: IRoleView) {
        while (mLogicBg.judgeResult()) {
            onChildRun(role)
        }
    }

    override fun clone(): IBaseBlock {
        val newObj = CirculationUtilBlockView(context)
        newObj.layoutParams = this.layoutParams
        newObj.minimumWidth = measuredWidth
        newObj.minimumHeight = measuredHeight
        val child = mLogicBg.getChildAt(0)
        if (child != null && child is BaseLogicBlockView) {
            newObj.mLogicBg.addView(child.clone() as BaseLogicBlockView)
        }
        return newObj
    }
}