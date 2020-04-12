package com.hesheng1024.happystudy.modules.study.p

import com.hesheng1024.base.base.BasePresenter
import com.hesheng1024.happystudy.modules.study.m.IStudyMotionModel
import com.hesheng1024.happystudy.modules.study.m.impl.StudyMotionModelImpl
import com.hesheng1024.happystudy.modules.study.v.IStudyMotionView

/**
 *
 * @author hesheng1024
 * @date 2020/3/19 10:08
 */
class StudyMotionPresenter(view: IStudyMotionView) : BasePresenter<IStudyMotionView, IStudyMotionModel>(view) {

    override fun createModel(): IStudyMotionModel {
        return StudyMotionModelImpl()
    }
}