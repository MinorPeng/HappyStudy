package com.hesheng1024.happystudy.modules.programme.v

import com.hesheng1024.base.base.IBaseView
import com.hesheng1024.happystudy.modules.Block

/**
 *
 * @author hesheng1024
 * @date 2020/5/22 17:02
 */
interface IIndroduceView : IBaseView {

    fun setBlocks(blocks: List<Block>)
}