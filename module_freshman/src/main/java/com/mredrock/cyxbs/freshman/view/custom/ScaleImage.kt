package com.mredrock.cyxbs.freshman.view.custom

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ImageView
import com.mredrock.cyxbs.common.utils.LogUtils
import com.mredrock.cyxbs.freshman.R

/**
 * @date 2019-08-08
 * @author Override0330
 * @description 自定义View，可以手指缩放的图片
 */
class ScaleImage : ImageView{
    var bitmap = BitmapFactory.decodeResource(resources,R.drawable.freshman_goto_map_normal)
    val currentMatrix = Matrix()
    val saveMatrix = Matrix()
    var startX = 0F
    var startY = 0F
    var midX = 0F
    var midY = 0F
    var oldDis = 0F
    var newDis = 0F
    var MODE_NOW = -1
    val MODE_DAGE = 0
    val MODE_SCALE = 1
    val MODE_NULL = -1

    init {
//        imageBitmap = bitmap
    }
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun countDis (event:MotionEvent): Float{
        val x = event.getX(0) - event.getX(1)
        val y = event.getY(0) - event.getY(1)
        return Math.sqrt(x*x+y*y+0.0).toFloat()
    }

    //单点拖动
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        LogUtils.d("触控点数","${event?.pointerCount}")
        LogUtils.d("action:","${event?.action}")
        when(event?.action){
            MotionEvent.ACTION_DOWN ->{
//                LogUtils.d("触控点数","${event.pointerCount}")
                saveMatrix.set(currentMatrix)
                startX = event.x
                startY = event.y
                MODE_NOW = MODE_DAGE
                LogUtils.d("","进入拖拽模式")
            }
            MotionEvent.ACTION_POINTER_2_DOWN ->{
//                LogUtils.d("","${event.pointerCount}")
                oldDis = countDis(event)
                LogUtils.d("","进入缩放模式")
                    saveMatrix.set(currentMatrix)
                    midX = event.x
                    midY = event.y
                    MODE_NOW = MODE_SCALE
            }
            MotionEvent.ACTION_MOVE ->{
                currentMatrix.set(saveMatrix)
                if (MODE_NOW == MODE_DAGE){
                    currentMatrix.postTranslate(event.x-startX,event.y-startY)
                }else if(MODE_NOW == MODE_SCALE){
                    newDis = countDis(event)
                    if (newDis>=10F){
                        val scale = newDis/oldDis
                        LogUtils.d("缩放比例","$scale newDis: $newDis oldDis: $oldDis")
                        currentMatrix.postScale(scale,scale,midX,midY)
                    }
                }
            }
            MotionEvent.ACTION_UP ->{
                MODE_NOW = MODE_NULL
            }
            MotionEvent.ACTION_POINTER_UP ->{
                MODE_NOW = MODE_NULL
            }
        }
        imageMatrix = currentMatrix
        return true
    }
}