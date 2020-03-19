package com.minorpeng.happystudy.modules.study.p

import com.minorpeng.base.base.BasePresenter
import com.minorpeng.happystudy.modules.study.m.IStudyControlModel
import com.minorpeng.happystudy.modules.study.m.impl.StudyControlModelImpl
import com.minorpeng.happystudy.modules.study.v.IStudyControlView

/**
 *
 * @author MinorPeng
 * @date 2020/3/19 10:09
 */
class StudyControlPresenter(view: IStudyControlView) : BasePresenter<IStudyControlView, IStudyControlModel>(view) {

    override fun createModel(): IStudyControlModel {
        return StudyControlModelImpl()
    }
}