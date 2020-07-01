package com.hencoder.hencoderpracticedraw4.practice

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import com.hencoder.hencoderpracticedraw4.R

class Practice13CameraRotateHittingFaceView : View {
    var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var bitmap: Bitmap? = null
    var point = Point(200, 50)
    var camera = Camera()
    var myMatrix = Matrix()
    var degree = 0f
    var animator = ObjectAnimator.ofInt(this, "degree", 0, 360)

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        animator.start()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        animator.end()
    }

    fun setDegree(degree: Int) {
        this.degree = degree.toFloat()
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val bitmapWidth = bitmap!!.width
        val bitmapHeight = bitmap!!.height
        val centerX = point.x + bitmapWidth / 2
        val centerY = point.y + bitmapHeight / 2
        camera.save()
        myMatrix.reset()
        camera.rotateX(degree.toFloat())
        camera.getMatrix(myMatrix)
        camera.restore()
        myMatrix.preTranslate(-centerX.toFloat(), -centerY.toFloat())
        myMatrix.postTranslate(centerX.toFloat(), centerY.toFloat())
        canvas.save()
        canvas.concat(myMatrix)
        canvas.drawBitmap(bitmap, point.x.toFloat(), point.y.toFloat(), paint)
        canvas.restore()
    }

    init {
        bitmap = BitmapFactory.decodeResource(resources, R.drawable.maps)
        val scaledBitmap: Bitmap
        bitmap?.let { it ->
            scaledBitmap = Bitmap.createScaledBitmap(it, it.width * 2, it.height * 2, true)
            it.recycle()
            bitmap = scaledBitmap
        }
        animator.duration = 5000
        animator.interpolator = LinearInterpolator()
        animator.repeatCount = ValueAnimator.INFINITE
    }
}