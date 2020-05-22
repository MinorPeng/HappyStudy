package com.hesheng1024.happystudy.modules.study.p

import com.hesheng1024.base.base.BasePresenter
import com.hesheng1024.base.utils.logI
import com.hesheng1024.happystudy.FLAG_PROGRAMME_APPEARANCE
import com.hesheng1024.happystudy.FLAG_PROGRAMME_CONTROL
import com.hesheng1024.happystudy.FLAG_PROGRAMME_DRAW
import com.hesheng1024.happystudy.FLAG_PROGRAMME_MOTION
import com.hesheng1024.happystudy.modules.study.m.IStudyGuideModel
import com.hesheng1024.happystudy.modules.study.m.impl.StudyGuideModelImpl
import com.hesheng1024.happystudy.modules.study.v.IStudyGuideView

/**
 *
 * @author hesheng1024
 * @date 2020/4/21 16:04
 */
class StudyGuidePresenter(view: IStudyGuideView) : BasePresenter<IStudyGuideView, IStudyGuideModel>(view) {
    override fun createModel(): IStudyGuideModel = StudyGuideModelImpl()

    fun getIvIds(flag: String) {
        val ivs = when (flag) {
            FLAG_PROGRAMME_MOTION -> {
                mModel.getMotionIvIds()
            }
            FLAG_PROGRAMME_APPEARANCE -> {
                mModel.getAppearanceIvIds()
            }
            FLAG_PROGRAMME_CONTROL -> {
                mModel.getControlIvIds()
            }
            FLAG_PROGRAMME_DRAW -> {
                mModel.getDrawIvIds()
            }
            else -> null
        }
        if (ivs.isNullOrEmpty()) {
            logI(msg = "iv resIds is null or empty:$ivs")
            mView.toastMsg("something error!")
        } else {
            mView.setAdapter(ivs)
        }
    }
}