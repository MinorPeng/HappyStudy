package com.hesheng1024.happystudy.modules.study.v.impl

import android.content.Context
import android.content.Intent
import com.hesheng1024.base.base.BaseActivity
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.modules.study.p.StudyGuidePresenter
import com.hesheng1024.happystudy.modules.study.v.IStudyGuideView

/**
 *
 * @author hesheng1024
 * @date 2020/4/21 16:06
 */
class StudyGuideActivity : BaseActivity<StudyGuidePresenter>(), IStudyGuideView {

    companion object {
        fun startActivity(context: Context, flag: String) {
            context.startActivity(Intent(context, StudyGuideActivity::class.java).putExtra("flag", flag))
        }
    }

    override fun getLayoutId(): Int = R.layout.activity_study_guide

    override fun createPresenter(): StudyGuidePresenter {
        return StudyGuidePresenter(this)
    }

    override fun initView() {
        val flag = intent.getStringExtra("flag")
        if (flag.isNullOrEmpty()) {
            finish()
            return
        }

    }

}