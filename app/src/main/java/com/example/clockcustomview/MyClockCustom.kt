package com.example.clockcustomview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.text.style.UpdateLayout

import android.util.AttributeSet
import android.view.View
import androidx.annotation.CheckResult
import androidx.annotation.ColorInt
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import java.util.*


class MyClockCustom @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private lateinit var mBitmapSec: Bitmap
    private lateinit var mBitmapMint: Bitmap
    private lateinit var mBitmapHr: Bitmap
    private lateinit var mBitmapMain: Bitmap

    var startDegHr = 0.0F
    var startDegMint = 0.0F
    var startDegSec = 0.0F

    private val clockImage: Int = R.drawable.clock_bg
    private val hourImage: Int = R.drawable.clock_hour
    private val mintImage: Int = R.drawable.clock_min
    private val secImage: Int = R.drawable.clock_sec

    private val hourColor: Int = Color.parseColor("#ffffff")
    private val mintColor: Int = Color.parseColor("#ffffff")
    private val secColor: Int = Color.parseColor("#ffffff")


    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //getInitialAllAngle()
        drawCanvas(canvas!!)
        invalidate()
    }


    private fun drawCanvas(canvas: Canvas) {

        getInitialAllAngle()

        mBitmapMain = BitmapFactory.decodeResource(resources, clockImage)
        val bitmap = Bitmap.createScaledBitmap(
            mBitmapMain,
            getPercentageWidth(70, canvas.width),
            getPercentageWidth(70, canvas.width),
            true
        )

        canvas.drawBitmap(
            bitmap,
            ((canvas.width - bitmap.width) / 2).toFloat(),
            ((canvas.height - bitmap.height) / 2).toFloat(),
            null
        )

        mBitmapHr = BitmapFactory.decodeResource(resources, hourImage)
        hrNeedleControl(canvas, startDegHr)

        mBitmapMint = BitmapFactory.decodeResource(resources, mintImage)
        mintNeedleControl(canvas, startDegMint)

        mBitmapSec = BitmapFactory.decodeResource(resources, secImage)
        secNeedleControl(canvas, startDegSec)

    }


    private fun getPercentageWidth(percentage: Int, mWidth: Int): Int {
        return ((percentage * mWidth) / 100)

    }

    private fun secNeedleControl(canvas: Canvas, mStartDegSec: Float) {
        val paint = Paint()
        val filter: ColorFilter = PorterDuffColorFilter(
            secColor,
            PorterDuff.Mode.SRC_IN
        )
        paint.colorFilter = filter

        val width: Int = mBitmapSec.width
        val height: Int = mBitmapSec.height
        val newWidth = getPercentageWidth(70, canvas.width)
        val newHeight = getPercentageWidth(70, canvas.width)

        val scaleWidth = newWidth.toFloat() / width
        val scaleHeight = newHeight.toFloat() / height

        val matrix = Matrix()
        matrix.postScale(scaleWidth, scaleHeight)
        matrix.postRotate(mStartDegSec)
        mBitmapSec = Bitmap.createBitmap(
            mBitmapSec, 0, 0,
            width, height, matrix, true
        )

        if (secColor == Color.parseColor("#00FFFFFF")) {
            canvas.drawBitmap(
                mBitmapSec,
                ((canvas.width - mBitmapSec.width) / 2).toFloat(),
                ((canvas.height - mBitmapSec.height) / 2).toFloat(),
                null
            )
        } else {
            canvas.drawBitmap(
                mBitmapSec,
                ((canvas.width - mBitmapSec.width) / 2).toFloat(),
                ((canvas.height - mBitmapSec.height) / 2).toFloat(),
                paint
            )
        }


    }

    private fun mintNeedleControl(canvas: Canvas, mStartDegMint: Float) {
        val paint = Paint()
        val filter: ColorFilter = PorterDuffColorFilter(
            mintColor,
            PorterDuff.Mode.SRC_IN
        )
        paint.colorFilter = filter

        val width: Int = mBitmapMint.width
        val height: Int = mBitmapMint.height
        val newWidth = getPercentageWidth(70, canvas.width)
        val newHeight = getPercentageWidth(70, canvas.width)

        val scaleWidth = newWidth.toFloat() / width
        val scaleHeight = newHeight.toFloat() / height

        val matrix = Matrix()
        matrix.postScale(scaleWidth, scaleHeight)
        matrix.postRotate(mStartDegMint)
        mBitmapMint = Bitmap.createBitmap(
            mBitmapMint, 0, 0,
            width, height, matrix, true
        )

        if (mintColor == Color.parseColor("#00FFFFFF")) {
            canvas.drawBitmap(
                mBitmapMint,
                ((canvas.width - mBitmapMint.width) / 2).toFloat(),
                ((canvas.height - mBitmapMint.height) / 2).toFloat(),
                null
            )
        } else {
            canvas.drawBitmap(
                mBitmapMint,
                ((canvas.width - mBitmapMint.width) / 2).toFloat(),
                ((canvas.height - mBitmapMint.height) / 2).toFloat(),
                paint
            )
        }


    }

    private fun hrNeedleControl(canvas: Canvas, mStartDegHr: Float) {

        val paint = Paint()
        val filter: ColorFilter = PorterDuffColorFilter(
            hourColor,
            PorterDuff.Mode.SRC_IN
        )
        paint.colorFilter = filter

        val width: Int = mBitmapHr.width
        val height: Int = mBitmapHr.height
        val newWidth = getPercentageWidth(70, canvas.width)
        val newHeight = getPercentageWidth(70, canvas.width)

        val scaleWidth = newWidth.toFloat() / width
        val scaleHeight = newHeight.toFloat() / height

        val matrix = Matrix()
        matrix.postScale(scaleWidth, scaleHeight)
        matrix.postRotate(mStartDegHr)
        mBitmapHr = Bitmap.createBitmap(
            mBitmapHr, 0, 0,
            width, height, matrix, true
        )

        if (hourColor == Color.parseColor("#00FFFFFF")) {
            canvas.drawBitmap(
                mBitmapHr,
                ((canvas.width - mBitmapHr.width) / 2).toFloat(),
                ((canvas.height - mBitmapHr.height) / 2).toFloat(),
                null
            )
        } else {
            canvas.drawBitmap(
                mBitmapHr,
                ((canvas.width - mBitmapHr.width) / 2).toFloat(),
                ((canvas.height - mBitmapHr.height) / 2).toFloat(),
                paint
            )
        }
    }


    private fun getInitialAllAngle() {
        val calender: Calendar = Calendar.getInstance()
        val mSecond: Int = calender.get(Calendar.SECOND)
        val mMinute: Int = calender.get(Calendar.MINUTE)
        val mHour: Int = calender.get(Calendar.HOUR)

        startDegSec = initialSecondAngle(mSecond)
        startDegMint = initialMinuteAngle(mMinute)
        startDegHr = initialHourAngle(mHour, mMinute)
    }

    private fun initialSecondAngle(sec: Int): Float {
        return sec * 6F
    }

    private fun initialMinuteAngle(mint: Int): Float {
        return mint * 6F
    }

    private fun initialHourAngle(hour: Int, mint: Int): Float {
        return ((hour * 30) + (mint * 0.5)).toFloat()
    }

    private fun needleControlAngle() {

        if (startDegSec >= 360) {
            startDegSec = 6.0F
            if (startDegMint >= 360) {
                startDegMint = 6.0F
                if (startDegHr >= 360) {
                    startDegHr = 0.5F
                } else {
                    startDegHr += 0.5F
                }
            } else {
                startDegMint += 6.0F
                if (startDegMint % 6 == 0.0F) {
                    if (startDegHr >= 360) {
                        startDegHr = 0.5F
                    } else {
                        startDegHr += 0.5F
                    }
                }
            }
        } else {
            startDegSec += 6.0F
        }

    }
}
