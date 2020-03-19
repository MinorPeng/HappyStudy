package com.minorpeng.happystudy.modules.home.p

import com.minorpeng.base.base.BasePresenter
import com.minorpeng.happystudy.modules.home.m.HomeModelImpl
import com.minorpeng.happystudy.modules.home.m.IHomeModel
import com.minorpeng.happystudy.modules.home.v.IHomeView

/**
 *
 * @author MinorPeng
 * @date 2020/3/19 9:43
 */
class HomePresenter(view: IHomeView) : BasePresenter<IHomeView, IHomeModel>(view) {

    override fun createModel(): IHomeModel = HomeModelImpl()
}