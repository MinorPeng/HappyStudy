package com.hesheng1024.happystudy.modules

import android.view.View
import com.hesheng1024.happystudy.R

/**
 *
 * @author hesheng1024
 * @date 2020/4/8 15:46
 */
data class Block(val category: Category, val view: View, val content: String = "") {
    enum class Category(val resId: Int) {
        MOTION(R.string.motion),
        APPEARANCE(R.string.appearance),
        VOICE(R.string.voice),
        EVENT(R.string.event),
        CONTROL(R.string.control),
        CALCULATE(R.string.calculate),
        DRAW(R.string.draw);
    }
}
