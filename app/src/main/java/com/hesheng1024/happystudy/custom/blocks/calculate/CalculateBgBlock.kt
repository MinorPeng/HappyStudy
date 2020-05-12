package com.hesheng1024.happystudy.custom.blocks.calculate

import android.content.Context
import android.util.AttributeSet
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatEditText
import com.hesheng1024.base.utils.logD
import com.hesheng1024.base.utils.logI
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.custom.blocks.BlockEditText
import com.hesheng1024.happystudy.custom.blocks.base.IBaseBlock
import com.hesheng1024.happystudy.custom.role.IRoleView

/**
 *
 * @author hesheng1024
 * @date 2020/4/17 21:39
 */
class CalculateBgBlock : BaseCalculateBlockView, View.OnDragListener {

    private val mEt: BlockEditText

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        this.setBgColorId(android.R.color.transparent)
        setStatus(IBaseBlock.Status.STATUS_NONE)
        this.setPadding(0, 0, 0, 0)
        setOnDragListener(this)
        mEt = BlockEditText(context)
        mEt.setText(R.string.zero)
    }

    override fun getBgBorderColor(): Int {
        return android.R.color.transparent
    }

    override fun onDrag(v: View?, event: DragEvent?): Boolean {
        if (v == null || event == null) {
            return false
        }
        logD(msg = "event:$event")
        val calculateBlock = event.localState
        when (event.action) {
            DragEvent.ACTION_DRAG_EXITED -> {
                if (calculateBlock == getChildAt(0)) {
                    (mEt.parent as? ViewGroup)?.removeView(mEt)
                    addView(mEt)
                }
            }
            DragEvent.ACTION_DROP -> {
                logI(msg = "logicBgView drop")
                if (calculateBlock is BaseCalculateBlockView) {
                    (calculateBlock.parent as? ViewGroup)?.removeView(calculateBlock)
                    addView(calculateBlock)
                } else {
                    logI(msg = "can't add view:$calculateBlock")
                }
            }
        }
        return true
    }

    override suspend fun onRun(role: IRoleView) {

    }

    override fun clone(): IBaseBlock {
        val newObj = CalculateBgBlock(context)
        newObj.layoutParams = this.layoutParams
        when (val child = getChildAt(0)) {
            null -> {
                newObj.minimumWidth = measuredWidth
                newObj.minimumHeight = measuredHeight
            }
            is AppCompatEditText -> {
                val newEt = AppCompatEditText(context)
                newEt.setText(child.text.toString())
                newObj.addView(newEt, 0)
            }
            is BaseCalculateBlockView -> {
                newObj.addView(child.clone() as BaseCalculateBlockView, 0)
            }
        }
        return newObj
    }

    fun clone(other: CalculateBgBlock) {
        when (val child = other.getChildAt(0)) {
            is AppCompatEditText -> {
                if (getChildAt(0) is AppCompatEditText) {
                    (getChildAt(0) as AppCompatEditText).setText(child.text.toString())
                } else {
                    val newEt = AppCompatEditText(context)
                    newEt.setText(child.text.toString())
                    addView(newEt, 0)
                }
            }
            is BaseCalculateBlockView -> {
                addView(child.clone() as BaseCalculateBlockView, 0)
            }
        }
    }

    override fun calculateResult(): Float {
        return when (val child = getChildAt(0)) {
            is AppCompatEditText -> {
                if (child.text.isNullOrEmpty()) {
                    0f
                } else {
                    child.text.toString().toFloat()
                }
            }
            is BaseCalculateBlockView -> child.calculateResult()
            else -> -1f
        }
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