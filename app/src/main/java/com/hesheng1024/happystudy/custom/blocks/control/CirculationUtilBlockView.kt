package com.hesheng1024.happystudy.custom.blocks.control

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.DragEvent
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.hesheng1024.base.utils.DensityUtil
import com.hesheng1024.base.utils.LogUtil
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.custom.base.IBaseBlock
import com.hesheng1024.happystudy.custom.base.IRoleView
import com.hesheng1024.happystudy.custom.blocks.calculate.BaseLogicBlockView
import com.hesheng1024.happystudy.custom.blocks.calculate.LogicBgBlockView

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
        val whiteColor = ContextCompat.getColor(context, android.R.color.white)
        val tvCirculation = TextView(context)
        tvCirculation.setText(R.string.circulation_until)
        tvCirculation.tag = ChildTag.TAG_TOP
        tvCirculation.setTextColor(whiteColor)
        addView(tvCirculation,0)

        val lp = generateDefaultLayoutParams() as MarginLayoutParams
        lp.leftMargin = DensityUtil.dp2px(context, 8f)
        mLogicBg.setBgColorId(R.color.colorControlYellowDark)
        mLogicBg.tag = ChildTag.TAG_TOP
        // 也可以直接在父类中统一监听，只是坐标计算相对复杂一点
        var isIn = false
        mLogicBg.setOnDragListener { v, event ->
            when(event.action) {
                DragEvent.ACTION_DRAG_ENTERED -> {
                    LogUtil.i(msg = "logicBgView entered")
                    isIn = true
                }
                DragEvent.ACTION_DRAG_EXITED -> {
                    LogUtil.i(msg = "logicBgView exited")
                    isIn = false
                }
                DragEvent.ACTION_DROP -> {
                    LogUtil.i(msg = "logicBgView drop")
                    val logicBlock = event.localState
                    if (isIn && mLogicBg.childCount == 0 && logicBlock is BaseLogicBlockView) {
                        (logicBlock.parent as? ViewGroup)?.removeView(logicBlock)
                        mLogicBg.addView(logicBlock)
                    } else {
                        LogUtil.i(msg = "can't add view: isIn->$isIn count:${mLogicBg.childCount} logic:$logicBlock")
                    }
                }
            }
            return@setOnDragListener true
        }
        addView(mLogicBg, 1, lp)

        val ivCirculation = ImageView(context)
        ivCirculation.setImageResource(R.drawable.ic_circulation_16)
        ivCirculation.tag = ChildTag.TAG_BOTTOM
        addView(ivCirculation, 2)
    }

    override fun onRun(role: IRoleView) {
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