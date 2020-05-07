package com.hesheng1024.happystudy.custom.blocks.motion

import android.annotation.SuppressLint
import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import com.hesheng1024.base.utils.dp2px
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.custom.blocks.BlockEditText
import com.hesheng1024.happystudy.custom.blocks.BlockTextView
import com.hesheng1024.happystudy.custom.blocks.base.BaseLinearBlockView
import com.hesheng1024.happystudy.custom.blocks.base.IBaseBlock
import com.hesheng1024.happystudy.custom.role.IRoleView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 *
 * @author hesheng1024
 * @date 2020/3/28 19:05
 */
@SuppressLint("ViewConstructor")
class LeftRotateBlockView : BaseLinearBlockView {

    private val mEt: BlockEditText

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        setBgColorId(R.color.colorMotionBlue500)
        mEt = BlockEditText(context)
        initView()
    }

    private fun initView() {
        val tvLeftRotate = BlockTextView(context)
        tvLeftRotate.setText(R.string.left_rotate)
        addView(tvLeftRotate)

        val lp = generateDefaultLayoutParams() as MarginLayoutParams
        lp.leftMargin = dp2px(context, 4f)
        lp.rightMargin = dp2px(context, 4f)
        mEt.inputType = InputType.TYPE_CLASS_NUMBER
        addView(mEt, lp)

        val tvDegree = BlockTextView(context)
        tvDegree.setText(R.string.degree)
        addView(tvDegree)
    }

    override suspend fun onRun(role: IRoleView) {
        GlobalScope.launch(Dispatchers.Main) {
            role.leftRotate(mEt.text.toString().toFloat())
        }
    }

    override fun clone(): IBaseBlock {
        val newObj = LeftRotateBlockView(context)
        newObj.layoutParams = this.layoutParams
        newObj.minimumWidth = measuredWidth
        newObj.minimumHeight = measuredHeight
        newObj.mEt.setText(this.mEt.text.toString())
        return newObj
    }
}