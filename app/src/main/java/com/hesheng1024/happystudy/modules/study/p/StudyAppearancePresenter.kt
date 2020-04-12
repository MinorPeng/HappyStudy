package com.hesheng1024.happystudy.modules.study.p

import com.hesheng1024.base.base.BasePresenter
import com.hesheng1024.happystudy.modules.study.m.IStudyAppearanceModel
import com.hesheng1024.happystudy.modules.study.m.impl.StudyAppearanceModelImpl
import com.hesheng1024.happystudy.modules.study.v.IStudyAppearanceView

/**
 *
 * @author hesheng1024
 * @date 2020/3/19 10:09
 */
class StudyAppearancePresenter(view: IStudyAppearanceView) : BasePresenter<IStudyAppearanceView, IStudyAppearanceModel>(view) {

    override fun createModel(): IStudyAppearanceModel {
        return StudyAppearanceModelImpl()
    }
}