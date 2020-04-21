package com.hesheng1024.happystudy.modules.study.p

import android.content.Context
import com.hesheng1024.base.base.BasePresenter
import com.hesheng1024.base.utils.logE
import com.hesheng1024.happystudy.modules.study.m.IStudyControlModel
import com.hesheng1024.happystudy.modules.study.m.impl.StudyControlModelImpl
import com.hesheng1024.happystudy.modules.study.v.IStudyControlView

/**
 *
 * @author hesheng1024
 * @date 2020/3/19 10:09
 */
class StudyControlPresenter(view: IStudyControlView) : BasePresenter<IStudyControlView, IStudyControlModel>(view) {

    override fun createModel(): IStudyControlModel {
        return StudyControlModelImpl()
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