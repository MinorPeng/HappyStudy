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
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

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

    private val mShapeMap = HashMap<String, Shape>()

    private val mBgColors = ArrayList<Int>()
    private val mCurP = Point()
    private var mRoleR = 0
    private var mRoleT = 0

    private var mCurColorIndex = 0

    private var mListener: IRoleChangeListener? = null

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

        // mLines.add(DrawLine(10f, 0f, 50f, 0f, 2f, Color.BLACK, 0f))
        // mLines.add(DrawLine(10f, 0f, 50f, 0f, 2f, Color.RED, 90f))
        // mLines.add(DrawLine(10f, 0f, 50f, 0f, 2f, Color.GREEN, 180f))
        // mLines.add(DrawLine(10f, 0f, 50f, 0f, 2f, Color.BLUE, 270f))
        // hide()
    }

    fun setListener(listener: IRoleChangeListener) {
        this.mListener = listener
    }

    fun restore() {
        VariableMap.clear()
        mShapeMap.clear()
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
            for (shape in mShapeMap.values) {
                when (shape) {
                    is DrawCircle -> {
                        logD(msg = "draw circle: $shape")
                        mPaint.color = shape.color
                        mPaint.style = shape.style
                        mPaint.strokeWidth = shape.w
                        canvas.drawCircle(shape.cx, shape.cy, shape.r, mPaint)
                    }
                    is DrawPoint -> {
                        logD(msg = "draw point: $shape")
                        mPaint.color = shape.color
                        mPaint.strokeWidth = shape.r
                        mPaint.style = Paint.Style.FILL_AND_STROKE
                        canvas.drawPoint(shape.cx, shape.cy, mPaint)
                    }
                    is DrawRect -> {
                        logD(msg = "draw rect: $shape")
                        canvas.save()
                        // 旋转操作要在所有的canvas操作之前，否则不生效
                        canvas.rotate(shape.rotation)
                        mPaint.color = shape.color
                        mPaint.style = shape.style
                        mPaint.strokeWidth = shape.w
                        canvas.drawRect(shape.x1, shape.y1, shape.x2, shape.y2, mPaint)
                        canvas.restore()
                    }
                    is DrawLine -> {
                        logD(msg = "draw line: $shape")
                        canvas.save()
                        canvas.rotate(shape.rotation)
                        mPaint.color = shape.color
                        mPaint.strokeWidth = shape.w
                        canvas.drawLine(shape.startX, shape.startY, shape.endX, shape.endY, mPaint)
                        canvas.restore()
                    }
                    is DrawTriangle -> {
                        logD(msg = "draw shape: $shape")
                        canvas.save()
                        canvas.rotate(shape.rotation)
                        mPath.reset()
                        mPaint.color = shape.color
                        mPaint.style = shape.style
                        mPaint.strokeWidth = shape.w
                        mPath.moveTo(shape.x1, shape.y1)
                        mPath.lineTo(shape.x2, shape.y2)
                        mPath.lineTo(shape.x3, shape.y3)
                        mPath.close()
                        mPath.fillType = when (shape.style) {
                            Paint.Style.STROKE -> Path.FillType.WINDING
                            Paint.Style.FILL -> Path.FillType.EVEN_ODD
                            else -> Path.FillType.WINDING
                        }
                        canvas.drawPath(mPath, mPaint)
                        canvas.restore()
                    }
                }
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

    override fun drawCircle(cx: Float, cy: Float, r: Float, w: Float, color: Int, style: Paint.Style, name: String?) {
        logD(msg = "draw circle")
        var nameStr = name
        val shape = mShapeMap[nameStr]
        if (shape is DrawCircle) {
            shape.update(cx, -cy, r, w, color, style)
        } else {
            if (nameStr.isNullOrEmpty()) {
                nameStr = getRandomStr()
            }
            mShapeMap[nameStr] = DrawCircle(nameStr, cx, -cy, r, w, color, style)
        }
        invalidate()
    }

    override fun drawPoint(cx: Float, cy: Float, r: Float, color: Int, name: String?) {
        logD(msg = "draw point")
        var nameStr = name
        val shape = mShapeMap[nameStr]
        if (shape is DrawPoint) {
            shape.update(cx, -cy, r, color)
        } else {
            if (nameStr.isNullOrEmpty()) {
                nameStr = getRandomStr()
            }
            mShapeMap[nameStr] = DrawPoint(nameStr, cx, -cy, r, color)
        }
        invalidate()
    }

    override fun drawRect(
        x1: Float, y1: Float, x2: Float, y2: Float, w: Float,
        color: Int, style: Paint.Style, name: String?, rotation: Float
    ) {
        logD(msg = "draw rect")
        var nameStr = name
        val shape = mShapeMap[nameStr]
        if (shape is DrawRect) {
            shape.update(x1, -y1, x2, -y2, w, color, style, rotation)
        } else {
            if (nameStr.isNullOrEmpty()) {
                nameStr = getRandomStr()
            }
            mShapeMap[nameStr] = DrawRect(nameStr, x1, -y1, x2, -y2, w, color, style, rotation)
        }
        invalidate()
    }

    override fun drawLine(
        startX: Float, startY: Float, endX: Float, endY: Float,
        w: Float, color: Int, name: String?, rotation: Float
    ) {
        logD(msg = "draw line")
        var nameStr = name
        val shape = mShapeMap[nameStr]
        if (shape is DrawLine) {
            shape.update(startX, -startY, endX, -endY, w, color, rotation)
        } else {
            if (nameStr.isNullOrEmpty()) {
                nameStr = getRandomStr()
            }
            mShapeMap[nameStr] = DrawLine(nameStr, startX, -startY, endX, -endY, w, color, rotation)
        }
        invalidate()
    }

    override fun drawTriangle(
        x1: Float, y1: Float, x2: Float, y2: Float, x3: Float, y3: Float,
        w: Float, color: Int, style: Paint.Style, name: String?, rotation: Float
    ) {
        logD(msg = "draw triangle")
        var nameStr = name
        val shape = mShapeMap[nameStr]
        if (shape is DrawTriangle) {
            shape.update(x1, -y1, x2, -y2, x3, -y3, w, color, style, rotation)
        } else {
            if (nameStr.isNullOrEmpty()) {
                nameStr = getRandomStr()
            }
            mShapeMap[nameStr] = DrawTriangle(nameStr, x1, -y1, x2, -y2, x3, -y3, w, color, style, rotation)
        }
        invalidate()
    }

    private val mRandom = Random()
    private val mRandomBytes = ByteArray(8)

    private fun getRandomStr(): String {
        mRandom.nextBytes(mRandomBytes)
        return String(mRandomBytes)
    }

    interface IRoleChangeListener {
        fun directionChange(curDire: Float)

        fun positionChanged(curPos: Point)
    }

    enum class ChildTag {
        TAG_ROLE,
        TAG_SAY
    }

    private interface Shape {
        val name: String
    }

    private data class DrawCircle(
        override val name: String,
        var cx: Float, var cy: Float, var r: Float,
        var w: Float, var color: Int, var style: Paint.Style
    ) : Shape {

        fun update(cx: Float, cy: Float, r: Float, w: Float, color: Int, style: Paint.Style) {
            this.cx = cx
            this.cy = cy
            this.r = r
            this.w = w
            this.color = color
            this.style = style
        }

        override fun toString(): String {
            return "name:$name p($cx, $cy) r:$r w:$w, color:$color, style:$style"
        }
    }

    private data class DrawPoint(
        override val name: String, var cx: Float, var cy: Float,
        var r: Float, var color: Int
    ) : Shape {

        fun update(cx: Float, cy: Float, r: Float, color: Int) {
            this.cx = cx
            this.cy = cy
            this.r = r
            this.color = color
        }

        override fun toString(): String {
            return "name:$name p($cx, $cy) r:$r, color:$color"
        }
    }

    private data class DrawRect(
        override val name: String,
        var x1: Float, var y1: Float, var x2: Float, var y2: Float,
        var w: Float, var color: Int, var style: Paint.Style, var rotation: Float
    ) : Shape {

        fun update(
            x1: Float, y1: Float, x2: Float, y2: Float, w: Float,
            color: Int, style: Paint.Style, rotation: Float
        ) {
            this.x1 = x1
            this.y1 = y1
            this.x2 = x2
            this.y2 = y2
            this.w = w
            this.color = color
            this.style = style
            this.rotation = rotation
        }

        override fun toString(): String {
            return "name:$name p1($x1, $y1) p2($x2, $y2) w:$w, color:$color, style:$style rotation:$rotation"
        }
    }

    private data class DrawLine(
        override val name: String,
        var startX: Float, var startY: Float, var endX: Float, var endY: Float,
        var w: Float, var color: Int, var rotation: Float
    ) : Shape {

        fun update(startX: Float, startY: Float, endX: Float, endY: Float, w: Float, color: Int, rotation: Float) {
            this.startX = startX
            this.startY = startY
            this.endX = endX
            this.endY = endY
            this.w = w
            this.color = color
            this.rotation = rotation
        }

        override fun toString(): String {
            return "name:$name startP($startX, $startY) endP($endX, $endY) w:$w, color:$color rotation:$rotation"
        }
    }

    private data class DrawTriangle(
        override val name: String,
        var x1: Float, var y1: Float, var x2: Float, var y2: Float, var x3: Float, var y3: Float,
        var w: Float, var color: Int, var style: Paint.Style, var rotation: Float
    ) : Shape {

        fun update(
            x1: Float, y1: Float, x2: Float, y2: Float, x3: Float, y3: Float,
            w: Float, color: Int, style: Paint.Style, rotation: Float
        ) {
            this.x1 = x1
            this.y1 = y1
            this.x2 = x2
            this.y2 = y2
            this.x3 = x3
            this.y3 = y3
            this.color = color
            this.style = style
            this.rotation = rotation
        }

        override fun toString(): String {
            return "name:$name p1($x1, $y1) p2($x2, $y2) p3($x3, $y3) " +
                    "w:$w, color:$color, style:$style rotation:$rotation"
        }
    }

}