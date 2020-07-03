package com.hencoder.hencoderpracticedraw4.practice

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import com.hencoder.hencoderpracticedraw4.R

class Practice14FlipboardView : View {
    var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var bitmap: Bitmap? = null
    var degree = 0f
    //自定义属性动画
    val animator = ObjectAnimator.ofInt(this,"degree",0,180)
    val camera = Camera()

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        animator.start()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        animator.cancel()
    }

    fun setDegree(degree: Int){
        this.degree = degree.toFloat()
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val bitmapWidth = bitmap!!.width
        val bitmapHeight = bitmap!!.height
        val centerX = width/2
        val centerY = height/2
        val x = centerX - bitmapWidth/2
        val y = centerY - bitmapHeight/2

        canvas.save()
        //绘制上部分
        canvas.clipRect(0,0,width,centerY)
        canvas.drawBitmap(bitmap,x.toFloat(),y.toFloat(),paint)
        canvas.restore()
        canvas.save()
        //绘制下部分
        if (degree < 90){
            canvas.clipRect(0,centerY,width,height)
        }else{
            canvas.clipRect(0,0,width,centerY)
        }
        camera.save()
        camera.rotateX(degree)
        canvas.translate(centerX.toFloat(),centerY.toFloat())
        camera.applyToCanvas(canvas)
        canvas.translate(-centerX.toFloat(),-centerY.toFloat())
        camera.restore()
        canvas.drawBitmap(bitmap,x.toFloat(),y.toFloat(),paint)
        canvas.restore()
    }

    init {
        bitmap = BitmapFactory.decodeResource(resources,R.drawable.maps)
        animator.duration = 2500
        animator.interpolator = LinearInterpolator()
        animator.repeatCount = ValueAnimator.INFINITE
        animator.repeatMode = ValueAnimator.REVERSE
    }
}