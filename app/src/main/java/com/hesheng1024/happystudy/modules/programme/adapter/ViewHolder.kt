package com.hesheng1024.happystudy.modules.programme.adapter

import android.view.View
import com.hesheng1024.base.base.adapter.BaseViewHolder

/**
 *
 * @author hesheng1024
 * @date 2020/4/11 9:17
 */
// 采用密封类写法，断定ViewHolder只有这几类 对于封装base不是很友好
sealed class CommonBlockViewHolder(itemView: View) : BaseViewHolder(itemView)
