package com.hesheng1024.happystudy.modules.study.p

import android.content.Context
import com.hesheng1024.base.base.BasePresenter
import com.hesheng1024.base.utils.logE
import com.hesheng1024.happystudy.modules.study.m.IStudyAppearanceModel
import com.hesheng1024.happystudy.modules.study.m.impl.StudyAppearanceModelImpl
import com.hesheng1024.happystudy.modules.study.v.IStudyAppearanceView

/**
 *
 * @author hesheng1024
 * @date 2020/3/19 10:09
 */
class StudyAppearancePresenter(view: IStudyAppearanceView) : BasePresenter<IStudyAppearanceView, IStudyAppearanceModel>(view) {

    override fun createModel(): IStudyAppearanceModel {
        return StudyAppearanceModelImpl()
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
}