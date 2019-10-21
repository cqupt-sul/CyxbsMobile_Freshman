package com.mredrock.cyxbs.freshman.repository

/**
 * Created by yyfbe on 2019-08-12
 */
class RequireCheckRepository private constructor() {
        init {
            upDate()
        }


    //单例
        companion object {
            private var instant: RequireCheckRepository? = null
            @Synchronized
            fun getInstant(): RequireCheckRepository {
                if (instant == null) {
                    instant = RequireCheckRepository()
                }
                return instant!!
            }
        }

    private fun upDate() {

    }

    }