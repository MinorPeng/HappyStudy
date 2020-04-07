package com.minorpeng.happystudy.modules.programme.p

import com.minorpeng.base.base.BasePresenter
import com.minorpeng.happystudy.modules.programme.m.IProgrammeModel
import com.minorpeng.happystudy.modules.programme.m.impl.ProgrammeModel
import com.minorpeng.happystudy.modules.programme.v.IProgrammeView

/**
 *
 * @author MinorPeng
 * @date 2020/4/7 20:24
 */
class ProgrammePresenter(programmeView: IProgrammeView) : BasePresenter<IProgrammeView, IProgrammeModel>(programmeView) {

    override fun createModel(): IProgrammeModel {
        return ProgrammeModel()
    }
}