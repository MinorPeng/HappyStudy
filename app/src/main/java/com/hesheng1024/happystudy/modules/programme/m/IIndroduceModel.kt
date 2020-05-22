package com.hesheng1024.happystudy.modules.programme.m

import android.content.Context
import com.hesheng1024.base.base.IBaseModel
import com.hesheng1024.happystudy.modules.Block

/**
 *
 * @author hesheng1024
 * @date 2020/5/22 17:02
 */
interface IIndroduceModel : IBaseModel {

    /**
     * 初始化积木
     */
    fun initBlocks(context: Context): List<Block>

}