package com.hesheng1024.happystudy.custom.blocks.motion

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.custom.blocks.BlockEditText
import com.hesheng1024.happystudy.custom.blocks.base.BaseLinearBlockView
import com.hesheng1024.happystudy.custom.blocks.base.IBaseBlock
import com.hesheng1024.happystudy.custom.role.IRoleView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 *
 * @author hesheng1024
 * @date 2020/3/28 19:04
 */
@SuppressLint("ViewConstructor")
class MoveToXYBlockView : BaseLinearBlockView {

    private val mEtX: BlockEditText
    private val mEtY: BlockEditText

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        setBgColorId(R.color.colorMotionBlue500)
        View.inflate(context, R.layout.layout_move_to_xy_block, this)
        mEtX = findViewById(R.id.et_move_to_xy_block_x)
        mEtY = findViewById(R.id.et_move_to_xy_block_y)
        initView()
    }

    private fun initView() {
    }

    override suspend fun onRun(role: IRoleView) {
        GlobalScope.launch(Dispatchers.Main) {
            role.moveToXY(mEtX.text.toString().toFloat(), mEtY.text.toString().toFloat())
        }
    }

    override fun clone(): IBaseBlock {
        val newObj = MoveToXYBlockView(context)
        newObj.layoutParams = this.layoutParams
        newObj.minimumWidth = measuredWidth
        newObj.minimumHeight = measuredHeight
        newObj.mEtX.setText(this.mEtX.text.toString())
        newObj.mEtY.setText(this.mEtY.text.toString())
        return newObj
    }
}