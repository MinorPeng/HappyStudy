package com.minorpeng.happystudy.modules.programme.v.impl

import com.minorpeng.base.base.BaseActivity
import com.minorpeng.happystudy.R
import com.minorpeng.happystudy.modules.programme.p.ProgrammePresenter
import com.minorpeng.happystudy.modules.programme.v.IProgrammeView

/**
 *
 * @author MinorPeng
 * @date 2020/4/7 20:26
 */
class ProgrammeActivity : BaseActivity<ProgrammePresenter>(), IProgrammeView {
    override fun getLayoutId(): Int {
        return R.layout.activity_programme
    }

    override fun createPresenter(): ProgrammePresenter {
        return ProgrammePresenter(this)
    }

    override fun initView() {

    }
}