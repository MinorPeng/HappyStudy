package com.minorpeng.happystudy.modules.programme

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import com.minorpeng.base.base.adapter.BaseRecyclerAdapter
import com.minorpeng.base.base.adapter.BaseViewHolder
import com.minorpeng.happystudy.R
import com.minorpeng.happystudy.modules.programme.m.Block
import java.util.*

/**
 *
 * @author MinorPeng
 * @date 2020/4/8 15:46
 */
class BlocksRecyclerAdapter : BaseRecyclerAdapter<Block>() {

    companion object {
        private const val TITLE = 11
        private const val BLOCK = 12
    }

    override fun addDatas(datas: List<Block>) {
        if (datas.isEmpty()) {
            return
        }
        val blocks = ArrayList<Block>()
        var lastBlock = datas[0]
        blocks.add(Block(lastBlock.category, lastBlock.view))
        for (block in datas) {
            if (lastBlock.category != block.category) {
                blocks.add(Block(block.category, block.view))
                lastBlock = block
            }
            blocks.add(block)
        }
        super.addDatas(blocks)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return if (viewType == TITLE) {
            TitleHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_blocks_category, parent, false))
        } else {
            BlockHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_blocks_block, parent, false))
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return TITLE
        }
        if (position < getDatas().size && position >= 1
            && getData(position).category != getData(position - 1).category) {
            return TITLE
        }
        return BLOCK
    }

    override fun getItemLayoutId(): Int {
        return 0
    }

    override fun bindHolder(holder: BaseViewHolder?, position: Int) {
        val block = getData(position)
        if (holder is TitleHolder) {
            (holder.itemView as AppCompatTextView).setText(block.category.resId)
        } else if (holder is BlockHolder) {
            (holder.itemView as ViewGroup).removeAllViews()
            if (block.view.parent != null) {
                (block.view.parent as ViewGroup).removeView(block.view)
            }
            (holder.itemView as ViewGroup).addView(block.view)
        }
    }

    fun getPosByCategory(category: Block.Category): Int {
        for (index in getDatas().indices) {
            if (category == getData(index).category) {
                return index
            }
        }
        return -1
    }

    class TitleHolder(itemView: View) : BaseViewHolder(itemView) {
    }

    class BlockHolder(itemView: View) : BaseViewHolder(itemView) {

    }
}