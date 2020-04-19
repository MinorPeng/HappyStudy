package com.hesheng1024.happystudy.custom.blocks.calculate

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.DragEvent
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.hesheng1024.base.utils.DensityUtil
import com.hesheng1024.base.utils.LogUtil
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.custom.base.IBaseBlock
import com.hesheng1024.happystudy.custom.base.IRoleView

/**
 *
 * @author hesheng1024
 * @date 2020/4/4 14:21
 */
@SuppressLint("ViewConstructor")
class NotBlockView : BaseLogicBlockView {

    private val mLogicBg: LogicBgBlockView

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        this.setPadding(
            IBaseBlock.DIS_TO_TOP.toInt(),
            IBaseBlock.DIS_TO_TOP.toInt(),
            IBaseBlock.DIS_TO_TOP.toInt(),
            IBaseBlock.DIS_TO_TOP.toInt()
        )
        mLogicBg = LogicBgBlockView(context)
        initView()
    }

    private fun initView() {
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
        addView(mLogicBg)

        val lpTvMoreThan = generateDefaultLayoutParams() as MarginLayoutParams
        lpTvMoreThan.leftMargin = DensityUtil.dp2px(context, 8f)
        lpTvMoreThan.rightMargin = sDisLeft.toInt()
        val tvMoreThan = TextView(context)
        tvMoreThan.setText(R.string.not)
        tvMoreThan.setTextColor(ContextCompat.getColor(context, android.R.color.white))
        addView(tvMoreThan, lpTvMoreThan)
    }

    override fun onRun(role: IRoleView) {

    }

    override fun clone(): IBaseBlock {
        val newObj = NotBlockView(context)
        newObj.layoutParams = this.layoutParams
        newObj.minimumWidth = measuredWidth
        newObj.minimumHeight = measuredHeight
        val child = mLogicBg.getChildAt(0)
        if ( child is BaseLogicBlockView) {
            newObj.mLogicBg.addView(child.clone() as BaseLogicBlockView)
        }
        return newObj
    }

    override fun judgeResult(): Boolean {
        return !mLogicBg.judgeResult()
    }
}