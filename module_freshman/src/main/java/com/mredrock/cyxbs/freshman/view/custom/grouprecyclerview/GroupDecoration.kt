package com.mredrock.cyxbs.freshman.view.custom.grouprecyclerview

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mredrock.cyxbs.common.utils.LogUtils
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.model.bean.RequirementData

/**
 * Created by yyfbe on 2019-08-08
 */

class GroupDecoration(val data: ArrayList<RequirementData>, context: Context) :
        RecyclerView.ItemDecoration() {

    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    @SuppressLint("UseSparseArrays")
    private val mTitleMap = HashMap<Int, String>()
    private var res: Resources = context.resources
    private var topGap = res.getDimension(R.dimen.freshman_school_require_recycler_view_pad_top)
    private var textStart = res.getDimension(R.dimen.freshman_school_require_recycler_view_text_start)

    init {
        for (i in data)
            LogUtils.d("data", i.requirementName + i.requirementTitle)
//        sortTitle(data)
        divideTitle(data)
        textPaint.textSize = res.getDimension(R.dimen.freshman_school_require_recycler_view_title)
    }

    override fun getItemOffsets(outRect: Rect, view: View,
                                parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val itemPosition = parent.getChildItemId(view)
        //如果有这个下标，就画分离组
        when (mTitleMap.containsKey(parent.getChildAdapterPosition(view))) {
            true -> {
                outRect.top = topGap.toInt()
            }
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val left = parent.paddingLeft
//        val right=parent.width-parent.paddingRight
        var childView: View
//        var top:Float
        var bottom: Float
        for (i in 0 until parent.childCount) {
            childView = parent.getChildAt(i)
            val itemPosition= parent.getChildAdapterPosition(childView)
//            top=childView.top-topGap
            bottom = childView.top.toFloat()
            when (mTitleMap.containsKey(itemPosition)) {
//                true -> mTitleMap[i]?.let { c.drawText(it, textStart, bottom - 20f, textPaint) }
                true -> {
                    c.drawText(mTitleMap.getValue(itemPosition), textStart, bottom - 20f, textPaint)
                }
            }

        }
    }

    private fun divideTitle(requirementDataList: ArrayList<RequirementData>) {

        for (i in 0 until requirementDataList.size) {
            if (i == 0)
            //判断是不是第一个元素，是就加入title
                mTitleMap[i] = requirementDataList[i].requirementTitle
            //判断是否已经有这种元素
            else {
                if (!mTitleMap.containsValue(requirementDataList[i].requirementTitle)) {
                    mTitleMap[i] = requirementDataList[i].requirementTitle
                }
            }
        }
    }

    private fun sortTitle(requirementDataList: ArrayList<RequirementData>) {
//        for (i in 0 until requirementDataList.size) {
//            if (requirementDataList[i].requirementTitle != "备忘录") {
//            }
//        }
        requirementDataList.sortBy { it.requirementTitle }
    }
}
