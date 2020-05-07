package com.hesheng1024.happystudy.custom.blocks.voice

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.hesheng1024.base.utils.dp2px
import com.hesheng1024.happystudy.R
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
 * @date 2020/3/30 21:03
 */
@SuppressLint("ViewConstructor")
class PlayVoiceBlockView : BaseLinearBlockView {

    private var mSelectPos = 0

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        setBgColorId(R.color.colorVoicePurple500)
        initView()
    }

    private fun initView() {
        val whiteColor = ContextCompat.getColor(context, android.R.color.white)
        val tv = BlockTextView(context)
        tv.setText(R.string.when_str)
        addView(tv)

        val lp = generateDefaultLayoutParams() as MarginLayoutParams
        lp.leftMargin = dp2px(context, 4f)
        val spinner = Spinner(context)
        spinner.setBackgroundResource(R.drawable.bg_spinner_circle_purple)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (view is TextView) {
                    view.setTextColor(whiteColor)
                    view.gravity = Gravity.CENTER
                }
                mSelectPos = position
            }
        }
        spinner.adapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, listOf("声音1", "声音2", "声音3"))
        addView(spinner, lp)
    }

    override suspend fun onRun(role: IRoleView) {
        val rawId = when(mSelectPos) {
            0 -> R.raw.voice1
            1 -> R.raw.voice2
            2 -> R.raw.voice3
            else -> R.raw.voice1
        }
        GlobalScope.launch(Dispatchers.Main) {
            role.playVoice(rawId)
        }
    }

    override fun clone(): IBaseBlock {
        val newObj = PlayVoiceBlockView(context)
        newObj.layoutParams = this.layoutParams
        newObj.minimumWidth = measuredWidth
        newObj.minimumHeight = measuredHeight
        return newObj
    }
}