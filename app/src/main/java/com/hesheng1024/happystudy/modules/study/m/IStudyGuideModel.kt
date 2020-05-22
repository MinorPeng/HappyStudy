package com.hesheng1024.happystudy.modules.study.m

import com.hesheng1024.base.base.IBaseModel

/**
 *
 * @author hesheng1024
 * @date 2020/4/21 16:03
 */
interface IStudyGuideModel : IBaseModel {

    fun getMotionIvIds(): List<Int>

    fun getAppearanceIvIds(): List<Int>

    fun getControlIvIds(): List<Int>

    fun getDrawIvIds(): List<Int>
}