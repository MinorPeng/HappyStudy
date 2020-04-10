package com.minorpeng.happystudy.modules.programme.v.impl

import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.minorpeng.base.base.BaseActivity
import com.minorpeng.base.utils.ContextHolder
import com.minorpeng.base.utils.LogUtil
import com.minorpeng.happystudy.R
import com.minorpeng.happystudy.modules.programme.BlocksRecyclerAdapter
import com.minorpeng.happystudy.modules.programme.m.Block
import com.minorpeng.happystudy.modules.programme.p.ProgrammePresenter
import com.minorpeng.happystudy.modules.programme.v.IProgrammeView
import kotlinx.android.synthetic.main.activity_programme.*
import kotlinx.android.synthetic.main.layout_block_categories.*
import kotlinx.android.synthetic.main.layout_programme_utils.*

/**
 *
 * @author MinorPeng
 * @date 2020/4/7 20:26
 */
class ProgrammeActivity : BaseActivity<ProgrammePresenter>(), IProgrammeView {

    private val mAdapter = BlocksRecyclerAdapter()
    private val mSelectedColor = ContextCompat.getColor(ContextHolder.getMainContext(), R.color.colorGrayBgSelectedE)
    private val mUnSelectedColor = ContextCompat.getColor(ContextHolder.getMainContext(), android.R.color.white)
    private var mLastSelectCategory = Block.Category.MOTION

    override fun getLayoutId(): Int {
        return R.layout.activity_programme
    }

    override fun createPresenter(): ProgrammePresenter {
        return ProgrammePresenter(this)
    }

    override fun initView() {
        ibtn_programme_back.setOnClickListener { finishActivity() }
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
        mPresenter.getBlocks(this)

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

    private var mToPos = 0
    private var mShouldScroll = false

    private fun smoothScrollToPosition(pos: Int) {
        val firstItem = recycler_view_programme.getChildLayoutPosition(recycler_view_programme.getChildAt(0))
        val lastItem = recycler_view_programme.getChildLayoutPosition(
            recycler_view_programme.getChildAt(recycler_view_programme.childCount - 1))
        LogUtil.d("fir:$firstItem las:$lastItem pos:$pos")
        if (pos < firstItem) {
            // 跳转位置在可视界面之前，向前滑动
            recycler_view_programme.smoothScrollToPosition(pos)
        } else if (pos <= lastItem) {
            // 跳转位置在可视界面内，置顶即可
            val movePos = pos - firstItem
            LogUtil.d("movePos:$movePos childCount:${recycler_view_programme.childCount}")
            if (movePos >= 0 && movePos < recycler_view_programme.childCount) {
                val top = recycler_view_programme.getChildAt(movePos).top
                LogUtil.d("top:$top")
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
        LogUtil.d("last:${mLastSelectCategory} now:$category")
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