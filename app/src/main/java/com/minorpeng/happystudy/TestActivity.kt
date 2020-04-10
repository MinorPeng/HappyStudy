package com.minorpeng.happystudy

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.DragEvent
import android.view.HapticFeedbackConstants
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.minorpeng.base.utils.LogUtil
import com.minorpeng.happystudy.custom.blocks.appearance.NextBgBlockView
import com.minorpeng.happystudy.custom.blocks.motion.SetYBlockView
import kotlinx.android.synthetic.main.activity_test.*


/**
 *
 * @author MinorPeng
 * @date 2020/4/8 9:07
 */
class TestActivity : AppCompatActivity() {

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        setContentView(R.layout.activity_test)
        block_test.setOnLongClickListener { v ->
            LogUtil.i("long click")
            val intent = Intent()
            val clipData = ClipData.newIntent("label", intent)
            val block = NextBgBlockView(this)
            val shadowBuilder = View.DragShadowBuilder(v)
            v.startDrag(null, shadowBuilder, block, 0)
            //震动反馈
            v.performHapticFeedback(
                HapticFeedbackConstants.LONG_PRESS,
                HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING
            )
            return@setOnLongClickListener true
        }
        frame_test_end.setOnDragListener { v, event ->
            //v 永远是设置该监听的view，这里即fl_blue
            LogUtil.i("name:${v.javaClass.simpleName}")
            val block = event.localState as NextBgBlockView
            when(event.action) {
                DragEvent.ACTION_DRAG_STARTED -> {
                    LogUtil.i("start")
                    block.visibility = View.VISIBLE
                }
                DragEvent.ACTION_DRAG_ENDED -> {
                    LogUtil.i("end")
                    block.visibility = View.VISIBLE
                }
                DragEvent.ACTION_DRAG_ENTERED -> {
                    LogUtil.i("view in draging in frame")
                }
                DragEvent.ACTION_DRAG_EXITED -> {
                    LogUtil.i("view in draging out frame")
                }
                DragEvent.ACTION_DRAG_LOCATION -> {
                    LogUtil.i("view pos in frame: x->${event.x} y->${event.y}")
                }
                DragEvent.ACTION_DROP -> {
                    LogUtil.i("release draging view")
                    frame_test_end.addView(block)
                }
            }
            //是否响应拖拽事件，true响应，返回false只能接受到ACTION_DRAG_STARTED事件，后续事件不会收到
            return@setOnDragListener true
        }
        frame_test_start.setOnDragListener { v, event ->
            //v 永远是设置该监听的view，这里即fl_blue
            LogUtil.i("name:${v.javaClass.simpleName}")
            val block = event.localState as NextBgBlockView
            when(event.action) {
                DragEvent.ACTION_DRAG_STARTED -> {
                    LogUtil.i("start")
                    block.visibility = View.INVISIBLE
                }
                DragEvent.ACTION_DRAG_ENDED -> {
                    LogUtil.i("end")
                    block.visibility = View.VISIBLE
                }
                DragEvent.ACTION_DRAG_ENTERED -> {
                    LogUtil.i("view in draging in frame")
                }
                DragEvent.ACTION_DRAG_EXITED -> {
                    LogUtil.i("view in draging out frame")
                }
                DragEvent.ACTION_DRAG_LOCATION -> {
                    LogUtil.i("view pos in frame: x->${event.x} y->${event.y}")
                }
                DragEvent.ACTION_DROP -> {
                    LogUtil.i("release draging view")
                    (block.parent as ViewGroup).removeView(block)
                }
            }
            //是否响应拖拽事件，true响应，返回false只能接受到ACTION_DRAG_STARTED事件，后续事件不会收到
            return@setOnDragListener true
        }
    }
}