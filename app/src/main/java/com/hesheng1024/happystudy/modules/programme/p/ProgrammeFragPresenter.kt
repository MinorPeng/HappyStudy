package com.hesheng1024.happystudy.modules.programme.p

import com.hesheng1024.base.base.BasePresenter
import com.hesheng1024.happystudy.modules.programme.m.IProgrammeFragModel
import com.hesheng1024.happystudy.modules.programme.m.impl.ProgrammeFragModelImpl
import com.hesheng1024.happystudy.modules.programme.v.IProgrammeFragView

/**
 *
 * @author hesheng1024
 * @date 2020/3/19 9:46
 */
class ProgrammeFragPresenter(view: IProgrammeFragView) : BasePresenter<IProgrammeFragView, IProgrammeFragModel>(view) {

    override fun createModel(): IProgrammeFragModel {
        return ProgrammeFragModelImpl()
    }
}