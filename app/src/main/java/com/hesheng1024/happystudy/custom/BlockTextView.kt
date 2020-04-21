package com.hesheng1024.happystudy.custom

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.hesheng1024.happystudy.TEXT_SIZE_BLOCK_14

/**
 *
 * @author hesheng1024
 * @date 2020/4/21 15:02
 */
open class BlockTextView : AppCompatTextView {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        this.setTextColor(ContextCompat.getColor(context, android.R.color.white))
        textSize = TEXT_SIZE_BLOCK_14
    }
}