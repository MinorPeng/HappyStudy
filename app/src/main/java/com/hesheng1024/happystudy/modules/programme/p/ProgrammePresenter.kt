package com.hesheng1024.happystudy.modules.programme.p

import android.content.Context
import com.hesheng1024.base.base.BasePresenter
import com.hesheng1024.base.utils.logE
import com.hesheng1024.happystudy.modules.programme.m.IProgrammeModel
import com.hesheng1024.happystudy.modules.programme.m.impl.ProgrammeModel
import com.hesheng1024.happystudy.modules.programme.v.IProgrammeView

/**
 *
 * @author hesheng1024
 * @date 2020/4/7 20:24
 */
class ProgrammePresenter(programmeView: IProgrammeView) : BasePresenter<IProgrammeView, IProgrammeModel>(programmeView) {

    override fun createModel(): IProgrammeModel {
        return ProgrammeModel()
    }

    fun getBlocks(context: Context) {
        val blocks = mModel.initBlocks(context)
        if (blocks.isNullOrEmpty()) {
            logE(msg = "init blocks error!")
            mView.toastMsg("something error!")
        } else {
            mView.setBlocks(blocks)
        }
    }

    fun getMotionBlocks(context: Context) {
        val blocks = mModel.initMotionBlocks(context)
        if (blocks.isNullOrEmpty()) {
            logE(msg = "init motion blocks error!")
            mView.toastMsg("something error!")
        } else {
            mView.setBlocks(blocks)
        }
    }

    fun getAppearanceBlocks(context: Context) {
        val blocks = mModel.initAppearanceBlocks(context)
        if (blocks.isNullOrEmpty()) {
            logE(msg = "init appearance blocks error!")
            mView.toastMsg("something error!")
        } else {
            mView.setBlocks(blocks)
        }
    }

    fun getControlBlocks(context: Context) {
        val blocks = mModel.initControlBlocks(context)
        if (blocks.isNullOrEmpty()) {
            logE(msg = "init control blocks error!")
            mView.toastMsg("something error!")
        } else {
            mView.setBlocks(blocks)
        }
    }
}