package com.hesheng1024.happystudy.modules.programme.v.impl

import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.MarginLayoutParamsCompat
import androidx.core.view.children
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hesheng1024.base.base.BaseActivity
import com.hesheng1024.base.utils.ContextHolder
import com.hesheng1024.base.utils.LogUtil
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.custom.base.BaseTextBlockView
import com.hesheng1024.happystudy.custom.base.IBaseBlock
import com.hesheng1024.happystudy.modules.programme.adapter.BlocksRecyclerAdapter
import com.hesheng1024.happystudy.modules.programme.m.Block
import com.hesheng1024.happystudy.modules.programme.p.ProgrammePresenter
import com.hesheng1024.happystudy.modules.programme.v.IProgrammeView
import kotlinx.android.synthetic.main.activity_programme.*
import kotlinx.android.synthetic.main.activity_test.*
import kotlinx.android.synthetic.main.layout_block_categories.*
import kotlinx.android.synthetic.main.layout_programme_utils.*

/**
 *
 * @author hesheng1024
 * @date 2020/4/7 20:26
 */
class ProgrammeActivity : BaseActivity<ProgrammePresenter>(), IProgrammeView {

    private val mAdapter = BlocksRecyclerAdapter()
    private val mSelectedColor = ContextCompat.getColor(ContextHolder.getMainContext(), R.color.colorGrayBgSelectedE)
    private val mUnSelectedColor = ContextCompat.getColor(ContextHolder.getMainContext(), android.R.color.white)
    private var mLastSelectCategory = Block.Category.MOTION

    /**
     * recycler 联动滚动标志量
     */
    private var mToPos = 0
    private var mShouldScroll = false

    override fun getLayoutId(): Int {
        return R.layout.activity_programme
    }

    override fun createPresenter(): ProgrammePresenter {
        return ProgrammePresenter(this)
    }

    override fun initView() {
        ibtn_programme_back.setOnClickListener { finishActivity() }
        initBlockCategory()
        initRecyclerView()
        initProgramme()

        switch_programme_show.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked && iv_programme_role.visibility != View.VISIBLE) {
                iv_programme_role.visibility = View.VISIBLE
            } else {
                if (iv_programme_role.visibility != View.INVISIBLE) {
                    iv_programme_role.visibility = View.INVISIBLE
                }
            }
        }
        ibtn_programme_run.setOnClickListener {
            runProgramme()
        }
    }

    private fun initBlockCategory() {
        tv_programme_motion.setBackgroundColor(mSelectedColor)
        tv_programme_motion.setOnClickListener {
            val pos = mAdapter.getPosByCategory(Block.Category.MOTION)
            if (pos != -1) {
                changeSelectedCategory(Block.Category.MOTION)
                smoothScrollToPosition(pos)
            }
        }
        tv_programme_appearance.setOnClickListener {
            val pos = mAdapter.getPosByCategory(Block.Category.APPEARANCE)
            if (pos != -1) {
                changeSelectedCategory(Block.Category.APPEARANCE)
                smoothScrollToPosition(pos)
            }
        }
        tv_programme_voice.setOnClickListener {
            val pos = mAdapter.getPosByCategory(Block.Category.VOICE)
            if (pos != -1) {
                changeSelectedCategory(Block.Category.VOICE)
                smoothScrollToPosition(pos)
            }
        }
        tv_programme_event.setOnClickListener {
            val pos = mAdapter.getPosByCategory(Block.Category.EVENT)
            if (pos != -1) {
                changeSelectedCategory(Block.Category.EVENT)
                smoothScrollToPosition(pos)
            }
        }
        tv_programme_control.setOnClickListener {
            val pos = mAdapter.getPosByCategory(Block.Category.CONTROL)
            if (pos != -1) {
                changeSelectedCategory(Block.Category.CONTROL)
                smoothScrollToPosition(pos)
            }
        }
        tv_programme_calculate.setOnClickListener {
            val pos = mAdapter.getPosByCategory(Block.Category.CALCULATE)
            if (pos != -1) {
                changeSelectedCategory(Block.Category.CALCULATE)
                smoothScrollToPosition(pos)
            }
        }
    }

    private fun initRecyclerView() {
        recycler_view_programme.adapter = mAdapter
        val layoutManager = LinearLayoutManager(this)
        recycler_view_programme.layoutManager = layoutManager
        recycler_view_programme.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (mShouldScroll && RecyclerView.SCROLL_STATE_IDLE == newState) {
                    mShouldScroll = false
                    smoothScrollToPosition(mToPos)
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val firstItem = recyclerView.getChildLayoutPosition(recyclerView.getChildAt(0))
                val category = mAdapter.getData(firstItem).category
                changeSelectedCategory(category)
            }
        })
        recycler_view_programme.setOnDragListener { v, event ->
            val block = event.localState
            if (block !is IBaseBlock || block !is View) {
                return@setOnDragListener true
            }
            when (event.action) {
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
                    LogUtil.i(msg = "release dragging view")
                    (block.parent as? ViewGroup)?.removeView(block)
                }
            }
            //是否响应拖拽事件，true响应，返回false只能接受到ACTION_DRAG_STARTED事件，后续事件不会收到
            return@setOnDragListener true
        }
        mPresenter.getBlocks(this)
    }

    private fun initProgramme() {
        linear_layout_programme.setOnDragListener { v, event ->
            for (child in linear_layout_programme.children) {
                LogUtil.d(msg = "c$child")
                if (child is IBaseBlock && child.onDrag(child, event)) {
                    return@setOnDragListener true
                }
            }
            val block = event.localState
            if (block !is IBaseBlock || block !is View) {
                return@setOnDragListener true
            }
            when (event.action) {
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
                    block.setStatus(IBaseBlock.Status.STATUS_DRAG)
                    val lp = LinearLayout.LayoutParams(block.layoutParams)
                    // lp.leftMargin = event.x.toInt() - lp.width / 2
                    // lp.topMargin = event.y.toInt() - lp.height / 2
                    // lp.topMargin = -IBaseBlock.sDis2Top.toInt()
                    (block.layoutParams as LinearLayout.LayoutParams).bottomMargin = -IBaseBlock.sDis2Top.toInt()
                    linear_layout_programme.addView(block, block.layoutParams)
                }
            }
            return@setOnDragListener  true
        }
    }

    private fun smoothScrollToPosition(pos: Int) {
        val firstItem = recycler_view_programme.getChildLayoutPosition(recycler_view_programme.getChildAt(0))
        val lastItem = recycler_view_programme.getChildLayoutPosition(
            recycler_view_programme.getChildAt(recycler_view_programme.childCount - 1)
        )
        LogUtil.d(msg = "fir:$firstItem las:$lastItem pos:$pos")
        if (pos < firstItem) {
            // 跳转位置在可视界面之前，向前滑动
            recycler_view_programme.smoothScrollToPosition(pos)
        } else if (pos <= lastItem) {
            // 跳转位置在可视界面内，置顶即可
            val movePos = pos - firstItem
            LogUtil.d(msg = "movePos:$movePos childCount:${recycler_view_programme.childCount}")
            if (movePos >= 0 && movePos < recycler_view_programme.childCount) {
                val top = recycler_view_programme.getChildAt(movePos).top
                LogUtil.d(msg = "top:$top")
                recycler_view_programme.smoothScrollBy(0, top)
            }
        } else {
            // 跳转位置在可视界面之后，需要先滑动到可视界面内，再置顶
            recycler_view_programme.smoothScrollToPosition(pos)
            mToPos = pos
            mShouldScroll = true
        }
    }

    private fun changeSelectedCategory(category: Block.Category) {
        if (category == mLastSelectCategory) {
            return
        }
        LogUtil.d(msg = "last:${mLastSelectCategory} now:$category")
        when (mLastSelectCategory) {
            Block.Category.MOTION -> tv_programme_motion.setBackgroundColor(mUnSelectedColor)
            Block.Category.APPEARANCE -> tv_programme_appearance.setBackgroundColor(mUnSelectedColor)
            Block.Category.VOICE -> tv_programme_voice.setBackgroundColor(mUnSelectedColor)
            Block.Category.EVENT -> tv_programme_event.setBackgroundColor(mUnSelectedColor)
            Block.Category.CONTROL -> tv_programme_control.setBackgroundColor(mUnSelectedColor)
            Block.Category.CALCULATE -> tv_programme_calculate.setBackgroundColor(mUnSelectedColor)
        }
        when (category) {
            Block.Category.MOTION -> tv_programme_motion.setBackgroundColor(mSelectedColor)
            Block.Category.APPEARANCE -> tv_programme_appearance.setBackgroundColor(mSelectedColor)
            Block.Category.VOICE -> tv_programme_voice.setBackgroundColor(mSelectedColor)
            Block.Category.EVENT -> tv_programme_event.setBackgroundColor(mSelectedColor)
            Block.Category.CONTROL -> tv_programme_control.setBackgroundColor(mSelectedColor)
            Block.Category.CALCULATE -> tv_programme_calculate.setBackgroundColor(mSelectedColor)
        }
        mLastSelectCategory = category
    }

    private fun runProgramme() {
        val x = et_programme_x.text.toString()
        val y = et_programme_y.text.toString()
        val direction = et_programme_direction.text.toString()

    }

    override fun setBlocks(blocks: List<Block>) {
        mAdapter.clear()
        mAdapter.addDatas(blocks)
    }
}