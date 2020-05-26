package com.hesheng1024.happystudy.custom.blocks.calculate

import android.content.Context
import android.util.AttributeSet
import android.util.TimeUtils
import android.view.View
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.custom.blocks.base.IBaseBlock
import com.hesheng1024.happystudy.custom.role.IRoleView
import com.hesheng1024.spinner.MaterialSpinner
import java.util.*

/**
 *
 * @author hesheng1024
 * @date 2020/5/26 8:38
 */
class GetTimeBlockView : BaseCalculateBlockView {

    private val mSpinner: MaterialSpinner
    private var mSelectType = "h"

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        View.inflate(context, R.layout.layout_get_time_block, this)
        mSpinner = findViewById(R.id.spinner_get_time_block)
        mSpinner.setOnItemSelectedListener(object : MaterialSpinner.OnItemSelectedListener {
            override fun onItemSelected(view: MaterialSpinner?, position: Int, id: Long, item: Any) {
                mSelectType = item.toString()
            }
        })
    }

    override fun calculateResult(): Float {
        return when(mSelectType) {
            "h" -> Calendar.getInstance().get(Calendar.HOUR) * 360 / 12
            "m" -> Calendar.getInstance().get(Calendar.MINUTE) * 360 / 60
            "s" -> Calendar.getInstance().get(Calendar.SECOND) * 360 / 60
            "Y" -> Calendar.getInstance().get(Calendar.YEAR)
            "M" -> Calendar.getInstance().get(Calendar.MONTH) + 1
            "D" -> Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            else -> -1
        }.toFloat()
    }

    override fun clone(): IBaseBlock {
        val newObj = GetTimeBlockView(context)
        newObj.layoutParams = this.layoutParams
        newObj.minimumWidth = measuredWidth
        newObj.minimumHeight = measuredHeight
        newObj.mSpinner.setSelectedIndex(mSpinner.getSelectedIndex())
        return newObj
    }

    override suspend fun onRun(role: IRoleView) {
    }
}