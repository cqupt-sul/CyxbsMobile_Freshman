package com.mredrock.cyxbs.freshman.view.customui.circle

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.mredrock.cyxbs.common.utils.LogUtils
import com.mredrock.cyxbs.freshman.R
import kotlin.math.PI

 /**
 * @date 2019-08-02
 * @author Override0330
 * @description 原始信息包含一个list，list里有需要展示的每个分饼图的信息，分饼图信息应该含有，text，颜色，所占百分比
  */
class CircleView : View {
    private val paintFill = Paint()
    private val paintStroke = Paint()
    private val paintNumber = Paint()
    var totalAngle = 0F
    var nowAngle = 0F
    private var showList : ArrayList<CircleData>? = null

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onCreateDrawableState(extraSpace: Int): IntArray {
        paintFill.style = Paint.Style.FILL
        paintFill.isAntiAlias = true

        paintStroke.style = Paint.Style.STROKE
        paintStroke.isAntiAlias = true
        paintStroke.color = resources.getColor(R.color.freshman_activity_data_revealed_edge_color)
        paintStroke.strokeWidth = 1F

        paintNumber.style = Paint.Style.FILL
        paintNumber.isAntiAlias = true
        paintNumber.color = Color.WHITE
        paintNumber.textSize = 30F
        return super.onCreateDrawableState(extraSpace)
    }

    fun init(showList:ArrayList<CircleData>?){
        this.showList = showList
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.translate(width/2F,height/2F)
        val r = Math.min(width,height)/2F
        val rect = RectF(-r,-r,r,r)
        if (showList != null){
            for (i in 0 until showList!!.size) {
                val showData = showList?.get(i)
                paintFill.color = showData!!.color
                if (nowAngle >= showData.startAngle&&nowAngle<=(showData.scrollAngle+showData.startAngle)){
                    canvas?.drawArc(rect,showData.startAngle,nowAngle-showData.startAngle,true,paintFill)
                    canvas?.drawArc(rect,showData.startAngle,nowAngle-showData.startAngle,true,paintStroke)
                    break
                }else if (nowAngle >= showData.startAngle&&nowAngle>=(showData.scrollAngle+showData.startAngle)){
                    canvas?.drawArc(rect,showData.startAngle,showData.scrollAngle,true,paintFill)
                    canvas?.drawArc(rect,showData.startAngle,showData.scrollAngle,true,paintStroke)

                }
            }
            for (i in 0 until showList!!.size){
                with(showList!![i]){
                    val textAngle = (startAngle*2+scrollAngle)/2
                    val textWidth = paintNumber.measureText(text)
                    paintNumber.alpha = ((nowAngle/360F+ showList!![0].startAngle)*255).toInt()
                    println((nowAngle/totalAngle).toInt()*255)
                    val angleSin = (Math.sqrt(Math.pow(textWidth/2.0, 2.0)+Math.pow(paintNumber.textSize*1.5,2.0))/2)/r
                    when (textAngle) {
                        in 0.0..90.0 -> {
                            //字体在右下方
                            canvas?.drawText(text,
                                    (Math.cos(textAngle*2*PI/360)*r*0.7-textWidth/2).toFloat(),
                                    (Math.sin(textAngle*2*PI/360)*r*0.7).toFloat(),
                                    paintNumber)
                        }
                        in 90.0..180.0 -> {
                            //字体在左下方
                            canvas?.drawText(text,
                                    ((Math.cos(textAngle*2*PI/360)*r*0.7-textWidth/2).toFloat()),
                                    ((Math.sin(textAngle*2*PI/360)*r*0.7).toFloat()),
                                    paintNumber)
                        }
                        in 180.0..270.0 ->{
                            //字体在左上方
                            canvas?.drawText(text,
                                    (Math.cos(textAngle*2*PI/360)*r*0.5-textWidth/2).toFloat(),
                                    (Math.sin(textAngle*2*PI/360)*r*0.5).toFloat(),
                                    paintNumber)
                        }
                        else ->{
                            //字体在右上方
                            canvas?.drawText(text,
                                    (Math.cos(textAngle*2*PI/360)*r*0.7-textWidth/2).toFloat(),
                                    (Math.sin(textAngle*2*PI/360)*r*0.7).toFloat(),
                                    paintNumber)
                        }
                    }
                }
            }
        }

    }
}