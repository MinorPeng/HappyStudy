package com.hesheng1024.happystudy.utils

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import com.hesheng1024.base.utils.logD

/**
 *
 * @author hesheng1024
 * @date 2020/4/22 8:58
 */

fun setSoftKeyBoardListener(activity: AppCompatActivity, listener: OnSoftKeyBoardChangeListener) {
    val rootView = activity.window.decorView
    var rootHeight = 0
    rootView.viewTreeObserver.addOnGlobalLayoutListener {
        val rect = Rect()
        rootView.getWindowVisibleDisplayFrame(rect)

        val visibleH = rect.height()
        logD(msg = "rh:$rootHeight vh:$visibleH")
        when {
            rootHeight == 0 -> {
                rootHeight = visibleH
                return@addOnGlobalLayoutListener
            }
            rootHeight == visibleH -> return@addOnGlobalLayoutListener
            rootHeight - visibleH > 200 -> {
                listener.onShow()
                rootHeight = visibleH
                return@addOnGlobalLayoutListener
            }
            else -> {
                listener.onHide()
                rootHeight = visibleH
                return@addOnGlobalLayoutListener
            }
        }
    }
}

interface OnSoftKeyBoardChangeListener {
    fun onShow()

    fun onHide()
}