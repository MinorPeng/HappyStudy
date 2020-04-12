package com.hesheng1024.happystudy.modules.study.p

import com.hesheng1024.base.base.BasePresenter
import com.hesheng1024.happystudy.modules.study.m.IStudyModel
import com.hesheng1024.happystudy.modules.study.m.impl.StudyModelImpl
import com.hesheng1024.happystudy.modules.study.v.IStudyView

/**
 *
 * @author hesheng1024
 * @date 2020/3/19 9:47
 */
class StudyPresenter(view: IStudyView) : BasePresenter<IStudyView, IStudyModel>(view) {

    override fun createModel(): IStudyModel {
        return StudyModelImpl()
    }
}