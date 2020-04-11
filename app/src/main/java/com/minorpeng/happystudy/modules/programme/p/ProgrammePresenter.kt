package com.minorpeng.happystudy.modules.programme.p

import android.content.Context
import com.minorpeng.base.base.BasePresenter
import com.minorpeng.base.utils.LogUtil
import com.minorpeng.happystudy.modules.programme.m.IProgrammeModel
import com.minorpeng.happystudy.modules.programme.m.impl.ProgrammeModel
import com.minorpeng.happystudy.modules.programme.v.IProgrammeView

/**
 *
 * @author MinorPeng
 * @date 2020/4/7 20:24
 */
class ProgrammePresenter(programmeView: IProgrammeView) : BasePresenter<IProgrammeView, IProgrammeModel>(programmeView) {

    override fun createModel(): IProgrammeModel {
        return ProgrammeModel()
    }

    fun getBlocks(context: Context) {
        val blocks = mModel.initBlocks(context)
        if (blocks.isNullOrEmpty()) {
            LogUtil.e(msg = "init blocks error!")
            mView.toastMsg("something error!")
        } else {
            mView.setBlocks(blocks)
        }
    }
}