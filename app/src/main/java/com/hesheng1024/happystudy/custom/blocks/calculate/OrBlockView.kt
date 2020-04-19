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
 * @date 2020/4/4 15:19
 */
@SuppressLint("ViewConstructor")
class OrBlockView : BaseLogicBlockView {

    private val mLeftLogicBg: LogicBgBlockView
    private val mRightLogicBg: LogicBgBlockView

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
        mLeftLogicBg = LogicBgBlockView(context)
        mRightLogicBg = LogicBgBlockView(context)
        initView()
    }

    private fun initView() {
        var isIn = false
        mLeftLogicBg.setOnDragListener { v, event ->
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
                    if (isIn && mLeftLogicBg.childCount == 0 && logicBlock is BaseLogicBlockView) {
                        (logicBlock.parent as? ViewGroup)?.removeView(logicBlock)
                        mLeftLogicBg.addView(logicBlock)
                    } else {
                        LogUtil.i(msg = "can't add view: isIn->$isIn count:${mLeftLogicBg.childCount} logic:$logicBlock")
                    }
                }
            }
            return@setOnDragListener true
        }
        addView(mLeftLogicBg)

        val lpTvMoreThan = generateDefaultLayoutParams() as MarginLayoutParams
        lpTvMoreThan.leftMargin = DensityUtil.dp2px(context, 8f)
        lpTvMoreThan.rightMargin = DensityUtil.dp2px(context, 8f)
        val tvMoreThan = TextView(context)
        tvMoreThan.setText(R.string.or)
        tvMoreThan.setTextColor(ContextCompat.getColor(context, android.R.color.white))
        addView(tvMoreThan, lpTvMoreThan)

        mRightLogicBg.setOnDragListener { v, event ->
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
                    if (isIn && mRightLogicBg.childCount == 0 && logicBlock is BaseLogicBlockView) {
                        (logicBlock.parent as? ViewGroup)?.removeView(logicBlock)
                        mRightLogicBg.addView(logicBlock)
                    } else {
                        LogUtil.i(msg = "can't add view: isIn->$isIn count:${mRightLogicBg.childCount} logic:$logicBlock")
                    }
                }
            }
            return@setOnDragListener true
        }
        addView(mRightLogicBg)
    }

    override suspend fun onRun(role: IRoleView) {

    }

    override fun clone(): IBaseBlock {
        val newObj = OrBlockView(context)
        newObj.layoutParams = this.layoutParams
        newObj.minimumWidth = measuredWidth
        newObj.minimumHeight = measuredHeight
        var child = mLeftLogicBg.getChildAt(0)
        if ( child is BaseLogicBlockView) {
            newObj.mLeftLogicBg.addView(child.clone() as BaseLogicBlockView)
        }
        child = mRightLogicBg.getChildAt(0)
        if (child is BaseLogicBlockView) {
            newObj.mRightLogicBg.addView(child.clone() as BaseLogicBlockView)
        }
        return newObj
    }

    override fun judgeResult(): Boolean {
        return mLeftLogicBg.judgeResult() || mRightLogicBg.judgeResult()
    }
}