package com.hesheng1024.happystudy.modules.home.p

import com.hesheng1024.base.base.BasePresenter
import com.hesheng1024.happystudy.modules.home.m.HomeModelImpl
import com.hesheng1024.happystudy.modules.home.m.IHomeModel
import com.hesheng1024.happystudy.modules.home.v.IHomeView

/**
 *
 * @author hesheng1024
 * @date 2020/3/19 9:43
 */
class HomePresenter(view: IHomeView) : BasePresenter<IHomeView, IHomeModel>(view) {

    override fun createModel(): IHomeModel = HomeModelImpl()
}