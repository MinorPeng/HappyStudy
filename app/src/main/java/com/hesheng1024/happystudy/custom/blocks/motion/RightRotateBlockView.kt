package com.hesheng1024.happystudy.custom.blocks.motion

import android.annotation.SuppressLint
import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.view.View
import com.hesheng1024.happystudy.BLOCK_ROLE_RUN_DELAY
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.custom.blocks.BlockEditText
import com.hesheng1024.happystudy.custom.blocks.base.BaseLinearBlockView
import com.hesheng1024.happystudy.custom.blocks.base.IBaseBlock
import com.hesheng1024.happystudy.custom.role.IRoleView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 *
 * @author hesheng1024
 * @date 2020/3/28 19:06
 */
@SuppressLint("ViewConstructor")
class RightRotateBlockView : BaseLinearBlockView {

    private val mEt: BlockEditText

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        setBgColorId(R.color.colorMotionBlue500)
        View.inflate(context, R.layout.layout_right_rotate_block, this)
        mEt = findViewById(R.id.et_right_rotate_block_degree)
        initView()
    }

    private fun initView() {
        mEt.inputType = InputType.TYPE_CLASS_NUMBER
    }

    override suspend fun onRun(role: IRoleView) {
        delay(BLOCK_ROLE_RUN_DELAY)
        GlobalScope.launch(Dispatchers.Main) {
            role.rightRotate(mEt.text.toString().toFloat())
        }
    }

    override fun clone(): IBaseBlock {
        val newObj = RightRotateBlockView(context)
        newObj.layoutParams = this.layoutParams
        newObj.minimumWidth = measuredWidth
        newObj.minimumHeight = measuredHeight
        newObj.mEt.setText(this.mEt.text.toString())
        return newObj
    }
}