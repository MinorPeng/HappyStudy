package com.minorpeng.happystudy.modules.study.p

import com.minorpeng.base.base.BasePresenter
import com.minorpeng.happystudy.modules.study.m.IStudyMotionModel
import com.minorpeng.happystudy.modules.study.m.impl.StudyMotionModelImpl
import com.minorpeng.happystudy.modules.study.v.IStudyMotionView

/**
 *
 * @author MinorPeng
 * @date 2020/3/19 10:08
 */
class StudyMotionPresenter(view: IStudyMotionView) : BasePresenter<IStudyMotionView, IStudyMotionModel>(view) {

    override fun createModel(): IStudyMotionModel {
        return StudyMotionModelImpl()
    }
}