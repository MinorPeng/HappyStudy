package com.minorpeng.happystudy.modules.programme.v

import com.minorpeng.base.base.IBaseView
import com.minorpeng.happystudy.modules.programme.m.Block

/**
 *
 * @author MinorPeng
 * @date 2020/4/7 20:24
 */
interface IProgrammeView : IBaseView {

    fun setBlocks(blocks: List<Block>)
}