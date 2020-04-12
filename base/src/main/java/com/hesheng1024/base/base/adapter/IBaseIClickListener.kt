package com.hesheng1024.base.base.adapter

import android.view.View

/**
 *
 * @author hesheng1024
 * @date 2020/2/8 11:16
 */
interface IBaseIClickListener {
    /**
     * click
     * @param view
     * @param position
     */
    fun onClick(view: View?, position: Int)

    /**
     * long click
     * @param view
     * @param position
     * @return
     */
    fun onLongClick(view: View?, position: Int): Boolean
}