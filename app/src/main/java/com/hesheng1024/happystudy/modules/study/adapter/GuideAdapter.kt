package com.hesheng1024.happystudy.modules.study.adapter

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.hesheng1024.base.base.adapter.BaseRecyclerAdapter
import com.hesheng1024.base.base.adapter.BaseViewHolder
import com.hesheng1024.happystudy.R

/**
 *
 * @author hesheng1024
 * @date 2020/4/23 14:23
 */
class GuideAdapter : BaseRecyclerAdapter<Int>() {

    override fun getItemLayoutId(): Int = R.layout.item_view_pager2_guide

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.getView<AppCompatImageView>(R.id.iv_item_view_pager2_guide)?.let {
            Glide.with(it.context).load(get(position)).override(it.width, it.height).into(it)
        }
    }
}