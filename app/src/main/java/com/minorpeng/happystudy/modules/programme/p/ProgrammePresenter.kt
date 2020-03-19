package com.minorpeng.happystudy.modules.programme.p

import com.minorpeng.base.base.BasePresenter
import com.minorpeng.happystudy.modules.programme.m.IProgrammeModel
import com.minorpeng.happystudy.modules.programme.m.impl.ProgrammeModelImpl
import com.minorpeng.happystudy.modules.programme.v.IProgrammeView

/**
 *
 * @author MinorPeng
 * @date 2020/3/19 9:46
 */
class ProgrammePresenter(view: IProgrammeView) : BasePresenter<IProgrammeView, IProgrammeModel>(view) {

    override fun createModel(): IProgrammeModel {
        return ProgrammeModelImpl()
    }
}