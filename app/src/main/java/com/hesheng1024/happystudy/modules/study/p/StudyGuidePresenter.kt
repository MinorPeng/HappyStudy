package com.hesheng1024.happystudy.modules.study.p

import com.hesheng1024.base.base.BasePresenter
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

}