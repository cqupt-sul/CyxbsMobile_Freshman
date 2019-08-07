package com.mredrock.cyxbs.freshman.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class BaseFragmentPagerAdapter(private val titles: List<String>,
                               private val fragments: List<Fragment>,
                               fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return titles.size
    }

//    override fun getPageTitle(position: Int): CharSequence? {
//        return titles[position]
//    }
}