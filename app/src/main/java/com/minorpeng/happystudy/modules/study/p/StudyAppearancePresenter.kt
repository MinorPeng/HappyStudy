package com.minorpeng.happystudy.modules.study.p

import com.minorpeng.base.base.BasePresenter
import com.minorpeng.happystudy.modules.study.m.IStudyAppearanceModel
import com.minorpeng.happystudy.modules.study.m.impl.StudyAppearanceModelImpl
import com.minorpeng.happystudy.modules.study.v.IStudyAppearanceView

/**
 *
 * @author MinorPeng
 * @date 2020/3/19 10:09
 */
class StudyAppearancePresenter(view: IStudyAppearanceView) : BasePresenter<IStudyAppearanceView, IStudyAppearanceModel>(view) {

    override fun createModel(): IStudyAppearanceModel {
        return StudyAppearanceModelImpl()
    }
}