package com.minorpeng.happystudy.modules.programme.adapter

import android.content.Context
import android.view.View
import com.minorpeng.base.base.adapter.BaseViewHolder

/**
 *
 * @author MinorPeng
 * @date 2020/4/11 9:17
 */
// 采用密封类写法，断定ViewHolder只有这几类 对于封装base不是很友好
sealed class CommonBlockViewHolder(itemView: View) : BaseViewHolder(itemView)
