package com.hesheng1024.happystudy.custom.blocks.control

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.hesheng1024.base.utils.DensityUtil
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.custom.blocks.motion.MoveBlockView

/**
 *
 * @author hesheng1024
 * @date 2020/4/1 11:04
 */
@SuppressLint("ViewConstructor")
class CirculationNumBlockView : BaseControlBlockView {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
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

        val lpEtCount = generateDefaultLayoutParams() as MarginLayoutParams
        lpEtCount.leftMargin = DensityUtil.dp2px(context, 8f)
        lpEtCount.rightMargin =  DensityUtil.dp2px(context, 8f)
        val etCount = EditText(context)
        etCount.setText(R.string.ten)
        etCount.minEms = 2
        etCount.tag = ChildTag.TAG_TOP
        etCount.gravity = Gravity.CENTER
        etCount.setBackgroundResource(R.drawable.bg_et_circle_whilte)
        addView(etCount, lpEtCount)


        val tvCount = TextView(context)
        tvCount.setText(R.string.count)
        tvCount.tag = ChildTag.TAG_TOP
        tvCount.setTextColor(whiteColor)
        addView(tvCount)


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