package com.hesheng1024.base.base.adapter

import android.view.View

/**
 *
 * @author hesheng1024
 * @date 2020/5/22 17:22
 */
abstract class RecyclerClickListener : IBaseIClickListener {

    override fun onClick(view: View?, position: Int) {
    }

    override fun onLongClick(view: View?, position: Int): Boolean {
        return false
    }
}