package com.hesheng1024.happystudy.modules.programme.v.impl

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hesheng1024.base.base.BaseActivity
import com.hesheng1024.base.base.adapter.RecyclerClickListener
import com.hesheng1024.base.utils.ContextHolder
import com.hesheng1024.base.utils.logD
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.modules.Block
import com.hesheng1024.happystudy.modules.programme.adapter.BlocksRecyclerAdapter
import com.hesheng1024.happystudy.modules.programme.p.IntroducePresenter
import com.hesheng1024.happystudy.modules.programme.v.IIndroduceView
import kotlinx.android.synthetic.main.activity_introduce.*
import kotlinx.android.synthetic.main.layout_block_categories.*

/**
 *
 * @author hesheng1024
 * @date 2020/5/22 17:07
 */
class IntroduceActivity : BaseActivity<IntroducePresenter>(), IIndroduceView {

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
        return R.layout.activity_introduce
    }

    override fun createPresenter(): IntroducePresenter {
        return IntroducePresenter(this)
    }

    override fun initView() {
        ibtn_introduce_back.setOnClickListener { finishActivity() }
        initBlockCategory()
        initRecyclerView()
        mPresenter.getBlocks(this)
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
        tv_programme_draw.setOnClickListener {
            val pos = mAdapter.getPosByCategory(Block.Category.DRAW)
            if (pos != -1) {
                changeSelectedCategory(Block.Category.DRAW)
                smoothScrollToPosition(pos)
            }
        }
    }

    private fun changeSelectedCategory(category: Block.Category) {
        if (category == mLastSelectCategory) {
            return
        }
        logD(msg = "last:${mLastSelectCategory} now:$category")
        when (mLastSelectCategory) {
            Block.Category.MOTION -> tv_programme_motion.setBackgroundColor(mUnSelectedColor)
            Block.Category.APPEARANCE -> tv_programme_appearance.setBackgroundColor(mUnSelectedColor)
            Block.Category.VOICE -> tv_programme_voice.setBackgroundColor(mUnSelectedColor)
            Block.Category.EVENT -> tv_programme_event.setBackgroundColor(mUnSelectedColor)
            Block.Category.CONTROL -> tv_programme_control.setBackgroundColor(mUnSelectedColor)
            Block.Category.CALCULATE -> tv_programme_calculate.setBackgroundColor(mUnSelectedColor)
            Block.Category.DRAW -> tv_programme_draw.setBackgroundColor(mUnSelectedColor)
        }
        when (category) {
            Block.Category.MOTION -> tv_programme_motion.setBackgroundColor(mSelectedColor)
            Block.Category.APPEARANCE -> tv_programme_appearance.setBackgroundColor(mSelectedColor)
            Block.Category.VOICE -> tv_programme_voice.setBackgroundColor(mSelectedColor)
            Block.Category.EVENT -> tv_programme_event.setBackgroundColor(mSelectedColor)
            Block.Category.CONTROL -> tv_programme_control.setBackgroundColor(mSelectedColor)
            Block.Category.CALCULATE -> tv_programme_calculate.setBackgroundColor(mSelectedColor)
            Block.Category.DRAW -> tv_programme_draw.setBackgroundColor(mSelectedColor)
        }
        mLastSelectCategory = category
    }

    private fun smoothScrollToPosition(pos: Int) {
        val firstItem = recycler_view_introduce.getChildLayoutPosition(recycler_view_introduce.getChildAt(0))
        val lastItem = recycler_view_introduce.getChildLayoutPosition(
            recycler_view_introduce.getChildAt(recycler_view_introduce.childCount - 1)
        )
        logD(msg = "fir:$firstItem las:$lastItem pos:$pos")
        if (pos < firstItem) {
            // 跳转位置在可视界面之前，向前滑动
            recycler_view_introduce.smoothScrollToPosition(pos)
        } else if (pos <= lastItem) {
            // 跳转位置在可视界面内，置顶即可
            val movePos = pos - firstItem
            logD(msg = "movePos:$movePos childCount:${recycler_view_introduce.childCount}")
            if (movePos >= 0 && movePos < recycler_view_introduce.childCount) {
                val top = recycler_view_introduce.getChildAt(movePos).top
                logD(msg = "top:$top")
                recycler_view_introduce.smoothScrollBy(0, top)
            }
        } else {
            // 跳转位置在可视界面之后，需要先滑动到可视界面内，再置顶
            recycler_view_introduce.smoothScrollToPosition(pos)
            mToPos = pos
            mShouldScroll = true
        }
    }

    private fun initRecyclerView() {
        recycler_view_introduce.adapter = mAdapter
        val layoutManager = LinearLayoutManager(this)
        recycler_view_introduce.layoutManager = layoutManager
        recycler_view_introduce.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (mShouldScroll && RecyclerView.SCROLL_STATE_IDLE == newState) {
                    mShouldScroll = false
                    smoothScrollToPosition(mToPos)
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val category = mAdapter.get(layoutManager.findFirstCompletelyVisibleItemPosition()).category
                changeSelectedCategory(category)
            }
        })
        mAdapter.setClickListener(object : RecyclerClickListener() {
            override fun onClick(view: View?, position: Int) {
                val block = mAdapter.get(position)
                tv_introduce_content.text = block.content
                logD(msg = "click: ${block.content} pos:$position")

            }
        })
    }

    override fun setBlocks(blocks: List<Block>) {
        mAdapter.setList(blocks)
        val block = blocks[0]
        logD(msg = "set: ${block.content}")
        tv_introduce_content.text = block.content
    }
}