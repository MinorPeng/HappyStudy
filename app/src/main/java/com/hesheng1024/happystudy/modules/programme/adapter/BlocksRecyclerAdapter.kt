package com.hesheng1024.happystudy.modules.programme.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import com.hesheng1024.base.base.adapter.BaseRecyclerAdapter
import com.hesheng1024.base.base.adapter.BaseViewHolder
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.modules.Block
import java.util.*

/**
 *
 * @author hesheng1024
 * @date 2020/4/8 15:46
 */
class BlocksRecyclerAdapter : BaseRecyclerAdapter<Block>() {

    companion object {
        private const val TITLE = 11
        private const val BLOCK = 12
    }

    override fun addList(datas: List<Block>) {
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
        super.addList(blocks)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return if (viewType == TITLE) {
            BlockTitleViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_blocks_category, parent, false)
            )
        } else {
            BlockViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_blocks_block, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val block = get(position)
        when (holder) {
            is BlockTitleViewHolder -> {
                (holder.itemView as? AppCompatTextView)?.setText(block.category.resId)
            }
            is BlockViewHolder -> {
                (block.view.parent as? ViewGroup)?.removeView(block.view)
                (holder.itemView as? ViewGroup)?.run {
                    removeAllViews()
                    addView(block.view)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return TITLE
        }
        if (position < getList().size && position >= 1
            && get(position).category != get(position - 1).category
        ) {
            return TITLE
        }
        return BLOCK
    }

    override fun getItemLayoutId(): Int = -1

    fun getPosByCategory(category: Block.Category): Int {
        for (index in getList().indices) {
            if (category == get(index).category) {
                return index
            }
        }
        return -1
    }

    class BlockTitleViewHolder(itemView: View) : BaseViewHolder(itemView) {
    }

    class BlockViewHolder(itemView: View) : BaseViewHolder(itemView) {

    }
}