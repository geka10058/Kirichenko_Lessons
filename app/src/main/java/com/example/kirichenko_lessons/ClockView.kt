package com.example.kirichenko_lessons

import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import androidx.core.content.ContextCompat
import java.time.LocalDate
import java.util.*
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin


enum class HandType { HOUR, MINUTE, SECOND }

class ClockView(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    constructor(context: Context) : this(context, null)

    private val darkShadowColor = ContextCompat.getColor(context, R.color.dark_shadow_color)
    private val lightShadowColor = ContextCompat.getColor(context, R.color.light_shadow_color)
    private val darkBackgroundColor = ContextCompat.getColor(context, R.color.dark_background_color)
    private val lightBackgroundColor =
        ContextCompat.getColor(context, R.color.light_background_color)
    private val borderColor = ContextCompat.getColor(context, R.color.border_color)
    private val textColor = ContextCompat.getColor(context, R.color.text_color)
    private var hourHandsColor = ContextCompat.getColor(context, R.color.hour_hands_color)
    private var secondHandsColor = ContextCompat.getColor(context, R.color.second_hands_color)
    private var minuteHandsColor = ContextCompat.getColor(context, R.color.minute_hour_hands_color)
    private val textFontSize =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14f, context.resources.displayMetrics)
    private val defaultMargin =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8f, context.resources.displayMetrics)
    private var hourHandsSize = 0
    private var minuteHandsSize = 0
    private var secondHandsSize = 0

    private var mHeight: Int = 0
    private var mWidth: Int = 0
    private var mHourHandSize: Int = 0
    private var mHandSize: Int = 0
    private var mPadding: Int = 50
    private var mRadius: Int = 0
    private var mPaint = Paint()
    private var mIsInit: Boolean = false
    private var mAngle: Double = 0.0
    private var mCentreX: Float = 0F
    private var mCentreY: Float = 0F
    private var mMinimum: Int = 0
    private var mHour = 0
    private lateinit var mPath: Path
    private lateinit var mRect: Rect
    private lateinit var mNumbers: Array<Int>

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ClockView,
            0, 0
        ).apply {
            try {
                hourHandsColor = getColor(
                    R.styleable.ClockView_hourHandsColor,
                    ContextCompat.getColor(context, R.color.hour_hands_color)
                )
                minuteHandsColor = getColor(
                    R.styleable.ClockView_minuteHandsColor,
                    ContextCompat.getColor(context, R.color.hour_hands_color)
                )
                secondHandsColor = getColor(
                    R.styleable.ClockView_secondHandsColor,
                    ContextCompat.getColor(context, R.color.second_hands_color)
                )
                hourHandsSize = getDimensionPixelSize(R.styleable.ClockView_hourHandsSize, 50)
                minuteHandsSize = getDimensionPixelSize(R.styleable.ClockView_minuteHandsSize, 100)
                secondHandsSize = getDimensionPixelSize(R.styleable.ClockView_secondHandsSize, 120)
            } finally {
                recycle()
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (!mIsInit) initialize()

        drawCircle(canvas)
        drawHands(canvas)
        drawNumerals(canvas)
        drawCurrentDayText(canvas)
    }

    private fun initialize() {
        mHeight = height
        mWidth = width
        mMinimum = min(mHeight, mWidth)
        mRadius = (mMinimum / 2 - mPadding) - 20
        mPaint.isAntiAlias = true
        mIsInit = true
        mCentreX = (mWidth / 2).toFloat()
        mCentreY = (mHeight / 2).toFloat()
        mAngle = ((Math.PI / 30) - (Math.PI / 2))
        mPaint = Paint()
        mPath = Path()
        mRect = Rect()
        mHourHandSize = mRadius - mRadius / 2
        mHandSize = mRadius - mRadius / 4
        mNumbers = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
    }

    private fun drawCircle(canvas: Canvas?) {
        //Bottom shadow
        mPaint.reset()
        mPaint.setShadowLayer(30f, 15f, 15f, darkShadowColor)
        canvas?.drawCircle(mCentreX - 4, mCentreY, mRadius + 40.toFloat(), mPaint)

        //Top shadow
        mPaint.setShadowLayer(30f, -15f, -15f, lightShadowColor)
        canvas?.drawCircle(mCentreX, mCentreY, mRadius.toFloat(), mPaint)

        //Clock circle
        mPaint.color = lightBackgroundColor
        canvas?.drawCircle(mCentreX - 4, mCentreY, mRadius + 40.toFloat(), mPaint)

        //Border
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 4f
        mPaint.color = borderColor
        canvas?.drawCircle(mCentreX - 4, mCentreY, mRadius + 40.toFloat(), mPaint)
        mPaint.reset()
    }

    private fun setPaintAttributes(color: Int, stroke: Paint.Style, strokeWidth: Float) {

        mPaint.reset()
        mPaint.color = color
        mPaint.style = stroke
        mPaint.strokeWidth = strokeWidth
        mPaint.isAntiAlias = true
        mPaint.setShadowLayer(strokeWidth * 3, 15f, 15f, color)
    }

    private fun drawHands(canvas: Canvas?) {

        mPaint.reset()
        val calendar: Calendar = Calendar.getInstance()
        mHour = calendar.get(Calendar.HOUR)
        drawHandLine(canvas, (mHour + calendar.get(Calendar.MINUTE) / 60f) * 5f, HandType.HOUR)
        drawHandLine(canvas, calendar.get(Calendar.MINUTE).toFloat(), HandType.MINUTE)
        drawHandLine(canvas, calendar.get(Calendar.SECOND).toFloat(), HandType.SECOND)

        postInvalidateDelayed(500)
        invalidate()
    }

    private fun drawHandLine(canvas: Canvas?, value: Float, handType: HandType) {

        mPaint.reset()
        mAngle = Math.PI * value / 30 - Math.PI / 2
        var handRadius = 0

        when (handType) {
            HandType.HOUR -> {
                //handRadius = mRadius - mRadius / 2
                handRadius = hourHandsSize
                setPaintAttributes(hourHandsColor, Paint.Style.STROKE, 12F)
            }
            HandType.MINUTE -> {
                //handRadius = mRadius - mRadius / 4
                handRadius = minuteHandsSize
                setPaintAttributes(minuteHandsColor, Paint.Style.STROKE, 6F)
            }
            HandType.SECOND -> {
                //handRadius = mRadius - mRadius / 9
                handRadius = secondHandsSize
                setPaintAttributes(secondHandsColor, Paint.Style.STROKE, 6F)
            }
        }

        mPaint.strokeCap = Paint.Cap.ROUND

        canvas?.drawLine(
            mCentreX,
            mCentreY,
            (mWidth / 2 + cos(mAngle) * handRadius).toFloat(),
            (mHeight / 2 + sin(mAngle) * handRadius).toFloat(),
            mPaint
        )
    }

    private fun drawNumerals(canvas: Canvas?) {

        mPaint.reset()
        mPaint.textSize = textFontSize
        mPaint.isFakeBoldText = true
        mPaint.color = textColor

        for (hour in mNumbers) {
            val tmp = hour.toString()

            mPaint.getTextBounds(tmp, 0, tmp.length, mRect)
            val angle = Math.PI / 6 * (hour - 3)
            val x = ((mCentreX + 10) + cos(angle) * mRadius - mRect.width() / 2).toFloat()
            val y = (mCentreY.toDouble() + sin(angle) * mRadius + (mRect.height() / 2)).toFloat()

            if (listOf(12, 3, 6, 9).contains(hour)) {
                canvas?.drawText(tmp, x, y, mPaint)
            } else canvas?.drawText("•", x, y, mPaint)
        }
    }

    private fun drawCurrentDayText(canvas: Canvas?) {

        val xPosition = mCentreX
        val yPosition = (mCentreY - (mRadius / 1.3)).toFloat()
        val calendar: Calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH)
        val currentDay = calendar.get(Calendar.DAY_OF_MONTH)
        val currentDate = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDate.now().toString()
        } else {
            "$currentDay.$currentMonth.$currentYear"
        }

        mPaint.reset()
        mPaint.textSize = textFontSize
        mPaint.textAlign = Paint.Align.CENTER

        val textSize = mPaint.measureText(currentDate) / 2
        val rectF = RectF(
            xPosition - textSize - defaultMargin,
            yPosition - textSize / 2,
            xPosition + textSize + defaultMargin,
            yPosition + defaultMargin
        )

        mPaint.setShadowLayer(4f, 4f, 4f, darkShadowColor)
        canvas?.drawRoundRect(rectF, defaultMargin, defaultMargin, mPaint)

        mPaint.color = darkBackgroundColor
        mPaint.setShadowLayer(4f, -4f, -4f, darkShadowColor)
        canvas?.drawRoundRect(rectF, defaultMargin, defaultMargin, mPaint)

        mPaint.strokeWidth = 2f
        mPaint.style = Paint.Style.STROKE
        mPaint.color = borderColor
        canvas?.drawRoundRect(rectF, defaultMargin, defaultMargin, mPaint)

        mPaint.style = Paint.Style.FILL
        mPaint.isFakeBoldText = true
        mPaint.color = textColor
        canvas?.drawText(
            currentDate,
            rectF.centerX(),
            (rectF.centerY() - defaultMargin) + (textSize / 2) - 8,
            mPaint
        )
    }
}