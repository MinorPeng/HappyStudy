package com.hesheng1024.happystudy.modules.study.m.impl

import android.content.Context
import com.hesheng1024.happystudy.custom.blocks.control.*
import com.hesheng1024.happystudy.custom.blocks.motion.*
import com.hesheng1024.happystudy.modules.Block
import com.hesheng1024.happystudy.modules.study.m.IStudyControlModel
import java.util.*

/**
 *
 * @author hesheng1024
 * @date 2020/3/19 10:07
 */
class StudyControlModelImpl : IStudyControlModel {
    override fun initBlocks(context: Context): List<Block> {
        val blocks = ArrayList<Block>()
        blocks.add(Block(Block.Category.MOTION, MoveBlockView(context)))
        blocks.add(Block(Block.Category.MOTION, DecreaseXBlockView(context)))
        blocks.add(Block(Block.Category.MOTION, DecreaseYBlockView(context)))
        blocks.add(Block(Block.Category.MOTION, IncreaseXBlockView(context)))
        blocks.add(Block(Block.Category.MOTION, IncreaseYBlockView(context)))
        blocks.add(Block(Block.Category.MOTION, LeftRotateBlockView(context)))
        blocks.add(Block(Block.Category.MOTION, RightRotateBlockView(context)))
        blocks.add(Block(Block.Category.MOTION, MoveToXYBlockView(context)))
        blocks.add(Block(Block.Category.MOTION, SetXBlockView(context)))
        blocks.add(Block(Block.Category.MOTION, SetYBlockView(context)))

        blocks.add(Block(Block.Category.CONTROL, CirculationNumBlockView(context)))
        blocks.add(Block(Block.Category.CONTROL, CirculationUtilBlockView(context)))
        blocks.add(Block(Block.Category.CONTROL, DeathCirculationBlockView(context)))
        blocks.add(Block(Block.Category.CONTROL, IfBlockView(context)))
        blocks.add(Block(Block.Category.CONTROL, IfElseBlockView(context)))
        blocks.add(Block(Block.Category.CONTROL, WaitBlockView(context)))

        return blocks
    }
}