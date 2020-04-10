package com.minorpeng.happystudy.modules.programme.m

import android.content.Context
import com.minorpeng.base.base.IBaseModel

/**
 *
 * @author MinorPeng
 * @date 2020/4/7 20:23
 */
interface IProgrammeModel : IBaseModel {

    /**
     * 初始化积木
     */
    fun initBlocks(context: Context): List<Block>
}