package com.mredrock.cyxbs.freshman.utils

/**
 * Created by yyfbe on 2019-08-11
 */
object FastClickCheck {
    private val FAST_CLICK_DELAY_TIME = 1000
    private var lastClickTime: Long = 0

    val isFastClick: Boolean
        get() {
            var flag = true
            val currentClickTime = System.currentTimeMillis()
            if (currentClickTime - lastClickTime >= FAST_CLICK_DELAY_TIME) {
                flag = false
            }
            lastClickTime = currentClickTime
            return flag
        }

}
