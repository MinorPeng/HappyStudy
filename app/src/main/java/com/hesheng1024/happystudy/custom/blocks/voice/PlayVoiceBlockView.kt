package com.hesheng1024.happystudy.custom.blocks.voice

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.custom.blocks.base.BaseLinearBlockView
import com.hesheng1024.happystudy.custom.blocks.base.IBaseBlock
import com.hesheng1024.happystudy.custom.role.IRoleView
import com.hesheng1024.spinner.MaterialSpinner
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

    private val mSpinner: MaterialSpinner
    private val mVoiceMap = LinkedHashMap<String, Int>()
    private var mVoiceId: Int? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        setBgColorId(R.color.colorVoicePurple500)
        View.inflate(context, R.layout.layout_play_voice_block, this)
        mSpinner = findViewById(R.id.spinner_play_voice_block)
        initView()
    }

    private fun initView() {
        mVoiceMap["声音1"] = R.raw.voice1
        mVoiceMap["声音2"] = R.raw.voice2
        mVoiceMap["声音3"] = R.raw.voice3
        val list = mVoiceMap.keys.toList()
        mVoiceId = mVoiceMap[list[0]]
        mSpinner.setItems(list)
        mSpinner.setOnItemSelectedListener(object : MaterialSpinner.OnItemSelectedListener {
            override fun onItemSelected(view: MaterialSpinner?, position: Int, id: Long, item: Any) {
                if (item is String) {
                    mVoiceId = mVoiceMap[item]
                }
            }
        })
    }

    override suspend fun onRun(role: IRoleView) {
        GlobalScope.launch(Dispatchers.Main) {
            mVoiceId?.let {
                role.playVoice(it)
            }
        }
    }

    override fun clone(): IBaseBlock {
        val newObj = PlayVoiceBlockView(context)
        newObj.layoutParams = this.layoutParams
        newObj.minimumWidth = measuredWidth
        newObj.minimumHeight = measuredHeight
        newObj.mSpinner.setSelectedIndex(mSpinner.getSelectedIndex())
        return newObj
    }
}