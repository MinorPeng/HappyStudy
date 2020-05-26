package com.hesheng1024.happystudy.custom.blocks

import android.content.Context
import android.text.InputType
import android.text.SpannableString
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.util.AttributeSet
import android.view.DragEvent
import android.view.Gravity
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.AppCompatEditText
import com.hesheng1024.base.utils.logD
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.TEXT_SIZE_BLOCK_12

/**
 *
 * @author hesheng1024
 * @date 2020/4/21 14:59
 */
class BlockEditText : AppCompatEditText, View.OnDragListener, View.OnFocusChangeListener {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, android.R.attr.editTextStyle)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        gravity = Gravity.CENTER
        textSize = TEXT_SIZE_BLOCK_12
        inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or
                InputType.TYPE_NUMBER_FLAG_SIGNED
        if (minEms == 0 || minEms == -1) {
            minEms = 2
        }
        // 修复hint字体大小和text字体大小不一致的问题，主要是text为number，hint为text时
        if (hint != null) {
            val ss = SpannableString(hint)
            val ass = AbsoluteSizeSpan(textSize.toInt())
            ss.setSpan(ass, 0, ss.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            hint = SpannableString(ss)
        }
        isCursorVisible = false
        imeOptions = EditorInfo.IME_ACTION_DONE
        setBackgroundResource(R.drawable.bg_et_circle_white)
        isSingleLine = true
        setOnDragListener(this)
        onFocusChangeListener = this
    }

    override fun onDrag(v: View?, event: DragEvent?): Boolean {
        return false
    }

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        isCursorVisible = hasFocus
    }
}