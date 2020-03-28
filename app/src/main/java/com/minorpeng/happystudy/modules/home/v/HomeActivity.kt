package com.minorpeng.happystudy.modules.home.v

import com.minorpeng.base.base.BaseActivity
import com.minorpeng.happystudy.R
import com.minorpeng.happystudy.modules.home.p.HomePresenter

/**
 *
 * @author MinorPeng
 * @date 2020/3/19 9:37
 */
class HomeActivity : BaseActivity<HomePresenter>(), IHomeView {

    override fun getLayoutId(): Int {
        return R.layout.test
    }

    override fun createPresenter(): HomePresenter {
        return HomePresenter(this)
    }

    override fun initView() {
    }
}