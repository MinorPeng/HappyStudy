package com.hesheng1024.happystudy.modules.study.m.impl

import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.modules.study.m.IStudyGuideModel
import java.util.*

/**
 *
 * @author hesheng1024
 * @date 2020/4/21 16:05
 */
class StudyGuideModelImpl : IStudyGuideModel {
    override fun getMotionIvIds(): List<Int> {
        val resIds = ArrayList<Int>()
        resIds.add(R.drawable.motion_step1)
        resIds.add(R.drawable.motion_step2)
        resIds.add(R.drawable.motion_step3)
        resIds.add(R.drawable.motion_step4)
        resIds.add(R.drawable.motion_step5)
        return resIds
    }

    override fun getAppearanceIvIds(): List<Int> {
        val resIds = ArrayList<Int>()
        resIds.add(R.drawable.appearance_step1)
        resIds.add(R.drawable.appearance_step2)
        resIds.add(R.drawable.appearance_step3)
        resIds.add(R.drawable.appearance_step4)
        return resIds
    }

    override fun getControlIvIds(): List<Int> {
        val resIds = ArrayList<Int>()
        resIds.add(R.drawable.control_step1)
        resIds.add(R.drawable.control_step2)
        resIds.add(R.drawable.control_step3)
        resIds.add(R.drawable.control_step4)
        resIds.add(R.drawable.control_step5)
        resIds.add(R.drawable.control_step6)
        resIds.add(R.drawable.control_step7)
        return resIds
    }
}