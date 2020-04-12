package com.hesheng1024.base.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import com.hesheng1024.base.R

/**
 *
 * @author hesheng1024
 * @date 2020/2/8 11:00
 */
abstract class BaseActivityWithToolbar<P : BasePresenter<out IBaseView, out IBaseModel>> :
    BaseActivity<P>() {

    protected var mToolbar: Toolbar? = null
    private var mTitle: AppCompatTextView? = null

    override fun inflateContentView(): View {
        mContentView = LayoutInflater.from(this).inflate(R.layout.activity_base, null)
        (mContentView as ViewGroup).addView(initToolbar(mContentView as ViewGroup), 0)
        LayoutInflater.from(this).inflate(getLayoutId(), mContentView as ViewGroup?)
        return mContentView
    }

    private fun initToolbar(contentView: ViewGroup): View? {
        mToolbar = layoutInflater.inflate(
            R.layout.include_toolbar,
            contentView,
            false
        ) as Toolbar?
        mToolbar?.let { toolbar ->
            if (mTranslucent) {
                toolbar.setPadding(0, getStatusBarHeight(), 0, 0)
            }
            toolbar.setBackgroundResource(getStatusBarBackground())
            toolbar.addView(layoutInflater.inflate(getToolbarContent(), mToolbar, false))
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            initToolbarEvent(toolbar)
        }
        return mToolbar
    }

    private fun initToolbarEvent(toolbar: Toolbar) {
        val ivLeft = toolbar.findViewById<AppCompatImageView>(R.id.iv_toolbar_left)
        mTitle = toolbar.findViewById(R.id.tv_toolbar_title)
        ivLeft?.setOnClickListener { finish() }
    }

    protected fun setTheTitle(title: String) {
        mTitle?.text = title
    }

    protected fun setTheTitle(resId: Int) {
        setTheTitle(resources.getText(resId).toString())
    }

    protected open fun getToolbarContent(): Int {
        return R.layout.include_toolbar_content
    }
}