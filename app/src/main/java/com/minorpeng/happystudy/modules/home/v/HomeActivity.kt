package com.minorpeng.happystudy.modules.home.v

import android.content.Intent
import androidx.core.content.ContextCompat
import com.minorpeng.base.base.BaseActivity
import com.minorpeng.happystudy.R
import com.minorpeng.happystudy.TestActivity
import com.minorpeng.happystudy.modules.home.p.HomePresenter
import com.minorpeng.happystudy.modules.programme.v.impl.ProgrammeFragment
import com.minorpeng.happystudy.modules.study.v.impl.StudyFragment
import kotlinx.android.synthetic.main.activity_home.*

/**
 *
 * @author MinorPeng
 * @date 2020/3/19 9:37
 */
class HomeActivity : BaseActivity<HomePresenter>(), IHomeView {

    private var mStudyFrag: StudyFragment? = null
    private var mProgrammeFrag: ProgrammeFragment? = null
    private var mStudySelected = true

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun createPresenter(): HomePresenter {
        return HomePresenter(this)
    }

    override fun initView() {
        if (mStudyFrag == null) {
            mStudyFrag = StudyFragment()
        }
        // 防止异常状态的恢复导致的重复fragment
        if (supportFragmentManager.fragments.isEmpty()) {
            supportFragmentManager.beginTransaction().add(R.id.frame_layout_home, mStudyFrag!!).commit()
        } else {
            supportFragmentManager.beginTransaction().replace(R.id.frame_layout_home, mStudyFrag!!).commit()
        }
        tv_home_study.setOnClickListener {
            if (!mStudySelected) {
                tv_home_programme.setBackgroundColor(ContextCompat.getColor(this, android.R.color.white))
                tv_home_study.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGrayBgSelectedE))
                if (mStudyFrag == null) {
                    mStudyFrag = StudyFragment()
                }
                supportFragmentManager.beginTransaction().replace(R.id.frame_layout_home, mStudyFrag!!).commit()
                mStudySelected = true
            }
        }
        tv_home_programme.setOnClickListener {
            if (mStudySelected) {
                tv_home_study.setBackgroundColor(ContextCompat.getColor(this, android.R.color.white))
                tv_home_programme.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGrayBgSelectedE))
                if (mProgrammeFrag == null) {
                    mProgrammeFrag = ProgrammeFragment()
                }
                supportFragmentManager.beginTransaction().replace(R.id.frame_layout_home, mProgrammeFrag!!).commit()
                mStudySelected = false
            }
        }
        floating_btn_home_test.setOnClickListener {
            startActivity(Intent(this, TestActivity::class.java))
        }
    }
}