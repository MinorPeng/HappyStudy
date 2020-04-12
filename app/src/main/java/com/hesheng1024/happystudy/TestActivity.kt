package com.hesheng1024.happystudy

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.hesheng1024.base.utils.LogUtil
import com.hesheng1024.happystudy.custom.base.BaseTextBlockView
import com.hesheng1024.happystudy.custom.base.IBaseBlockBg
import com.hesheng1024.happystudy.custom.blocks.appearance.NextBgBlockView
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
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        setContentView(R.layout.activity_test)
        frame_test_end.setOnDragListener { v, event ->
            //v 永远是设置该监听的view，这里即fl_blue
            LogUtil.i(msg = "name:${v.javaClass.simpleName}")
            val block = event.localState as BaseTextBlockView
            when(event.action) {
                DragEvent.ACTION_DRAG_STARTED -> {
                    LogUtil.i(msg = "start")
                    block.visibility = View.VISIBLE
                }
                DragEvent.ACTION_DRAG_ENDED -> {
                    LogUtil.i(msg = "end")
                    block.visibility = View.VISIBLE
                }
                DragEvent.ACTION_DRAG_ENTERED -> {
                    LogUtil.i(msg = "view in dragging in frame")
                }
                DragEvent.ACTION_DRAG_EXITED -> {
                    LogUtil.i(msg = "view in dragging out frame")
                }
                DragEvent.ACTION_DRAG_LOCATION -> {
                    LogUtil.i(msg = "view pos in frame: x->${event.x} y->${event.y}")
                }
                DragEvent.ACTION_DROP -> {
                    (block.parent as? ViewGroup)?.removeView(block)
                    block.setStatus(IBaseBlockBg.Status.STATUS_DRAG)
                    val lp = FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                    lp.leftMargin = event.x.toInt() - block.minWidth / 2
                    lp.topMargin = event.y.toInt() - block.minHeight / 2
                    LogUtil.i(msg = "release dragging view x:${event.x} y:${event.y}" +
                            " w:${block.width / 2} h:${block.height / 2} l:${lp.leftMargin} r:${lp.topMargin}")
                    frame_test_end.addView(block, lp)
                }
            }
            //是否响应拖拽事件，true响应，返回false只能接受到ACTION_DRAG_STARTED事件，后续事件不会收到
            return@setOnDragListener true
        }
        frame_test_start.setOnDragListener { v, event ->
            //v 永远是设置该监听的view，这里即fl_blue
            LogUtil.i(msg = "name:${v.javaClass.simpleName}")
            val block = event.localState as NextBgBlockView
            when(event.action) {
                DragEvent.ACTION_DRAG_STARTED -> {
                    LogUtil.i(msg = "start")
                    block.visibility = View.INVISIBLE
                }
                DragEvent.ACTION_DRAG_ENDED -> {
                    LogUtil.i(msg = "end")
                    block.visibility = View.VISIBLE
                }
                DragEvent.ACTION_DRAG_ENTERED -> {
                    LogUtil.i(msg = "view in dragging in frame")
                }
                DragEvent.ACTION_DRAG_EXITED -> {
                    LogUtil.i(msg = "view in dragging out frame")
                }
                DragEvent.ACTION_DRAG_LOCATION -> {
                    LogUtil.i(msg = "view pos in frame: x->${event.x} y->${event.y}")
                }
                DragEvent.ACTION_DROP -> {
                    LogUtil.i(msg = "release draging view")
                    (block.parent as? ViewGroup)?.removeView(block)
                }
            }
            //是否响应拖拽事件，true响应，返回false只能接受到ACTION_DRAG_STARTED事件，后续事件不会收到
            return@setOnDragListener true
        }
    }
}