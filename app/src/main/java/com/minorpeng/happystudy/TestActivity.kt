package com.minorpeng.happystudy

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.minorpeng.happystudy.custom.blocks.motion.MoveBlockView
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
        val moveBlockView = MoveBlockView(this)
        frame_layout_test.addView(moveBlockView)
    }
}