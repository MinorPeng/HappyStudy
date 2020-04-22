package com.hesheng1024.happystudy.modules.study.p

import com.hesheng1024.base.base.BasePresenter
import com.hesheng1024.happystudy.modules.study.m.IStudyFragModel
import com.hesheng1024.happystudy.modules.study.m.impl.StudyFragModelImpl
import com.hesheng1024.happystudy.modules.study.v.IStudyFragView

/**
 *
 * @author hesheng1024
 * @date 2020/3/19 9:47
 */
class StudyPresenter(view: IStudyFragView) : BasePresenter<IStudyFragView, IStudyFragModel>(view) {

    override fun createModel(): IStudyFragModel {
        return StudyFragModelImpl()
    }
}