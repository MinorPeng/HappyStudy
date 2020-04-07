package com.minorpeng.happystudy.modules.programme.p

import com.minorpeng.base.base.BasePresenter
import com.minorpeng.happystudy.modules.programme.m.IProgrammeFragModel
import com.minorpeng.happystudy.modules.programme.m.impl.ProgrammeFragModelImpl
import com.minorpeng.happystudy.modules.programme.v.IProgrammeFragView

/**
 *
 * @author MinorPeng
 * @date 2020/3/19 9:46
 */
class ProgrammeFragPresenter(view: IProgrammeFragView) : BasePresenter<IProgrammeFragView, IProgrammeFragModel>(view) {

    override fun createModel(): IProgrammeFragModel {
        return ProgrammeFragModelImpl()
    }
}