package com.mredrock.cyxbs.freshman.view.custom.circleindicator

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * Created by yyfbe on 2019-08-06
 */
class CircleIndicator(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : View(context, attrs, defStyleAttr) {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    private var pointX: Float = 0f
    private var pointY: Float = 0f
    private var r: Float = 0f
    val circlePaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val pointPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    override fun onDraw(canvas: Canvas?) {
        pointX = width / 2.toFloat()
        pointY = height / 2.toFloat()
        super.onDraw(canvas)
    }
}
