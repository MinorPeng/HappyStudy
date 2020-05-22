package com.hesheng1024.happystudy.modules.programme.p

import android.content.Context
import com.hesheng1024.base.base.BasePresenter
import com.hesheng1024.base.utils.logE
import com.hesheng1024.happystudy.modules.programme.m.IIndroduceModel
import com.hesheng1024.happystudy.modules.programme.m.impl.IntroduceModelImpl
import com.hesheng1024.happystudy.modules.programme.v.IIndroduceView

/**
 *
 * @author hesheng1024
 * @date 2020/5/22 17:04
 */
class IntroducePresenter(introduceView: IIndroduceView) :
    BasePresenter<IIndroduceView, IIndroduceModel>(introduceView) {

    override fun createModel(): IIndroduceModel {
        return IntroduceModelImpl()
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