package com.minorpeng.happystudy.modules.programme

import android.view.View
import com.minorpeng.happystudy.R

/**
 *
 * @author MinorPeng
 * @date 2020/4/8 15:46
 */
data class Block(val category: Category, val view: View) {
    enum class Category(resId: Int) {
        MOTION(R.string.motion),
        APPEARANCE(R.string.appearance),
        VOICE(R.string.voice),
        EVENT(R.string.event),
        CONTROL(R.string.control),
        CALCULATE(R.string.calculate)
    }
}
