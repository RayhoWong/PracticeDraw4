package com.hencoder.hencoderpracticedraw4.sample

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.hencoder.hencoderpracticedraw4.R

class Sample06SkewView : View {
    var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var bitmap: Bitmap? = null
    var point1 = Point(200, 200)
    var point2 = Point(600, 200)

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.save()
        canvas.skew(0f, 0.5f)
        canvas.drawBitmap(bitmap, point1.x.toFloat(), point1.y.toFloat(), paint)
        canvas.restore()
        canvas.save()
        canvas.skew(-0.5f, 0f)
        canvas.drawBitmap(bitmap, point2.x.toFloat(), point2.y.toFloat(), paint)
        canvas.restore()
    }

    init {
        bitmap = BitmapFactory.decodeResource(resources, R.drawable.maps)
    }
}