package com.hesheng1024.happystudy.modules.study.v.impl

import android.view.View
import com.hesheng1024.base.base.BaseFragment
import com.hesheng1024.happystudy.FLAG_PROGRAMME_APPEARANCE
import com.hesheng1024.happystudy.FLAG_PROGRAMME_CONTROL
import com.hesheng1024.happystudy.FLAG_PROGRAMME_MOTION
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.modules.study.p.StudyPresenter
import com.hesheng1024.happystudy.modules.study.v.IStudyFragView
import kotlinx.android.synthetic.main.framgment_study.*

/**
 *
 * @author hesheng1024
 * @date 2020/3/19 9:39
 */
class StudyFragment : BaseFragment<StudyPresenter>(), IStudyFragView {
    override fun getLayoutId(): Int {
        return R.layout.framgment_study
    }

    override fun createPresenter(): StudyPresenter {
        return StudyPresenter(this)
    }

    override fun initView(view: View) {
        btn_motion.setOnClickListener {
            context?.let {
                StudyGuideActivity.startActivity(it, FLAG_PROGRAMME_MOTION)
            }
        }
        btn_appearance.setOnClickListener {
            context?.let {
                StudyGuideActivity.startActivity(it, FLAG_PROGRAMME_APPEARANCE)
            }
        }
        btn_control.setOnClickListener {
            context?.let {
                StudyGuideActivity.startActivity(it, FLAG_PROGRAMME_CONTROL)
            }
        }
    }
}