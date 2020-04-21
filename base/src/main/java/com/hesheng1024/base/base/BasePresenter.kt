package com.hesheng1024.base.base

/**
 *
 * @author hesheng1024
 * @date 2020/2/7 17:01
 */
abstract class BasePresenter<out V : IBaseView, out M : IBaseModel>(protected val mView: V) {
    protected val mModel = this.createModel()

    abstract fun createModel(): M
}