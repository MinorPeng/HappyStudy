package com.hesheng1024.happystudy.modules.programme.m.impl

import android.content.Context
import com.hesheng1024.base.utils.logD
import com.hesheng1024.happystudy.custom.blocks.appearance.HideBlockView
import com.hesheng1024.happystudy.custom.blocks.appearance.NextBgBlockView
import com.hesheng1024.happystudy.custom.blocks.appearance.SayBlockView
import com.hesheng1024.happystudy.custom.blocks.appearance.ShowBlockView
import com.hesheng1024.happystudy.custom.blocks.calculate.*
import com.hesheng1024.happystudy.custom.blocks.control.*
import com.hesheng1024.happystudy.custom.blocks.draw.*
import com.hesheng1024.happystudy.custom.blocks.event.ClickRoleBlockView
import com.hesheng1024.happystudy.custom.blocks.event.ClickRunBlockView
import com.hesheng1024.happystudy.custom.blocks.motion.*
import com.hesheng1024.happystudy.custom.blocks.voice.DecreaseVoiceBlockView
import com.hesheng1024.happystudy.custom.blocks.voice.IncreaseVoiceBlockView
import com.hesheng1024.happystudy.custom.blocks.voice.PlayVoiceBlockView
import com.hesheng1024.happystudy.custom.blocks.voice.StopAllVoiceBlockView
import com.hesheng1024.happystudy.modules.Block
import com.hesheng1024.happystudy.modules.programme.m.IIndroduceModel
import java.util.*

/**
 *
 * @author hesheng1024
 * @date 2020/5/22 17:05
 */
class IntroduceModelImpl : IIndroduceModel {
    override fun initBlocks(context: Context): List<Block> {
        val blocks = ArrayList<Block>()
        blocks.add(Block(Block.Category.MOTION, MoveBlockView(context), "移动积木：可以控制角色向右移动整数步"))
        blocks.add(Block(Block.Category.MOTION, DecreaseXBlockView(context), "减少x值积木：减少角色的x坐标，float"))
        blocks.add(Block(Block.Category.MOTION, DecreaseYBlockView(context), "减少y值积木：减少角色的y坐标，float"))
        blocks.add(Block(Block.Category.MOTION, IncreaseXBlockView(context), "增加x值积木：增加角色的x坐标，float"))
        blocks.add(Block(Block.Category.MOTION, IncreaseYBlockView(context), "增加y值积木：增加角色的y坐标，float"))
        blocks.add(Block(Block.Category.MOTION, LeftRotateBlockView(context), "左旋积木：将角色逆时针旋转角度，int"))
        blocks.add(Block(Block.Category.MOTION, RightRotateBlockView(context), "右旋积木：将角色  顺时针旋转角度，int"))
        blocks.add(Block(Block.Category.MOTION, MoveToXYBlockView(context), "设置x、y积木：设置角色的x、y坐标，float、float"))
        blocks.add(Block(Block.Category.MOTION, SetXBlockView(context), "设置x积木：设置角色的x坐标，float   "))
        blocks.add(Block(Block.Category.MOTION, SetYBlockView(context), "设置y积木：设置角色的y坐标，float"))

        blocks.add(Block(Block.Category.APPEARANCE, SayBlockView(context), "聊天气泡积木：给角色添加聊天气泡，text"))
        blocks.add(Block(Block.Category.APPEARANCE, ShowBlockView(context), "显示积木：设置角色显示"))
        blocks.add(Block(Block.Category.APPEARANCE, HideBlockView(context), "隐藏积木：设置角色隐藏"))
        blocks.add(Block(Block.Category.APPEARANCE, NextBgBlockView(context), "下一个背景积木：更换角色的背景，结果区背景"))

        blocks.add(Block(Block.Category.VOICE, PlayVoiceBlockView(context), "声音积木：播放选择的声音"))
        blocks.add(Block(Block.Category.VOICE, DecreaseVoiceBlockView(context), "降低音量积木：减小媒体音量"))
        blocks.add(Block(Block.Category.VOICE, IncreaseVoiceBlockView(context), "增加音量积木：增加媒体音量"))
        blocks.add(Block(Block.Category.VOICE, StopAllVoiceBlockView(context), "停止声音积木：停止所有播放的声音"))

        blocks.add(Block(Block.Category.EVENT, ClickRoleBlockView(context), "角色被点击积木：当角色被点击"))
        blocks.add(Block(Block.Category.EVENT, ClickRunBlockView(context), "Run被点击积木：当运行按钮被点击"))

        blocks.add(Block(Block.Category.CONTROL, CirculationNumBlockView(context), "次数循环积木：一定次数的循环"))
        blocks.add(Block(Block.Category.CONTROL, CirculationUtilBlockView(context), "条件循环积木：有判断条件的循环"))
        blocks.add(Block(Block.Category.CONTROL, DeathCirculationBlockView(context), "死循环积木：死循环"))
        blocks.add(Block(Block.Category.CONTROL, IfBlockView(context), "如果积木：if条件判断"))
        blocks.add(Block(Block.Category.CONTROL, IfElseBlockView(context), "如果那么积木：if else条件判断"))
        blocks.add(Block(Block.Category.CONTROL, WaitBlockView(context), "等待积木：延时积木，等待一定时间后再向后执行，单位s"))

        blocks.add(Block(Block.Category.CALCULATE, AddBlockView(context), "加法积木：加法运算"))
        blocks.add(Block(Block.Category.CALCULATE, MinusBlockView(context), "减法积木：减法运算"))
        blocks.add(Block(Block.Category.CALCULATE, MultiplyBlockView(context), "乘法积木：乘法运算"))
        blocks.add(Block(Block.Category.CALCULATE, DivideBlockView(context), "除法积木：除法运算"))
        blocks.add(Block(Block.Category.CALCULATE, MoreThanBlockView(context), "大于积木：大于判断逻辑"))
        blocks.add(Block(Block.Category.CALCULATE, LessThanBlockView(context), "小于积木：小于判断逻辑"))
        blocks.add(Block(Block.Category.CALCULATE, EqualBlockView(context), "等于积木：等于判断逻辑"))
        blocks.add(Block(Block.Category.CALCULATE, AndBlockView(context), "且积木：与逻辑"))
        blocks.add(Block(Block.Category.CALCULATE, OrBlockView(context), "或积木：或逻辑"))
        blocks.add(Block(Block.Category.CALCULATE, NotBlockView(context), "非积木：非逻辑，条件不成立"))

        blocks.add(
            Block(
                Block.Category.CALCULATE, VarEqualBlockView(context),
                "变量赋值积木：声明变量并复制，整个程序变量名唯一"
            )
        )
        blocks.add(
            Block(
                Block.Category.CALCULATE, VarAddBlockView(context),
                "变量自增积木：声明的变量进行自增；\n\n如果没有声明，默认从0开始，整个程序变量名唯一"
            )
        )
        blocks.add(
            Block(
                Block.Category.CALCULATE, VarMinusBlockView(context),
                "变量自减积木：声明的变量进行自减；\n\n如果没有声明，默认从0开始，整个程序变量名唯一"
            )
        )
        blocks.add(
            Block(
                Block.Category.CALCULATE, VarMultiplyBlockView(context),
                "变量自增积木：声明的变量进行自乘；\n\n如果没有声明，默认从0开始，整个程序变量名唯一"
            )
        )
        blocks.add(
            Block(
                Block.Category.CALCULATE, VarDivideBlockView(context),
                "变量自增积木：声明的变量进行自除；\n\n如果没有声明，默认从0开始，整个程序变量名唯一"
            )
        )

        blocks.add(
            Block(
                Block.Category.DRAW, DrawCircleBlockView(context),
                "画圆积木：画一个圆；\n\n中心是圆中心的x、y坐标；\n半径是圆的半径r；\n线宽是圆的粗细程度；\n" +
                        "填充模式是圆线、实心圆和两者结合；\n颜色是圆的色彩"
            )
        )
        blocks.add(
            Block(
                Block.Category.DRAW, DrawPointBlockView(context),
                "画点积木：画一个点；\n\n中心是点的x、y坐标；\n半径是点的大小程度；\n颜色是点的色彩"
            )
        )
        blocks.add(
            Block(
                Block.Category.DRAW, DrawFillCircleBlockView(context),
                "画实心圆积木：画一个实心圆；\n\n中心是圆中心的x、y坐标；\n颜色是实心圆的填充色彩"
            )
        )
        blocks.add(
            Block(
                Block.Category.DRAW, DrawRingBlockView(context),
                "画圆环积木：画一个圆环；\n\n中心是圆中心的x、y坐标；\n半径是圆的半径r；\n线宽是圆环的粗细程度；\n颜色是圆环的色彩"
            )
        )
        blocks.add(
            Block(
                Block.Category.DRAW, DrawRectBlockView(context),
                "画矩形积木：画一个矩形；\n\n左上是左上点的x、y坐标；\n右下是右下点的x、y坐标；\n线宽是矩形的粗细程度；\n" +
                        "右转是矩形顺时针旋转的角度；\n填充模式是矩形线、实心矩形和两者结合；\n颜色是矩形的色彩"
            )
        )
        blocks.add(
            Block(
                Block.Category.DRAW, DrawSquareBlockView(context),
                "画正方形积木：画一个正放心；\n\n中心是正方形中心的x、y坐标；\n边长是正方形的边长；\n" +
                        "右转是正方形顺时针旋转的角度；\n填充模式是正方形线、实心正方形和两者结合；\n颜色是正方形的色彩"
            )
        )
        blocks.add(
            Block(
                Block.Category.DRAW, DrawTriangleBlockView(context),
                "画三角形积木：画一个三角形；\n\n顶点是三角形顶点x、y坐标；\n左下是左下角点x、y坐标；\n右下是右下角点x、y坐标；\n" +
                        "线宽是三角形的粗细程度；\n填充模式是三角形线、实心三角形和两者结合；\n颜色是三角形的色彩"
            )
        )
        blocks.add(
            Block(
                Block.Category.DRAW, DrawLineBlockView(context),
                "画线积木：画一条直线；\n\n起点是线的起点x、y坐标；\n终点是线的终点x、y坐标；\n线宽是线的粗细程度；\n" +
                        "右转是线顺时针旋转的角度；颜色是线的色彩"
            )
        )
        logD(msg = blocks[0].content)
        return blocks
    }
}