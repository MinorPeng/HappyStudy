package com.hesheng1024.happystudy

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import com.hesheng1024.base.utils.logI
import com.hesheng1024.happystudy.custom.base.IBaseBlock
import kotlinx.android.synthetic.main.activity_test.*

/**
 *
 * @author hesheng1024
 * @date 2020/4/8 9:07
 */
class TestActivity : AppCompatActivity() {

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        initView()
    }

    private fun initView() {
        btn_test.setOnClickListener {
        }
        // (tv_test.layoutParams as ViewGroup.MarginLayoutParams).leftMargin = 16
        // (tv_test.layoutParams as ViewGroup.MarginLayoutParams).topMargin = 16
        // MediaPlayerUtil.play()
        frame_test_end.setOnDragListener { v, event ->
            //v 永远是设置该监听的view，这里即fl_blue
            // logI(msg = "name:${v.javaClass.simpleName}")
            val block = event.localState
            if (block !is IBaseBlock || block !is View) {
                return@setOnDragListener true
            }

            for (child in frame_test_end.children) {
                // LogUtil.d(msg = "c$child")
                if (child is IBaseBlock) {
                    val customDragEvent = IBaseBlock.CustomDragEvent(event.x, event.y, event.action, event.localState,
                        event.clipData, event.clipDescription, event.result)
                    if (child.onDragEv(customDragEvent)) {
                        return@setOnDragListener true
                    }
                }
            }

            when(event.action) {
                DragEvent.ACTION_DRAG_STARTED -> {
                    logI(msg = "start")
                    block.visibility = View.VISIBLE
                }
                DragEvent.ACTION_DRAG_ENDED -> {
                    logI(msg = "end")
                    block.visibility = View.VISIBLE
                }
                DragEvent.ACTION_DRAG_ENTERED -> {
                    logI(msg = "view in dragging in frame")
                }
                DragEvent.ACTION_DRAG_EXITED -> {
                    logI(msg = "view in dragging out frame")
                }
                DragEvent.ACTION_DRAG_LOCATION -> {
                    // logI(msg = "view pos in frame: x->${event.x} y->${event.y}")
                }
                DragEvent.ACTION_DROP -> {
                    (block.parent as? ViewGroup)?.removeView(block)
                    block.setStatus(IBaseBlock.Status.STATUS_DRAG)
                    val lp = FrameLayout.LayoutParams(block.layoutParams)
                    lp.leftMargin = event.x.toInt() - lp.width / 2
                    lp.topMargin = event.y.toInt() - lp.height / 2
                    logI(msg = "release dragging view x:${event.x} y:${event.y}" +
                            " w:${lp.width} h:${lp.height} l:${lp.leftMargin} r:${lp.topMargin}")
                    frame_test_end.addView(block, lp)
                }
            }
            //是否响应拖拽事件，true响应，返回false只能接受到ACTION_DRAG_STARTED事件，后续事件不会收到
            return@setOnDragListener true
        }
        frame_test_start.setOnDragListener { v, event ->
            //v 永远是设置该监听的view，这里即fl_blue
            logI(msg = "name:${v.javaClass.simpleName}")
            val block = event.localState
            if (block !is IBaseBlock || block !is View) {
                return@setOnDragListener true
            }
            when(event.action) {
                DragEvent.ACTION_DRAG_STARTED -> {
                    logI(msg = "start")
                    block.visibility = View.INVISIBLE
                }
                DragEvent.ACTION_DRAG_ENDED -> {
                    logI(msg = "end")
                    block.visibility = View.VISIBLE
                }
                DragEvent.ACTION_DRAG_ENTERED -> {
                    logI(msg = "view in dragging in frame")
                }
                DragEvent.ACTION_DRAG_EXITED -> {
                    logI(msg = "view in dragging out frame")
                }
                DragEvent.ACTION_DRAG_LOCATION -> {
                    // logI(msg = "view pos in frame: x->${event.x} y->${event.y}")
                }
                DragEvent.ACTION_DROP -> {
                    logI(msg = "release dragging view")
                    (block.parent as? ViewGroup)?.removeView(block)
                }
            }
            //是否响应拖拽事件，true响应，返回false只能接受到ACTION_DRAG_STARTED事件，后续事件不会收到
            return@setOnDragListener true
        }
    }
}