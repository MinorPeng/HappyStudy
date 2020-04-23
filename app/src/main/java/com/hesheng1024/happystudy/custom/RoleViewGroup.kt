package com.hesheng1024.happystudy.custom

import android.content.Context
import android.graphics.Point
import android.text.method.ScrollingMovementMethod
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.hesheng1024.base.utils.dp2px
import com.hesheng1024.base.utils.logD
import com.hesheng1024.happystudy.R
import com.hesheng1024.happystudy.custom.base.IRoleView
import com.hesheng1024.happystudy.utils.MediaPlayerUtil
import com.hesheng1024.happystudy.utils.adjustVolume
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

/**
 *
 * @author hesheng1024
 * @date 2020/4/22 13:11
 */
class RoleViewGroup : FrameLayout, IRoleView {

    private val mIvRole: AppCompatImageView
    private val mTvSay: AppCompatTextView
    private val mBgColorIds = ArrayList<Int>()
    private val mCurPos = Point()
    private var mCurBgId = 0
    private var mListener: IChangeListener? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        mIvRole = AppCompatImageView(context)
        mTvSay = AppCompatTextView(context)
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
        mTvSay.setBackgroundResource(R.drawable.bg_message)
        mTvSay.visibility = View.INVISIBLE
        mTvSay.maxEms = 6
        mTvSay.maxLines = 4
        mTvSay.isVerticalScrollBarEnabled = true
        mTvSay.scrollBarSize = 1
        mTvSay.textSize = 10f
        mTvSay.tag = ChildTag.TAG_SAY
        mTvSay.movementMethod = ScrollingMovementMethod.getInstance()
        addView(mTvSay, lpSay)
    }

    private fun initData() {
        mBgColorIds.add(R.color.colorRoleBg1)
        mBgColorIds.add(R.color.colorRoleBg2)
        mBgColorIds.add(R.color.colorRoleBg3)
        mBgColorIds.add(R.color.colorRoleBg4)
    }

    fun setListener(listener: IChangeListener) {
        this.mListener = listener
    }

    private var mRoleR = 0
    private var mRoleT = 0

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
        GlobalScope.launch(Dispatchers.Main) {
            showSayLayout(content)
        }
    }

    override fun hide() {
        GlobalScope.launch(Dispatchers.Main) {
            mIvRole.visibility = View.INVISIBLE
        }
    }

    override fun show() {
        GlobalScope.launch(Dispatchers.Main) {
            mIvRole.visibility = View.VISIBLE
        }
    }

    override fun nextBackground() {
        GlobalScope.launch(Dispatchers.Main) {
            if (mCurBgId == mBgColorIds.size) {
                mCurBgId = 0
            }
            setBackgroundColor(mBgColorIds[mCurBgId++])
        }
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
        GlobalScope.launch(Dispatchers.Main) {
            val lp = mIvRole.layoutParams as LayoutParams
            lp.leftMargin = mCurPos.x
            lp.topMargin = -mCurPos.y
            requestLayout()
            mListener?.positionChanged(mCurPos)
        }
    }

    override fun setDirection(direction: Float) {
        GlobalScope.launch(Dispatchers.Main) {
            val d = when {
                direction <= -180 -> 180 - direction
                direction > 180 -> direction - 180
                else -> direction
            }
            mIvRole.rotation = d - 90
            logD(msg = "setDirection?? ${mIvRole.rotation} $direction")
            mListener?.directionChange(d)
        }
    }

    override fun leftRotate(rotation: Float) {
        setDirection(mIvRole.rotation + 90 - rotation)
    }

    override fun rightRotate(rotation: Float) {
        setDirection(mIvRole.rotation + 90 + rotation)
    }

    override fun decreaseVolume(volume: Float) {
        GlobalScope.launch(Dispatchers.Main) {
            adjustVolume(-volume.toInt())
        }
    }

    override fun increaseVolume(volume: Float) {
        GlobalScope.launch(Dispatchers.Main) {
            adjustVolume(volume.toInt())
        }
    }

    override fun playVoice() {
        GlobalScope.launch(Dispatchers.Main) {
            MediaPlayerUtil.play()
        }
    }

    override fun stopVoice() {
        GlobalScope.launch(Dispatchers.Main) {
            MediaPlayerUtil.stop()
        }
    }

    fun showSayLayout(content: String? = null) {
        if (!content.isNullOrEmpty()) {
            if (mTvSay.text.toString() != content) {
                mTvSay.text = content
            }
            if (mTvSay.visibility != View.VISIBLE) {
                mTvSay.visibility = View.VISIBLE
            }
        } else {
            if (mTvSay.visibility == View.VISIBLE) {
                mTvSay.visibility = View.INVISIBLE
            }
        }
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