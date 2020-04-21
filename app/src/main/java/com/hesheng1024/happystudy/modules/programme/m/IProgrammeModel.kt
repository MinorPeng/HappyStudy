package com.hesheng1024.happystudy.modules.programme.m

import android.content.Context
import com.hesheng1024.base.base.IBaseModel
import com.hesheng1024.happystudy.modules.Block

/**
 *
 * @author hesheng1024
 * @date 2020/4/7 20:23
 */
interface IProgrammeModel : IBaseModel {

    /**
     * 初始化积木
     */
    fun initBlocks(context: Context): List<Block>

    fun initMotionBlocks(context: Context): List<Block>

    fun initAppearanceBlocks(context: Context): List<Block>

    fun initControlBlocks(context: Context): List<Block>
}