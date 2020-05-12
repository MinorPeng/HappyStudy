package com.hesheng1024.happystudy.custom.blocks.control

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.DragEvent
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.hesheng1024.base.utils.dp2px
import com.hesheng1024.base.utils.logI
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.TEXT_SIZE_BLOCK_12
import com.hesheng1024.happystudy.custom.blocks.base.IBaseBlock
import com.hesheng1024.happystudy.custom.blocks.calculate.BaseLogicBlockView
import com.hesheng1024.happystudy.custom.blocks.calculate.LogicBgBlockView
import com.hesheng1024.happystudy.custom.role.IRoleView

/**
 *
 * @author hesheng1024
 * @date 2020/4/1 11:03
 */
@SuppressLint("ViewConstructor")
class IfBlockView : BaseControlBlockView {

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
        val tvIf = AppCompatTextView(context)
        tvIf.setText(R.string.if_str)
        tvIf.textSize = TEXT_SIZE_BLOCK_12
        tvIf.setTextColor(ContextCompat.getColor(context, android.R.color.white))
        tvIf.tag = ChildTag.TAG_TOP
        addView(tvIf)

        val lp = generateDefaultLayoutParams() as MarginLayoutParams
        lp.leftMargin = dp2px(context, 4f)
        lp.rightMargin = dp2px(context, 4f)
        mLogicBg.setBgColorId(R.color.colorControlOrange700)
        mLogicBg.tag = ChildTag.TAG_TOP
        // 也可以直接在父类中统一监听，只是坐标计算相对复杂一点
        var isIn = false
        mLogicBg.setOnDragListener { v, event ->
            when (event.action) {
                DragEvent.ACTION_DRAG_ENTERED -> {
                    logI(msg = "logicBgView entered")
                    isIn = true
                }
                DragEvent.ACTION_DRAG_EXITED -> {
                    logI(msg = "logicBgView exited")
                    isIn = false
                }
                DragEvent.ACTION_DROP -> {
                    logI(msg = "logicBgView drop")
                    val logicBlock = event.localState
                    if (isIn && mLogicBg.childCount == 0 && logicBlock is BaseLogicBlockView) {
                        (logicBlock.parent as? ViewGroup)?.removeView(logicBlock)
                        mLogicBg.addView(logicBlock)
                    } else {
                        logI(msg = "can't add view: isIn->$isIn count:${mLogicBg.childCount} logic:$logicBlock")
                    }
                }
            }
            return@setOnDragListener true
        }
        addView(mLogicBg, lp)

        val tvThen = AppCompatTextView(context)
        tvThen.setText(R.string.then)
        tvThen.textSize = TEXT_SIZE_BLOCK_12
        tvThen.setTextColor(ContextCompat.getColor(context, android.R.color.white))
        tvThen.tag = ChildTag.TAG_TOP
        addView(tvThen)
    }

    override suspend fun onRun(role: IRoleView) {
        if (mLogicBg.judgeResult()) {
            onChildRun(role)
        }
    }

    override fun clone(): IBaseBlock {
        val newObj = IfBlockView(context)
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