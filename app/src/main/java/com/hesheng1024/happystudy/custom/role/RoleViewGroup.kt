package com.hesheng1024.happystudy.custom.role

import android.content.Context
import android.graphics.*
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
import com.hesheng1024.happystudy.VariableMap
import com.hesheng1024.happystudy.utils.MediaPlayerUtil
import com.hesheng1024.happystudy.utils.adjustVolume

/**
 *
 * @author hesheng1024
 * @date 2020/4/22 13:11
 */
class RoleViewGroup : FrameLayout, IRoleView {

    private val mIvRole: AppCompatImageView
    private val mVsSay: ViewStub
    private var mTvSay: AppCompatTextView? = null

    private val mCenterP = PointF()
    private val mPaint = Paint()
    private val mPath = Path()

    private val mCircles = HashSet<DrawCircle>()
    private val mRects = HashSet<DrawRect>()
    private val mLines = HashSet<DrawLine>()
    private val mTriangles = HashSet<DrawTriangle>()

    private val mBgColors = ArrayList<Int>()
    private val mCurP = Point()
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
        mVsSay = ViewStub(context)
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
        mVsSay.layoutResource = R.layout.layout_programme_role_say
        mVsSay.tag = ChildTag.TAG_SAY
        addView(mVsSay, lpSay)
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
        VariableMap.clear()
        mCircles.clear()
        mRects.clear()
        mLines.clear()
        mTriangles.clear()
        setDirection(90f)
        moveToXY(0f, 0f)
        mCurColorIndex = 0
        setBgColor()
        mVsSay.visibility = View.INVISIBLE
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mCenterP.set(measuredWidth / 2f, measuredHeight / 2f)
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
                    l = lp.leftMargin - childW / 2
                    t = -childH + lp.topMargin + childH / 2
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

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            canvas.translate(mCenterP.x, mCenterP.y)
            for (circle in mCircles) {
                logD(msg = "draw circle: $circle")
                mPaint.color = circle.color
                mPaint.style = circle.style
                mPaint.strokeWidth = circle.w
                canvas.drawCircle(circle.cx, circle.cy, circle.r, mPaint)
            }
            for (rect in mRects) {
                logD(msg = "draw rect: $rect")
                mPaint.color = rect.color
                mPaint.style = rect.style
                mPaint.strokeWidth = rect.w
                canvas.drawRect(rect.x1, rect.y1, rect.x2, rect.y2, mPaint)
                canvas.rotate(rect.rotation)
                canvas.save()
                canvas.restore()
            }
            for (line in mLines) {
                logD(msg = "draw line: $line")
                mPaint.color = line.color
                mPaint.strokeWidth = line.w
                canvas.drawLine(line.startX, line.startY, line.endX, line.endY, mPaint)
                canvas.rotate(line.rotation)
                canvas.save()
                canvas.restore()
            }
            for (triangle in mTriangles) {
                logD(msg = "draw triangle: $triangle")
                mPath.reset()
                mPaint.color = triangle.color
                mPaint.style = triangle.style
                mPaint.strokeWidth = triangle.w
                mPath.moveTo(triangle.x1, triangle.y1)
                mPath.lineTo(triangle.x2, triangle.y2)
                mPath.lineTo(triangle.x3, triangle.y3)
                mPath.close()
                mPath.fillType = when (triangle.style) {
                    Paint.Style.STROKE -> Path.FillType.WINDING
                    Paint.Style.FILL -> Path.FillType.EVEN_ODD
                    else -> Path.FillType.WINDING
                }
                canvas.drawPath(mPath, mPaint)
                canvas.rotate(triangle.rotation)
                canvas.save()
                canvas.restore()
            }
        }
    }


    override fun say(content: String) {
        try {
            mTvSay = mVsSay.inflate() as AppCompatTextView
            mTvSay?.tag = ChildTag.TAG_SAY
            mTvSay?.movementMethod = ScrollingMovementMethod.getInstance()
        } catch (e: Exception) {
            mVsSay.visibility = View.VISIBLE
        }
        if (mTvSay?.text.toString() != content) {
            mTvSay?.text = content
        }
    }

    override fun hide() {
        mIvRole.visibility = View.INVISIBLE
        mVsSay.visibility = View.INVISIBLE
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
        mCurP.x += step
        setPosition()
    }

    override fun moveToXY(x: Float, y: Float) {
        mCurP.x = x.toInt()
        mCurP.y = y.toInt()
        setPosition()
    }

    override fun decreaseX(x: Float) {
        mCurP.x -= x.toInt()
        setPosition()
    }

    override fun decreaseY(y: Float) {
        mCurP.y -= y.toInt()
        setPosition()
    }

    override fun increaseX(x: Float) {
        mCurP.x += x.toInt()
        setPosition()
    }

    override fun increaseY(y: Float) {
        mCurP.y += y.toInt()
        setPosition()
    }

    override fun setPX(x: Float) {
        mCurP.x = x.toInt()
        setPosition()
    }

    override fun setPY(y: Float) {
        mCurP.y = y.toInt()
        setPosition()
    }

    private fun setPosition() {
        val lp = mIvRole.layoutParams as LayoutParams
        lp.leftMargin = mCurP.x
        lp.topMargin = -mCurP.y
        requestLayout()
        mListener?.positionChanged(mCurP)
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

    override fun drawCircle(cx: Float, cy: Float, r: Float, w: Float, color: Int, style: Paint.Style) {
        logD(msg = "draw circle")
        mCircles.add(DrawCircle(cx, -cy, r, w, color, style))
        invalidate()
    }

    override fun drawRect(
        x1: Float, y1: Float, x2: Float, y2: Float, w: Float,
        color: Int, style: Paint.Style, rotation: Float
    ) {
        logD(msg = "draw rect")
        mRects.add(DrawRect(x1, -y1, x2, -y2, w, color, style, rotation))
        invalidate()
    }

    override fun drawLine(
        startX: Float, startY: Float, endX: Float, endY: Float,
        w: Float, color: Int, rotation: Float
    ) {
        logD(msg = "draw line")
        mLines.add(DrawLine(startX, -startY, endX, -endY, w, color, rotation))
        invalidate()
    }

    override fun drawTriangle(
        x1: Float, y1: Float, x2: Float, y2: Float, x3: Float, y3: Float,
        w: Float, color: Int, style: Paint.Style, rotation: Float
    ) {
        logD(msg = "draw triangle")
        mTriangles.add(DrawTriangle(x1, -y1, x2, -y2, x3, -y3, w, color, style, rotation))
        invalidate()
    }

    interface IChangeListener {
        fun directionChange(curDire: Float)

        fun positionChanged(curPos: Point)
    }

    enum class ChildTag {
        TAG_ROLE,
        TAG_SAY
    }

    private data class DrawCircle(
        val cx: Float, val cy: Float, val r: Float,
        val w: Float, val color: Int, val style: Paint.Style
    ) {
        override fun toString(): String {
            return "p($cx, $cy) r:$r w:$w, color:$color, style:$style"
        }

        override fun equals(other: Any?): Boolean {
            if (other is DrawCircle) {
                return other.cx.equals(cx) && other.cy.equals(cy) && other.r.equals(r) && other.w.equals(w)
                        && other.color == color && other.style == style
            }
            return super.equals(other)
        }

        override fun hashCode(): Int {
            return toString().hashCode()
        }
    }

    private data class DrawRect(
        val x1: Float, val y1: Float, val x2: Float, val y2: Float,
        val w: Float, val color: Int, val style: Paint.Style, val rotation: Float
    ) {
        override fun toString(): String {
            return "p1($x1, $y1) p2($x2, $y2) w:$w, color:$color, style:$style rotation:$rotation"
        }

        override fun equals(other: Any?): Boolean {
            if (other is DrawTriangle) {
                return other.x1.equals(x1) && other.y1.equals(y1) &&
                        other.x2.equals(x2) && other.y2.equals(y2) &&
                        other.w.equals(w) && other.color == color &&
                        other.style == style && other.rotation.equals(rotation)
            }
            return super.equals(other)
        }

        override fun hashCode(): Int {
            return toString().hashCode()
        }
    }

    private data class DrawLine(
        val startX: Float, val startY: Float, val endX: Float, val endY: Float,
        val w: Float, val color: Int, val rotation: Float
    ) {
        override fun toString(): String {
            return "startP($startX, $startY) endP($endX, $endY) w:$w, color:$color rotation:$rotation"
        }

        override fun equals(other: Any?): Boolean {
            if (other is DrawLine) {
                return other.startX.equals(startX) && other.startY.equals(startY) &&
                        other.endX.equals(endX) && other.endY.equals(endY) &&
                        other.w.equals(w) && other.color == color && other.rotation.equals(rotation)
            }
            return super.equals(other)
        }

        override fun hashCode(): Int {
            return toString().hashCode()
        }
    }

    private data class DrawTriangle(
        val x1: Float, val y1: Float, val x2: Float, val y2: Float, val x3: Float, val y3: Float,
        val w: Float, val color: Int, val style: Paint.Style, val rotation: Float
    ) {

        override fun toString(): String {
            return "p1($x1, $y1) p2($x2, $y2) p3($x3, $y3) w:$w, color:$color, style:$style rotation:$rotation"
        }

        override fun equals(other: Any?): Boolean {
            if (other is DrawTriangle) {
                return other.x1.equals(x1) && other.y1.equals(y1) &&
                        other.x2.equals(x2) && other.y2.equals(y2) &&
                        other.x3.equals(x3) && other.y2.equals(y3) &&
                        other.w.equals(w) && other.color == color &&
                        other.style == style && other.rotation.equals(rotation)
            }
            return super.equals(other)
        }

        override fun hashCode(): Int {
            return toString().hashCode()
        }
    }

}