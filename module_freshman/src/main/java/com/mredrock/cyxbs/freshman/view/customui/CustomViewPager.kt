package com.mredrock.cyxbs.freshman.view.customui

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

/**
 * Created by yyfbe on 2019-08-06
 */
class CustomViewPager(context: Context, attrs: AttributeSet?) : ViewPager(context, attrs) {
    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
            return false
    }
}