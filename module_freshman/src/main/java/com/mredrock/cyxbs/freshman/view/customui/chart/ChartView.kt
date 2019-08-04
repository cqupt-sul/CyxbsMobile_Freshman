package com.mredrock.cyxbs.freshman.view.customui.chart

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class ChartView(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : View(context, attrs, defStyleAttr) {
    companion object {
        @JvmStatic
        val DEFAULT_TITLE_COLOR = Color.parseColor("#bf000000")
        @JvmStatic
        val DEFAULT_COMMON_COLOR = Color.parseColor("#cc5c7ffc")
        @JvmStatic
        val DEFAULT_RATIO_COLOR = Color.parseColor("#809dff")
        @JvmStatic
        val DEFAULT_SUBJECT1_COLOR = Color.parseColor("#698aff")
        @JvmStatic
        val DEFAULT_SUBJECT2_COLOR = Color.parseColor("#ff95c3")
        @JvmStatic
        val DEFAULT_SUBJECT3_COLOR = Color.parseColor("#71d5ff")
        @JvmStatic
        val DEFAULT_RATIO_TEXT_COLOR = Color.parseColor("#cc888888")
        const val MAIN_TITLE = "2018-2019部分学科挂科率"
        const val SIDE_TITLE = "挂科率前三"
    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    private val sWidth = getScreenWidth()
    private val sHeight = getScreenHeight()
    private val axisY = sHeight * 0.26.toFloat()
    private val axisX = sWidth * 0.69.toFloat()
    //原点位置
    private val pointX = sWidth * 0.19.toFloat()
    private var pointY = 0.0f
    //view起点在屏幕的Y轴高度
    private var startY = 0.0f
    private val arrowPathX = Path()
    private val arrowPathY = Path()
    private val titlePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val sideTitlePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val axisPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val subjectNamePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val columnPaint1 = Paint(Paint.ANTI_ALIAS_FLAG)
    private val columnPaint2 = Paint(Paint.ANTI_ALIAS_FLAG)
    private val columnPaint3 = Paint(Paint.ANTI_ALIAS_FLAG)
    private val ratioTextPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    var subjectRatio1: Float = 0.0f
    var subjectRatio2: Float = 0.0f
    var subjectRatio3: Float = 0.0f
    var subjectName1: String = ""
    var subjectName2: String = ""
    var subjectName3: String = ""
    var currentRatio: Float = 1f
    var matchScreenRatio = sHeight / 2712
    private val ratioPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        ratioTextPaint.color = DEFAULT_RATIO_TEXT_COLOR
        ratioTextPaint.strokeWidth = 25f * matchScreenRatio
        ratioTextPaint.textAlign = Paint.Align.CENTER
        ratioTextPaint.textSize = 50f * matchScreenRatio

        titlePaint.color = DEFAULT_TITLE_COLOR
        titlePaint.strokeWidth = 15f * matchScreenRatio
        titlePaint.textSize = 70f * matchScreenRatio

        axisPaint.color = DEFAULT_COMMON_COLOR
        axisPaint.strokeWidth = 5f * matchScreenRatio
        axisPaint.style = Paint.Style.STROKE

        sideTitlePaint.color = DEFAULT_COMMON_COLOR
        sideTitlePaint.strokeWidth = 13f * matchScreenRatio
        sideTitlePaint.textSize = 50f * matchScreenRatio

        subjectNamePaint.color = DEFAULT_COMMON_COLOR
        subjectNamePaint.strokeWidth = 20f * matchScreenRatio
        subjectNamePaint.textAlign = Paint.Align.CENTER
        subjectNamePaint.textSize = 60f * matchScreenRatio

        columnPaint1.color = DEFAULT_SUBJECT1_COLOR
        columnPaint2.color = DEFAULT_SUBJECT2_COLOR
        columnPaint3.color = DEFAULT_SUBJECT3_COLOR


        ratioPaint.color = DEFAULT_RATIO_COLOR
        ratioPaint.strokeWidth = 4f * matchScreenRatio
        ratioPaint.style = Paint.Style.STROKE

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //得到startY位置
        startY = sHeight - height
        println("$sHeight>>$sWidth")
        pointY = (0.56 * sHeight - startY).toFloat()
        //画坐标轴
        canvas?.drawLine(pointX, pointY, pointX, pointY - axisY, axisPaint)
        canvas?.drawLine(pointX, pointY, pointX + axisX, pointY, axisPaint)
        //画箭头
        //X
        arrowPathY.moveTo(pointX - 15f * matchScreenRatio, pointY - axisY + 15f * matchScreenRatio)
        arrowPathY.lineTo(pointX, pointY - axisY)
        arrowPathY.lineTo(pointX + 15f * matchScreenRatio, pointY - axisY + 15f * matchScreenRatio)
        canvas?.drawPath(arrowPathY, axisPaint)
        //Y
        arrowPathX.moveTo(pointX + axisX - 15f * matchScreenRatio, pointY - 15f * matchScreenRatio)
        arrowPathX.lineTo(pointX + axisX, pointY)
        arrowPathX.lineTo(pointX + axisX - 15f * matchScreenRatio, pointY + 15f * matchScreenRatio)
        canvas?.drawPath(arrowPathX, axisPaint)
        //画柱状图
        drawSubject(subjectName1, 1, subjectRatio1, canvas, columnPaint1)
        drawSubject(subjectName2, 2, subjectRatio2, canvas, columnPaint2)
        drawSubject(subjectName3, 3, subjectRatio3, canvas, columnPaint3)
        //画主标题
        canvas?.drawText(MAIN_TITLE, (0.12 * sWidth).toFloat(), (0.26 * sHeight - startY).toFloat(), titlePaint)
        //画侧标题
        for (i in 0 until SIDE_TITLE.length) {
            canvas?.drawText(SIDE_TITLE[i].toString(), (0.12 * sWidth).toFloat(), pointY - axisY + (i + 1) * getTextHeight(sideTitlePaint),
                    sideTitlePaint)
        }
        //画y轴的线
        for (i in 0 until 4) {
            canvas?.drawLine(pointX, (pointY - (i + 1) * 0.04 * sHeight).toFloat(), pointX - 18f * matchScreenRatio,
                    (pointY - (i + 1) * 0.04 * sHeight).toFloat(), axisPaint)
        }
    }

    private fun getScreenWidth(): Float {
        val resources = this.resources
        val dm = resources.displayMetrics
        return dm.widthPixels.toFloat()
    }

    private fun getScreenHeight(): Float {
        val resources = this.resources
        val dm = resources.displayMetrics
        return dm.heightPixels.toFloat()
    }

    private fun drawSubject(name: String, position: Int, ratio: Float, canvas: Canvas?, paint: Paint) {
        val left = sWidth * (0.25 + 0.2 * (position - 1)).toFloat()
        val right = sWidth * (0.34 + 0.2 * (position - 1)).toFloat()
        canvas?.drawRect(left, pointY - currentRatio * ratio * axisY, right, pointY, paint)
        canvas?.drawRect(left, pointY - currentRatio * ratio * axisY, right, pointY, ratioPaint)
        canvas?.drawText(
                (Math.round(ratio * currentRatio * 100 * 100) / 100).toString() + "%",
                (left + right) / 2,
                pointY - currentRatio * ratio * axisY - getTextHeight(ratioTextPaint) / 2,
                ratioTextPaint)
        canvas?.drawText(
                name,
                (left + right) / 2,
                pointY + getTextHeight(subjectNamePaint),
                subjectNamePaint
        )

    }

    private fun getTextHeight(paint: Paint): Float {
        val fontMetrics = paint.fontMetrics
        // 计算文字高度
        return fontMetrics.bottom - fontMetrics.top

    }
    fun doAnim(animator: ValueAnimator){
        animator.addUpdateListener { animation->
            currentRatio= animation.animatedValue as Float
            invalidate()
        }

    }
}