package com.hesheng1024.happystudy.modules.programme.v.impl

import android.content.Intent
import android.view.View
import com.hesheng1024.base.base.BaseFragment
import com.hesheng1024.happystudy.FLAG_PROGRAMME_NORMAL
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.modules.programme.p.ProgrammeFragPresenter
import com.hesheng1024.happystudy.modules.programme.v.IProgrammeFragView
import kotlinx.android.synthetic.main.fragment_programme.*

/**
 *
 * @author hesheng1024
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
        btn_programme_frag_programme.setOnClickListener {
            ProgrammeActivity.startActivity(it.context, FLAG_PROGRAMME_NORMAL)
        }

        btn_programme_frag_introduce.setOnClickListener {
            startActivity(Intent(it.context, IntroduceActivity::class.java))
        }

        btn_programme_frag_get_through.setOnClickListener {
            toastMsg("该功能暂未上线!")
        }

    }
}