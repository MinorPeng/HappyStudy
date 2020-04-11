package com.minorpeng.happystudy.modules.programme.m.impl

import android.content.Context
import com.minorpeng.base.utils.LogUtil
import com.minorpeng.happystudy.custom.blocks.appearance.HideBlockView
import com.minorpeng.happystudy.custom.blocks.appearance.NextBgBlockView
import com.minorpeng.happystudy.custom.blocks.appearance.SayBlockView
import com.minorpeng.happystudy.custom.blocks.appearance.ShowBlockView
import com.minorpeng.happystudy.custom.blocks.calculate.*
import com.minorpeng.happystudy.custom.blocks.control.*
import com.minorpeng.happystudy.custom.blocks.event.ClickRoleBlockView
import com.minorpeng.happystudy.custom.blocks.event.ClickRunBlockView
import com.minorpeng.happystudy.custom.blocks.motion.*
import com.minorpeng.happystudy.custom.blocks.voice.DecreaseVoiceBlockView
import com.minorpeng.happystudy.custom.blocks.voice.IncreaseVoiceBlockView
import com.minorpeng.happystudy.custom.blocks.voice.PlayVoiceBlockView
import com.minorpeng.happystudy.custom.blocks.voice.StopAllVoiceBlockView
import com.minorpeng.happystudy.modules.programme.m.Block
import com.minorpeng.happystudy.modules.programme.m.IProgrammeModel
import java.util.ArrayList

/**
 *
 * @author MinorPeng
 * @date 2020/4/7 20:24
 */
class ProgrammeModel : IProgrammeModel {

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

        blocks.add(Block(Block.Category.APPEARANCE, SayBlockView(context)))
        blocks.add(Block(Block.Category.APPEARANCE, ShowBlockView(context)))
        blocks.add(Block(Block.Category.APPEARANCE, HideBlockView(context)))
        blocks.add(Block(Block.Category.APPEARANCE, NextBgBlockView(context)))

        blocks.add(Block(Block.Category.VOICE, PlayVoiceBlockView(context)))
        blocks.add(Block(Block.Category.VOICE, DecreaseVoiceBlockView(context)))
        blocks.add(Block(Block.Category.VOICE, IncreaseVoiceBlockView(context)))
        blocks.add(Block(Block.Category.VOICE, StopAllVoiceBlockView(context)))

        blocks.add(Block(Block.Category.EVENT, ClickRoleBlockView(context)))
        blocks.add(Block(Block.Category.EVENT, ClickRunBlockView(context)))

        blocks.add(Block(Block.Category.CONTROL, CirculationNumBlockView(context)))
        blocks.add(Block(Block.Category.CONTROL, CirculationUtilBlockView(context)))
        blocks.add(Block(Block.Category.CONTROL, DeathCirculationBlockView(context)))
        blocks.add(Block(Block.Category.CONTROL, IfBlockView(context)))
        blocks.add(Block(Block.Category.CONTROL, IfElseBlockView(context)))
        blocks.add(Block(Block.Category.CONTROL, WaitBlockView(context)))

        blocks.add(Block(Block.Category.CALCULATE, AddBlockView(context)))
        blocks.add(Block(Block.Category.CALCULATE, MinusBlockView(context)))
        blocks.add(Block(Block.Category.CALCULATE, MultiplyBlockView(context)))
        blocks.add(Block(Block.Category.CALCULATE, DivideBlockView(context)))
        blocks.add(Block(Block.Category.CALCULATE, MoreThanBlockView(context)))
        blocks.add(Block(Block.Category.CALCULATE, LessThanBlockView(context)))
        blocks.add(Block(Block.Category.CALCULATE, EqualBlockView(context)))
        blocks.add(Block(Block.Category.CALCULATE, AndBlockView(context)))
        blocks.add(Block(Block.Category.CALCULATE, OrBlockView(context)))
        blocks.add(Block(Block.Category.CALCULATE, NotBlockView(context)))
        LogUtil.d(msg = "blocks:${blocks.size}")
        return blocks
    }
}