package com.hesheng1024.happystudy.modules.study.m

import android.content.Context
import com.hesheng1024.base.base.IBaseModel
import com.hesheng1024.happystudy.modules.Block

/**
 *
 * @author hesheng1024
 * @date 2020/3/19 10:06
 */
interface IStudyControlModel : IBaseModel {

    /**
     * 初始化积木
     */
    fun initBlocks(context: Context): List<Block>
}