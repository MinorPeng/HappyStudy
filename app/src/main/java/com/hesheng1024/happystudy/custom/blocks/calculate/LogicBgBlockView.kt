package com.hesheng1024.happystudy.custom.blocks.calculate

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatEditText
import com.hesheng1024.base.utils.logI
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.custom.base.IBaseBlock
import com.hesheng1024.happystudy.custom.base.IRoleView

/**
 *
 * @author hesheng1024
 * @date 2020/4/4 15:22
 */
@SuppressLint("ViewConstructor")
class LogicBgBlockView : BaseLogicBlockView, View.OnDragListener {
    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        setBgColorId(R.color.colorCalculateGreenDark)
        setStatus(IBaseBlock.Status.STATUS_NONE)
        this.setPadding(0,0, 0, 0)
        minimumWidth = (sDisLeft * 3).toInt()
        minimumHeight = (sDisLeft * 2.3).toInt()
        setOnDragListener(this)
    }

    override fun onDrag(v: View?, event: DragEvent?): Boolean {
        if (v == null || event == null) {
            return false
        }
        when(event.action) {
            DragEvent.ACTION_DROP -> {
                logI(msg = "logicBgView drop")
                val logicBlock = event.localState
                if (childCount == 0 && logicBlock is BaseLogicBlockView) {
                    (logicBlock.parent as? ViewGroup)?.removeView(logicBlock)
                    addView(logicBlock)
                } else {
                    logI(msg = "can't add view:$logicBlock")
                }
            }
        }
        return true
    }

    override suspend fun onRun(role: IRoleView) {

    }

    override fun clone(): IBaseBlock {
        val newObj = LogicBgBlockView(context)
        newObj.layoutParams = this.layoutParams
        val child = getChildAt(0)
        if (child == null) {
            newObj.minimumWidth = measuredWidth
            newObj.minimumHeight = measuredHeight
        } else if (child is BaseLogicBlockView) {
            newObj.addView(child.clone() as BaseLogicBlockView)
        }
        return newObj
    }

    override fun judgeResult(): Boolean {
        val child = getChildAt(0)
        if (child != null && child is BaseLogicBlockView) {
            return child.judgeResult()
        }
        return false
    }

    override fun addView(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        removeAllViews()
        super.addView(child, index, params)
    }

    override fun addViewInLayout(
        child: View?,
        index: Int,
        params: ViewGroup.LayoutParams?,
        preventRequestLayout: Boolean
    ): Boolean {
        removeAllViews()
        return super.addViewInLayout(child, index, params, preventRequestLayout)
    }
}