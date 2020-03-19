package com.minorpeng.base.base.adapter

import android.view.View

/**
 *
 * @author MinorPeng
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