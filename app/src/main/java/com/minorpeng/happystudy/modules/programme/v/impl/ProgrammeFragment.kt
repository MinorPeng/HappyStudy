package com.minorpeng.happystudy.modules.programme.v.impl

import android.content.Intent
import android.view.View
import com.minorpeng.base.base.BaseFragment
import com.minorpeng.happystudy.R
import com.minorpeng.happystudy.modules.programme.p.ProgrammeFragPresenter
import com.minorpeng.happystudy.modules.programme.v.IProgrammeFragView
import kotlinx.android.synthetic.main.fragment_programme.*

/**
 *
 * @author MinorPeng
 * @date 2020/3/19 9:39
 */
class ProgrammeFragment : BaseFragment<ProgrammeFragPresenter>(), IProgrammeFragView {
    override fun getLayoutId(): Int {
        return R.layout.fragment_programme
    }

    override fun createPresenter(): ProgrammeFragPresenter {
        return ProgrammeFragPresenter(this)
    }

    override fun initView(view: View) {
        btn_programme.setOnClickListener {
            startActivity(Intent(context, ProgrammeActivity::class.java))
        }
        btn_get_through.setOnClickListener {
            toastMsg("该功能暂未上线!")
        }
    }
}