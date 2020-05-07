package com.hesheng1024.happystudy.custom.blocks.appearance

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
import com.hesheng1024.happystudy.custom.blocks.base.IRoleListener
import com.hesheng1024.happystudy.custom.role.IRoleView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 *
 * @author hesheng1024
 * @date 2020/3/29 19:34
 */
@SuppressLint("ViewConstructor")
class SayBlockView : BaseLinearBlockView, IRoleListener {

    private val mEtContent: BlockEditText

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        setBgColorId(R.color.colorAppearanceDeepPurple500)
        mEtContent = BlockEditText(context)
        initView()
    }

    private fun initView() {
        val tv = BlockTextView(context)
        tv.setText(R.string.say)
        addView(tv, 0)

        val lp = generateDefaultLayoutParams() as MarginLayoutParams
        lp.leftMargin = dp2px(context, 4f)
        mEtContent.setText(R.string.hello)
        mEtContent.inputType = InputType.TYPE_CLASS_TEXT
        addView(mEtContent, 1, lp)
    }

    override suspend fun onRun(role: IRoleView) {
        GlobalScope.launch(Dispatchers.Main) {
            role.say(mEtContent.text.toString())
        }
    }

    override fun clone(): IBaseBlock {
        val newObj = SayBlockView(context)
        newObj.layoutParams = this.layoutParams
        newObj.minimumWidth = measuredWidth
        newObj.minimumHeight = measuredHeight
        newObj.mEtContent.setText(this.mEtContent.text.toString())
        return newObj
    }
}