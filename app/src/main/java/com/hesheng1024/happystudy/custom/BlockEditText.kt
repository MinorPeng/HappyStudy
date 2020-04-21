package com.hesheng1024.happystudy.custom

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.view.DragEvent
import android.view.Gravity
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.TEXT_SIZE_BLOCK_14

/**
 *
 * @author hesheng1024
 * @date 2020/4/21 14:59
 */
class BlockEditText : AppCompatEditText, View.OnDragListener {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        gravity = Gravity.CENTER
        textSize = TEXT_SIZE_BLOCK_14
        inputType = InputType.TYPE_NUMBER_FLAG_DECIMAL
        minEms = 2
        setBackgroundResource(R.drawable.bg_et_circle_whilte)
        setLines(1)
        setOnDragListener(this)
        setText(R.string.ten)
    }

    override fun onDrag(v: View?, event: DragEvent?): Boolean {
        return true
    }
}