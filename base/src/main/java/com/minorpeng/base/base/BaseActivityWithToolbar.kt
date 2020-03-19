package com.minorpeng.base.base

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.minorpeng.base.R

/**
 *
 * @author MinorPeng
 * @date 2020/2/8 11:00
 */
abstract class BaseActivityWithToolbar<P : BasePresenter<out IBaseView, out IBaseModel>> :
    BaseActivity<P>() {

    protected var mToolbar: Toolbar? = null
    private var mTitle: TextView? = null

    override fun inflateContentView(): View {
        mContentView = View.inflate(this, R.layout.activity_base, null)
        (mContentView as ViewGroup).addView(initToolbar(mContentView as ViewGroup), 0)
        View.inflate(this, getLayoutId(), mContentView as ViewGroup?)
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
            toolbar.setBackgroundResource(R.drawable.bg_toolbar)
            toolbar.addView(layoutInflater.inflate(getToolbarContent(), mToolbar, false))
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            initToolbarEvent(toolbar)
        }
        return mToolbar
    }

    private fun initToolbarEvent(toolbar: Toolbar) {
        val ivLeft = toolbar.findViewById<ImageView>(R.id.iv_toolbar_left)
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