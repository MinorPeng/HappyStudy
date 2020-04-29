package com.hesheng1024.happystudy.custom

import android.content.Context
import android.graphics.Point
import android.text.method.ScrollingMovementMethod
import android.util.AttributeSet
import android.view.View
import android.view.ViewStub
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.hesheng1024.base.utils.dp2px
import com.hesheng1024.base.utils.logD
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.custom.base.IRoleView
import com.hesheng1024.happystudy.utils.MediaPlayerUtil
import com.hesheng1024.happystudy.utils.adjustVolume
import java.util.*

/**
 *
 * @author hesheng1024
 * @date 2020/4/22 13:11
 */
class RoleViewGroup : FrameLayout, IRoleView {

    private val mIvRole: AppCompatImageView
    private var mTvSay: AppCompatTextView? = null
    private val mViewStub: ViewStub

    private val mBgColors = ArrayList<Int>()
    private val mCurPos = Point()

    private var mRoleR = 0
    private var mRoleT = 0

    private var mCurColorIndex = 0
    private var mListener: IChangeListener? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        mIvRole = AppCompatImageView(context)
        mViewStub = ViewStub(context)
        initView()
        initData()
    }

    private fun initView() {
        val lpRole = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        mIvRole.setImageResource(R.drawable.role)
        mIvRole.visibility = View.VISIBLE
        mIvRole.tag = ChildTag.TAG_ROLE
        addView(mIvRole, lpRole)

        val lpSay = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        mViewStub.layoutResource = R.layout.layout_programme_role_say
        mViewStub.tag = ChildTag.TAG_SAY
        addView(mViewStub, lpSay)
    }

    private fun initData() {
        mBgColors.add(ContextCompat.getColor(context, R.color.colorRoleBg1))
        mBgColors.add(ContextCompat.getColor(context, R.color.colorRoleBg2))
        mBgColors.add(ContextCompat.getColor(context, R.color.colorRoleBg3))
        mBgColors.add(ContextCompat.getColor(context, R.color.colorRoleBg4))
    }

    fun setListener(listener: IChangeListener) {
        this.mListener = listener
    }

    fun restore() {
        setDirection(90f)
        moveToXY(0f, 0f)
        mCurColorIndex = 0
        setBgColor()
        mViewStub.visibility = View.INVISIBLE
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        for (index in 0 until childCount) {
            val child = getChildAt(index)
            if (child.visibility == View.GONE) {
                continue
            }
            val lp = child.layoutParams as LayoutParams
            val childW = child.measuredWidth
            val childH = child.measuredHeight
            var l: Int
            var t: Int
            var r: Int
            var b: Int
            when (child.tag) {
                ChildTag.TAG_ROLE -> {
                    l = measuredWidth / 2 + lp.leftMargin
                    t = measuredHeight / 2 - childH + lp.topMargin
                    r = l + childW
                    b = t + childH
                    mRoleR = r
                    mRoleT = t
                }
                ChildTag.TAG_SAY -> {
                    l = mRoleR - dp2px(context, 8f) + lp.leftMargin
                    t = mRoleT - childH + lp.topMargin
                    r = l + childW
                    b = t + childH
                }
                else -> return
            }
            child.layout(l, t, r, b)
        }
    }


    override fun say(content: String) {
        try {
            mTvSay = mViewStub.inflate() as AppCompatTextView
            mTvSay?.tag = ChildTag.TAG_SAY
            mTvSay?.movementMethod = ScrollingMovementMethod.getInstance()
        } catch (e: Exception) {
            mViewStub.visibility = View.VISIBLE
        }
        if (mTvSay?.text.toString() != content) {
            mTvSay?.text = content
        }
    }

    override fun hide() {
        mIvRole.visibility = View.INVISIBLE
    }

    override fun show() {
        mIvRole.visibility = View.VISIBLE
    }

    override fun nextBackground() {
        mCurColorIndex++
        if (mCurColorIndex == mBgColors.size) {
            mCurColorIndex = 0
        }
        setBgColor()
    }

    private fun setBgColor() {
        val bgColor = mBgColors[mCurColorIndex]
        setBackgroundColor(bgColor)
        mIvRole.setBackgroundColor(bgColor)
    }

    override fun moveStep(step: Int) {
        mCurPos.x += step
        setPosition()
    }

    override fun moveToXY(x: Float, y: Float) {
        mCurPos.x = x.toInt()
        mCurPos.y = y.toInt()
        setPosition()
    }

    override fun decreaseX(x: Float) {
        mCurPos.x -= x.toInt()
        setPosition()
    }

    override fun decreaseY(y: Float) {
        mCurPos.y -= y.toInt()
        setPosition()
    }

    override fun increaseX(x: Float) {
        mCurPos.x += x.toInt()
        setPosition()
    }

    override fun increaseY(y: Float) {
        mCurPos.y += y.toInt()
        setPosition()
    }

    override fun setPX(x: Float) {
        mCurPos.x = x.toInt()
        setPosition()
    }

    override fun setPY(y: Float) {
        mCurPos.y = y.toInt()
        setPosition()
    }

    private fun setPosition() {
        val lp = mIvRole.layoutParams as LayoutParams
        lp.leftMargin = mCurPos.x
        lp.topMargin = -mCurPos.y
        requestLayout()
        mListener?.positionChanged(mCurPos)
    }

    override fun setDirection(direction: Float) {
        val d = when {
            direction <= -180 -> 180 - direction
            direction > 180 -> direction - 180
            else -> direction
        }
        mIvRole.rotation = d - 90
        logD(msg = "setDirection?? ${mIvRole.rotation} $direction")
        mListener?.directionChange(d)
    }

    override fun leftRotate(rotation: Float) {
        setDirection(mIvRole.rotation + 90 - rotation)
    }

    override fun rightRotate(rotation: Float) {
        setDirection(mIvRole.rotation + 90 + rotation)
    }

    override fun decreaseVolume(volume: Float) {
        adjustVolume(-volume.toInt())
    }

    override fun increaseVolume(volume: Float) {
        adjustVolume(volume.toInt())
    }

    override fun playVoice() {
        MediaPlayerUtil.play()
    }

    override fun playVoice(rawId: Int) {
        MediaPlayerUtil.play(rawId)
    }

    override fun stopVoice() {
        MediaPlayerUtil.stop()
    }

    interface IChangeListener {
        fun directionChange(curDire: Float)

        fun positionChanged(curPos: Point)
    }

    enum class ChildTag {
        TAG_ROLE,
        TAG_SAY
    }
}