package com.hesheng1024.happystudy.custom.blocks.motion

import android.annotation.SuppressLint
import android.content.Context
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
 * @date 2020/3/24 18:12
 */
@SuppressLint("ViewConstructor")
class MoveBlockView : BaseLinearBlockView {

    private val mEt: BlockEditText

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        setBgColorId(R.color.colorMotionBlue500)
        View.inflate(context, R.layout.layout_move_block, this)
        mEt = findViewById(R.id.et_move_block_step)
        initView()
    }

    private fun initView() {
    }

    override suspend fun onRun(role: IRoleView) {
        delay(BLOCK_ROLE_RUN_DELAY)
        GlobalScope.launch(Dispatchers.Main) {
            role.moveStep(mEt.text.toString().toInt())
        }
    }

    override fun clone(): IBaseBlock {
        val newObj = MoveBlockView(context)
        newObj.layoutParams = this.layoutParams
        newObj.minimumWidth = measuredWidth
        newObj.minimumHeight = measuredHeight
        newObj.mEt.setText(this.mEt.text.toString())
        return newObj
    }
}