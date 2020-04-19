package com.hesheng1024.happystudy.custom

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.custom.base.IRoleView
import com.hesheng1024.happystudy.utils.MediaPlayerUtil
import com.hesheng1024.happystudy.utils.VolumeUtil
import java.util.ArrayList

/**
 *
 * @author hesheng1024
 * @date 2020/4/15 10:45
 */
class RoleView : RelativeLayout, IRoleView {

    companion object {
        private const val IV_COPY_ID = 1
        private const val IV_ROLE_ID = 2
        private const val TV_SAY_ID = 3
    }

    private val mIvRole: AppCompatImageView
    private val mTvSay: AppCompatTextView
    private val mBgColorIds = ArrayList<Int>()
    private var mCurBgId = 0

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        gravity = Gravity.CENTER
        mIvRole = AppCompatImageView(context)
        mTvSay = AppCompatTextView(context)
        initView()
        initData()
    }

    private fun initView() {
        val ivCopyLp = generateDefaultLayoutParams() as LayoutParams
        ivCopyLp.addRule(ALIGN_PARENT_START)
        ivCopyLp.addRule(ALIGN_PARENT_TOP)
        val ivRoleCopy = AppCompatImageView(context)
        ivRoleCopy.setImageResource(R.drawable.role)
        ivRoleCopy.visibility = View.INVISIBLE
        ivRoleCopy.id = IV_COPY_ID
        addView(ivRoleCopy, ivCopyLp)

        val ivRoleLp = generateDefaultLayoutParams() as LayoutParams
        ivRoleLp.addRule(BELOW, ivRoleCopy.id)
        ivRoleLp.addRule(ALIGN_START, ivRoleCopy.id)
        ivRoleLp.marginStart = 8
        mIvRole.setImageResource(R.drawable.role)
        mIvRole.visibility = View.VISIBLE
        mIvRole.id = IV_ROLE_ID
        addView(mIvRole, ivRoleLp)

        val tvSayLp = generateDefaultLayoutParams() as LayoutParams
        tvSayLp.addRule(ALIGN_TOP, ivRoleCopy.id)
        tvSayLp.addRule(END_OF, ivRoleCopy.id)
        tvSayLp.addRule(ALIGN_BOTTOM, ivRoleCopy.id)
        mTvSay.setBackgroundResource(R.drawable.bg_message)
        mTvSay.visibility = View.INVISIBLE
        mTvSay.id = TV_SAY_ID
        mTvSay.maxEms = 6
        mTvSay.maxLines = 3
        mTvSay.setPadding(4, 4, 4, 4)
        addView(mTvSay, tvSayLp)
    }

    private fun initData() {
        mBgColorIds.add(R.color.colorRoleBg1)
        mBgColorIds.add(R.color.colorRoleBg2)
        mBgColorIds.add(R.color.colorRoleBg3)
        mBgColorIds.add(R.color.colorRoleBg4)
    }

    override fun say(content: String) {
        showSayLayout(true, content)
    }

    override fun hide() {
        showSayLayout(false)
        this.visibility = View.INVISIBLE
    }

    override fun show() {
        showSayLayout(false)
        this.visibility = View.VISIBLE
    }

    override fun nextBackground() {
        showSayLayout(false)
        if (mCurBgId == mBgColorIds.size) {
            mCurBgId = 0
        }
        setBackgroundColor(mBgColorIds[mCurBgId++])
    }

    override fun moveStep(step: Int) {
        showSayLayout(false)
        (layoutParams as FrameLayout.LayoutParams).leftMargin += step
    }

    override fun moveToXY(x: Float, y: Float) {
        showSayLayout(false)
        (layoutParams as FrameLayout.LayoutParams).leftMargin = x.toInt()
        (layoutParams as FrameLayout.LayoutParams).topMargin = -y.toInt()
    }

    override fun decreaseX(x: Float) {
        showSayLayout(false)
        (layoutParams as FrameLayout.LayoutParams).leftMargin -= x.toInt()
    }

    override fun decreaseY(y: Float) {
        showSayLayout(false)
        (layoutParams as FrameLayout.LayoutParams).topMargin += y.toInt()
    }

    override fun increaseX(x: Float) {
        showSayLayout(false)
        (layoutParams as FrameLayout.LayoutParams).leftMargin += x.toInt()
    }

    override fun increaseY(y: Float) {
        showSayLayout(false)
        (layoutParams as FrameLayout.LayoutParams).topMargin -= y.toInt()
    }

    override fun setDirection(direction: Float) {
        showSayLayout(false)
        mIvRole.rotation = direction - 90
    }

    override fun leftRotate(rotation: Float) {
        showSayLayout(false)
        mIvRole.rotation += -rotation
    }

    override fun rightRotate(rotation: Float) {
        showSayLayout(false)
        mIvRole.rotation += rotation
    }

    override fun setPX(x: Float) {
        moveToXY(x, 0f)
    }

    override fun setPY(y: Float) {
        moveToXY(0f, y)
    }

    override fun decreaseVolume(volume: Float) {
        showSayLayout(false)
        VolumeUtil.adjustVolume(-volume.toInt())
    }

    override fun increaseVolume(volume: Float) {
        showSayLayout(false)
        VolumeUtil.adjustVolume(volume.toInt())
    }

    override fun playVoice() {
        showSayLayout(false)
        MediaPlayerUtil.play()
    }

    override fun stopVoice() {
        showSayLayout(false)
        MediaPlayerUtil.stop()
    }

    private fun showSayLayout(isShow: Boolean, content: String = "") {
        if (isShow && mTvSay.visibility != View.VISIBLE) {
            if (mTvSay.text.toString() != content) {
                mTvSay.text = content
            }
            mTvSay.visibility = View.VISIBLE
        } else if (!isShow && mTvSay.visibility == View.VISIBLE) {
            mTvSay.visibility = View.INVISIBLE
        }
    }
}