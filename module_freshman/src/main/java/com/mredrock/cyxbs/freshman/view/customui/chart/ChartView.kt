package com.mredrock.cyxbs.freshman.view.customui.chart

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.mredrock.cyxbs.common.utils.LogUtils
import com.mredrock.cyxbs.freshman.R
import java.text.NumberFormat

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
        const val MAIN_TITLE = "难度系数"
//        const val SIDE_TITLE = "挂科率前三"
    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    //    private val sWidth = getScreenWidth()
//    private val sHeight = getScreenHeight()
    private var axisY = 0f
    private var axisX = 0f
    //原点位置
    private var pointX = 0f
    private var pointY = 0f
    //view起点在屏幕的Y轴高度
//    private var startY = 0.0f
    private val arrowPathX = Path()
    private val arrowPathY = Path()
    private val sideTitlePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val axisPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val subjectNamePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val columnPaint1 = Paint(Paint.ANTI_ALIAS_FLAG)
    private val columnPaint2 = Paint(Paint.ANTI_ALIAS_FLAG)
    private val columnPaint3 = Paint(Paint.ANTI_ALIAS_FLAG)
    private val ratioTextPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val titlePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    var subjectRatio1: Float = 0.0f
    var subjectRatio2: Float = 0.0f
    var subjectRatio3: Float = 0.0f
    var subjectName1: String = ""
    var subjectName2: String = ""
    var subjectName3: String = ""
    var currentRatio: Float = 1f
    var matchScreenRatio = 1f
    var titleDistance = 0f
    private val iconPath = Path()
    private val ratioPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        titlePaint.color = DEFAULT_TITLE_COLOR
        ratioTextPaint.color = DEFAULT_RATIO_TEXT_COLOR
        ratioTextPaint.strokeWidth = 25f * matchScreenRatio
        ratioTextPaint.textAlign = Paint.Align.CENTER
        ratioTextPaint.textSize = resources.getDimensionPixelOffset(R.dimen.freshman_data_disclosure_custom_view_ratio_size).toFloat()
        titlePaint.textSize = resources.getDimensionPixelOffset(R.dimen.freshman_data_disclosure_custom_view_title_size).toFloat()
        titleDistance = resources.getDimensionPixelOffset(R.dimen.freshman_data_disclosure_custom_view_title_distance_size).toFloat()


        axisPaint.color = DEFAULT_COMMON_COLOR
        axisPaint.strokeWidth = resources.getDimensionPixelOffset(R.dimen.freshman_data_disclosure_custom_view_arrow_size).toFloat()

        axisPaint.style = Paint.Style.STROKE

        sideTitlePaint.color = DEFAULT_COMMON_COLOR
        sideTitlePaint.strokeWidth = 13f * matchScreenRatio
        sideTitlePaint.textSize = 40f * matchScreenRatio

        subjectNamePaint.color = DEFAULT_COMMON_COLOR
        subjectNamePaint.strokeWidth = 10f * matchScreenRatio
        subjectNamePaint.textAlign = Paint.Align.CENTER
        subjectNamePaint.textSize = resources.getDimensionPixelOffset(R.dimen.freshman_data_disclosure_custom_view_text_size).toFloat()

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
//        startY = sHeight - height
//        pointY = (0.56 * sHeight - startY).toFloat()
        axisY = height * 0.56.toFloat()
        axisX = width * 0.74.toFloat()
        pointY = (0.81 * height).toFloat()
        pointX = (width * 0.16).toFloat()
        //画坐标轴
        canvas?.drawLine(pointX, pointY, pointX, pointY - axisY, axisPaint)
        canvas?.drawLine(pointX, pointY, pointX + axisX, pointY, axisPaint)
        //画箭头
        //X
        arrowPathY.moveTo(pointX - 15f * matchScreenRatio,
                pointY - axisY + 15f * matchScreenRatio)
        arrowPathY.lineTo(pointX, pointY - axisY)
        arrowPathY.lineTo(pointX + 15f * matchScreenRatio,
                pointY - axisY + 15f * matchScreenRatio)
        canvas?.drawPath(arrowPathY, axisPaint)
        //Y
        arrowPathX.moveTo(pointX + axisX - 15f * matchScreenRatio,
                pointY - 15f * matchScreenRatio)
        arrowPathX.lineTo(pointX + axisX, pointY)
        arrowPathX.lineTo(pointX + axisX - 15f * matchScreenRatio,
                pointY + 15f * matchScreenRatio)
        canvas?.drawPath(arrowPathX, axisPaint)
        //画柱状图
        drawSubject(subjectName1, 1, subjectRatio1, canvas, columnPaint1)
        drawSubject(subjectName2, 2, subjectRatio2, canvas, columnPaint2)
        drawSubject(subjectName3, 3, subjectRatio3, canvas, columnPaint3)
        //画主标题
//        canvas?.drawText(MAIN_TITLE, (0.12 * sWidth).toFloat(), (0.26 * sHeight - startY).toFloat(), titlePaint)
        canvas?.drawText(MAIN_TITLE, pointX,
                pointY - axisY + 15f * matchScreenRatio - titleDistance, titlePaint)
        //画侧标题
//        for (i in 0 until SIDE_TITLE.length) {
//            canvas?.drawText(SIDE_TITLE[i].toString(), (pointX - getTextHeight(sideTitlePaint) * 1.5f), pointY - axisY + (i + 1) * getTextHeight(sideTitlePaint),
//                    sideTitlePaint)
//        }
        //画y轴的线
        for (i in 0 until 4) {
            canvas?.drawLine(pointX, (pointY - (i + 1) * 0.09 * height).toFloat(),
                    pointX - 18f * matchScreenRatio,
                    (pointY - (i + 1) * 0.09 * height).toFloat(), axisPaint)
        }
    }

    private fun drawSubject(name: String, position: Int, ratio: Float,
                            canvas: Canvas?, paint: Paint) {
        val left = width * (0.26 + 0.2 * (position - 1)).toFloat()
        val right = width * (0.36 + 0.2 * (position - 1)).toFloat()
        canvas?.drawRect(left, pointY - currentRatio * ratio * axisY,
                right, pointY, paint)
        canvas?.drawRect(left, pointY - currentRatio * ratio * axisY,
                right, pointY, ratioPaint)
        val nf = NumberFormat.getNumberInstance()
        nf.maximumFractionDigits = 2
        canvas?.drawText(
                (nf.format(ratio * currentRatio)).toString(),
//                (ratio * currentRatio * 100).toString(),
                (left + right) / 2 - 5f,
                pointY - currentRatio * ratio * axisY - getTextHeight(ratioTextPaint) / 2,
                ratioTextPaint)
        canvas?.drawText(
                name,
                (left + right) / 2,
                pointY + getTextHeight(subjectNamePaint),
                subjectNamePaint
        )
        //右上的角
        iconPath.reset()
        iconPath.moveTo(right - 8f, pointY - currentRatio * ratio * axisY + 28)
        iconPath.lineTo(right - 8f, pointY - currentRatio * ratio * axisY + 8)
        iconPath.lineTo(right - 28f, pointY - currentRatio * ratio * axisY + 8)
        canvas?.drawPath(iconPath,axisPaint)

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)

    }

    private fun getTextHeight(paint: Paint): Float {
        val fontMetrics = paint.fontMetrics
        // 计算文字高度
        return fontMetrics.bottom - fontMetrics.top
    }

    fun doAnim(animator: ValueAnimator) {
        animator.addUpdateListener { animation ->
            currentRatio = animation.animatedValue as Float
            invalidate()
        }

    }
}