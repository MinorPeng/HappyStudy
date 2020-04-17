package com.hesheng1024.happystudy.custom.blocks.control

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.hesheng1024.base.utils.DensityUtil
import com.hesheng1024.base.utils.LogUtil
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.custom.base.IBaseBlock
import com.hesheng1024.happystudy.custom.base.IRoleView
import com.hesheng1024.happystudy.custom.blocks.calculate.BaseCalculateBlockView
import com.hesheng1024.happystudy.custom.blocks.calculate.BaseLogicBlockView
import com.hesheng1024.happystudy.custom.blocks.calculate.LogicBgBlockView

/**
 *
 * @author hesheng1024
 * @date 2020/4/4 17:11
 */
@SuppressLint("ViewConstructor")
class CirculationUtilBlockView : BaseControlBlockView {

    private val mLogicBgView: LogicBgBlockView

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        mLogicBgView = LogicBgBlockView(context)
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
        mLogicBgView.setBgColorId(R.color.colorControlYellowDark)
        mLogicBgView.tag = ChildTag.TAG_TOP
        // 也可以直接在父类中统一监听，只是坐标计算相对复杂一点
        mLogicBgView.setOnDragListener { v, event ->
            var isIn = false
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
                    if (isIn && mLogicBgView.childCount == 0 && logicBlock is BaseLogicBlockView) {
                        mLogicBgView.addView(logicBlock)
                    } else {
                        LogUtil.i(msg = "can't add view: isIn->$isIn count:${mLogicBgView.childCount} logic:$logicBlock")
                    }
                }
            }
            return@setOnDragListener true
        }
        addView(mLogicBgView, lp)

        val ivCirculation = ImageView(context)
        ivCirculation.setImageResource(R.drawable.ic_circulation_16)
        ivCirculation.tag = ChildTag.TAG_BOTTOM
        addView(ivCirculation)

        // TODO test
        // val mo = MoveBlockView(context)
        // mo.tag = ChildTag.TAG_CHILD
        // addView(mo)
    }

    override fun onDragEv(event: DragEvent?): Boolean {
        if (event == null) {
            LogUtil.e(msg = "v:$this event:$event")
            return false
        }
        val block = event.localState
        if (block !is BaseLogicBlockView && block !is BaseCalculateBlockView
            && block is IBaseBlock && block is View && block.getBlackOwn() is View) {
            val blackOwn = block.getBlackOwn() as View
            val oldBlockTag = block.tag
            val oldBlackOwnTag = blackOwn.tag
            block.tag = ChildTag.TAG_CHILD
            blackOwn.tag = ChildTag.TAG_CHILD
            for (index in 0 until childCount) {
                val child = getChildAt(index)
                // 传递给子View进行判断处理 排除掉阴影
                if (child.tag == ChildTag.TAG_CHILD
                    && child is IBaseBlock
                    && child.getStatus() == IBaseBlock.Status.STATUS_DRAG
                    && child.onDragEvent (event)) {
                    return true
                }
            }
            // 除了第一次没有子View会走下面逻辑以外，有子View的情况都不应该走以下逻辑
            when (event.action) {
                DragEvent.ACTION_DRAG_LOCATION -> {
                    val x = event.x - left
                    val y = event.y - top
                    if (isInChildRectF(x, y) && blackOwn.parent == null) {
                        addView(blackOwn)
                    } else {
                        removeView(blackOwn)
                    }
                }
                DragEvent.ACTION_DROP -> {
                    val x = event.x - left
                    val y = event.y - top
                    LogUtil.i(msg = "drop in child: x->$x y->$y l:$left t:$top")
                    if (isInChildRectF(x, y)) {
                        (block.parent as? ViewGroup)?.removeView(block)
                        removeView(blackOwn)
                        addView(block)
                        return true
                    }
                }
                else -> {
                    // 如果该ViewGroup不处理，应当将标识重置为之前的状态
                    block.tag = oldBlockTag
                    blackOwn.tag = oldBlackOwnTag
                }
            }
        } else {
            LogUtil.d(msg = "block:$block")
        }
        return super.onDragEv(event)
    }

    override fun onRun(role: IRoleView) {

    }

    override fun clone(): IBaseBlock {
        val newObj = CirculationUtilBlockView(context)
        newObj.layoutParams = this.layoutParams
        if (newObj.layoutParams.width <= 0 || newObj.layoutParams.height <= 0) {
            newObj.layoutParams.width = measuredWidth
            newObj.layoutParams.height = measuredHeight
        }
        return newObj
    }
}