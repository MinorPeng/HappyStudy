package com.hesheng1024.happystudy.modules.study.m.impl

import android.content.Context
import com.hesheng1024.happystudy.custom.blocks.appearance.HideBlockView
import com.hesheng1024.happystudy.custom.blocks.appearance.NextBgBlockView
import com.hesheng1024.happystudy.custom.blocks.appearance.SayBlockView
import com.hesheng1024.happystudy.custom.blocks.appearance.ShowBlockView
import com.hesheng1024.happystudy.modules.Block
import com.hesheng1024.happystudy.modules.study.m.IStudyAppearanceModel
import java.util.*

/**
 *
 * @author hesheng1024
 * @date 2020/3/19 10:07
 */
class StudyAppearanceModelImpl : IStudyAppearanceModel {

    override fun initBlocks(context: Context): List<Block> {
        val blocks = ArrayList<Block>()
        blocks.add(Block(Block.Category.APPEARANCE, SayBlockView(context)))
        blocks.add(Block(Block.Category.APPEARANCE, ShowBlockView(context)))
        blocks.add(Block(Block.Category.APPEARANCE, HideBlockView(context)))
        blocks.add(Block(Block.Category.APPEARANCE, NextBgBlockView(context)))
        return blocks
    }
}