package com.hencoder.hencoderpracticedraw4.sample

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.hencoder.hencoderpracticedraw4.R

class Sample12CameraRotateFixedView : View {
    var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var bitmap: Bitmap? = null
    var point1 = Point(200, 200)
    var point2 = Point(600, 200)
    var camera = Camera()
    var myMatrix = Matrix()

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val bitmapWidth = bitmap!!.width
        val bitmapHeight = bitmap!!.height
        val center1X = point1.x + bitmapWidth / 2
        val center1Y = point1.y + bitmapHeight / 2
        val center2X = point2.x + bitmapWidth / 2
        val center2Y = point2.y + bitmapHeight / 2

        camera.save()
        myMatrix.reset()
        camera.rotateX(30f)
        camera.getMatrix(myMatrix)
        camera.restore()
        myMatrix.preTranslate(-center1X.toFloat(), -center1Y.toFloat())
        myMatrix.postTranslate(center1X.toFloat(), center1Y.toFloat())
        canvas.save()
        canvas.concat(myMatrix)
        canvas.drawBitmap(bitmap, point1.x.toFloat(), point1.y.toFloat(), paint)
        canvas.restore()

        camera.save()
        myMatrix.reset()
        camera.rotateY(30f)
        camera.getMatrix(myMatrix)
        camera.restore()
        myMatrix.preTranslate(-center2X.toFloat(), -center2Y.toFloat())
        myMatrix.postTranslate(center2X.toFloat(), center2Y.toFloat())
        canvas.save()
        canvas.concat(myMatrix)
        canvas.drawBitmap(bitmap, point2.x.toFloat(), point2.y.toFloat(), paint)
        canvas.restore()
    }

    init {
        bitmap = BitmapFactory.decodeResource(resources, R.drawable.maps)
    }
}