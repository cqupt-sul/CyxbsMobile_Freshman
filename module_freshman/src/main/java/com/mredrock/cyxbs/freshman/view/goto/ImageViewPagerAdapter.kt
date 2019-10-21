package com.mredrock.cyxbs.freshman.view.goto

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter

/**
 * @date 2019-08-08
 * @author Override0330
 * @description
 */
class ImageViewPagerAdapter(private val imageUrl:ArrayList<String>) :PagerAdapter(){
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return imageUrl.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        return 1
    }
}