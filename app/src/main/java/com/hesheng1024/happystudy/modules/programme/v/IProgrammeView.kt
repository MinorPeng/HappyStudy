package com.hesheng1024.happystudy.modules.programme.v

import com.hesheng1024.base.base.IBaseView
import com.hesheng1024.happystudy.modules.programme.m.Block

/**
 *
 * @author hesheng1024
 * @date 2020/4/7 20:24
 */
interface IProgrammeView : IBaseView {

    fun setBlocks(blocks: List<Block>)
}