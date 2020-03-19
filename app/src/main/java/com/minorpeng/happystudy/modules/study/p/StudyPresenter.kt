package com.minorpeng.happystudy.modules.study.p

import com.minorpeng.base.base.BasePresenter
import com.minorpeng.happystudy.modules.study.m.IStudyModel
import com.minorpeng.happystudy.modules.study.m.impl.StudyModelImpl
import com.minorpeng.happystudy.modules.study.v.IStudyView

/**
 *
 * @author MinorPeng
 * @date 2020/3/19 9:47
 */
class StudyPresenter(view: IStudyView) : BasePresenter<IStudyView, IStudyModel>(view) {

    override fun createModel(): IStudyModel {
        return StudyModelImpl()
    }
}