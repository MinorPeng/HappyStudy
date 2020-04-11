package com.minorpeng.happystudy.custom.blocks.control

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.minorpeng.happystudy.R
import com.minorpeng.happystudy.custom.blocks.motion.MoveBlockView

/**
 *
 * @author MinorPeng
 * @date 2020/4/1 11:02
 */
@SuppressLint("ViewConstructor")
class DeathCirculationBlockView(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes:
    Int = 0
) : BaseControlBlockView(context, attrs, defStyleAttr, defStyleRes) {

    init {
        initView()
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        val whiteColor = ContextCompat.getColor(context, android.R.color.white)
        val tvCirculation = TextView(context)
        tvCirculation.setText(R.string.circulation)
        tvCirculation.tag = ChildTag.TAG_TOP
        tvCirculation.setTextColor(whiteColor)
        addView(tvCirculation)

        val ivCirculation = ImageView(context)
        ivCirculation.setImageResource(R.drawable.ic_circulation_16)
        ivCirculation.tag = ChildTag.TAG_BOTTOM
        addView(ivCirculation)

        // TODO test
        val mo = MoveBlockView(context)
        mo.tag = ChildTag.TAG_CHILD
        addView(mo)
    }

    override fun onRun(role: View) {

    }
}