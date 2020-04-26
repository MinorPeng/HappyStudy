package com.hesheng1024.happystudy.modules.study.v.impl

import android.content.Context
import android.content.Intent
import androidx.viewpager2.widget.ViewPager2
import com.hesheng1024.base.base.BaseActivity
import com.hesheng1024.base.utils.logD
import com.hesheng1024.happystudy.*
import com.hesheng1024.happystudy.modules.programme.v.impl.ProgrammeActivity
import com.hesheng1024.happystudy.modules.study.adapter.GuideAdapter
import com.hesheng1024.happystudy.modules.study.p.StudyGuidePresenter
import com.hesheng1024.happystudy.modules.study.v.IStudyGuideView
import kotlinx.android.synthetic.main.activity_study_guide.*

/**
 *
 * @author hesheng1024
 * @date 2020/4/21 16:06
 */
class StudyGuideActivity : BaseActivity<StudyGuidePresenter>(), IStudyGuideView {

    private val mAdapter = GuideAdapter()
    private var mCurPos = 0

    companion object {
        fun startActivity(context: Context, flag: String) {
            when(flag) {
                FLAG_PROGRAMME_MOTION, FLAG_PROGRAMME_APPEARANCE, FLAG_PROGRAMME_CONTROL -> {
                    context.startActivity(Intent(context, StudyGuideActivity::class.java).putExtra("flag", flag))
                }
                else -> return
            }
        }
    }

    override fun getLayoutId(): Int = R.layout.activity_study_guide

    override fun createPresenter(): StudyGuidePresenter {
        return StudyGuidePresenter(this)
    }

    override fun initView() {
        val flag = intent.getStringExtra("flag")
        if (flag.isNullOrEmpty()) {
            toastMsg("跳转页面失败!")
            finish()
            return
        }
        ibtn_study_guide_back.setOnClickListener { finish() }

        view_pager_study_guide.adapter = mAdapter
        view_pager_study_guide.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        view_pager_study_guide.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                logD(msg = "pos:$position offset:$positionOffset offP:$positionOffsetPixels")
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                mCurPos = position
            }
        })
        btn_study_guide_next.setOnClickListener {
            if (mCurPos == mAdapter.itemCount - 1) {
                ProgrammeActivity.startActivity(this, flag)
            } else {
                view_pager_study_guide.currentItem = ++mCurPos
            }
        }
        when (flag) {
            FLAG_PROGRAMME_MOTION -> {
                mPresenter.getMotionIvs()
            }
            FLAG_PROGRAMME_APPEARANCE -> {
                mPresenter.getAppearanceIvs()
            }
            FLAG_PROGRAMME_CONTROL -> {
                mPresenter.getControlIvs()
            }
        }
    }

    override fun setAdapter(resIds: List<Int>) {
        mAdapter.clear()
        mAdapter.addList(resIds)
    }
}