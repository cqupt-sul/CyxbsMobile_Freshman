package com.mredrock.cyxbs.freshman.view

import android.graphics.Color
import android.graphics.Typeface
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.mredrock.cyxbs.common.ui.BaseViewModelFragment
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.view.adapter.BaseFragmentPagerAdapter
import org.jetbrains.anko.textColor

/**
 * Created by yyfbe on 2019-08-05
 */
abstract class BaseTabLayoutFragment<T : BaseViewModel>(override val viewModelClass: Class<T>) : BaseViewModelFragment<T>() {
//   lateinit var titles: List<String>
//    lateinit var fragments: List<Fragment>
    fun setTabView(position: Int, titles: List<String>, tabLayout: TabLayout) {
        getTabView(position, true, titles, tabLayout)
    }

    fun resetTabView(position: Int, titles: List<String>, tabLayout: TabLayout) {
        getTabView(position, false, titles, tabLayout)
    }

    private fun getTabView(position: Int, selected: Boolean, titles: List<String>,
                           tabLayout: TabLayout) {
        val tab = tabLayout.getTabAt(position)
        val customView = tab?.customView?.findViewById<TextView>(R.id.tv_tab_select)
        customView?.text = titles[position]
        if (selected) {
            customView?.textColor = Color.parseColor("#bf000000")
            customView?.typeface = Typeface.DEFAULT_BOLD
        } else {
            customView?.textColor = Color.parseColor("#99000000")
            customView?.typeface = Typeface.DEFAULT
        }
    }

    open fun initTabLayout(viewPager: ViewPager, tabLayout: TabLayout,
                           titles: List<String>,fragments: List<Fragment>) {
        checkMatch(titles, fragments)
        val fm = this.childFragmentManager
        val dataFragmentPagerAdapter = BaseFragmentPagerAdapter(titles, fragments, fm)
        viewPager.adapter = dataFragmentPagerAdapter

        tabLayout.setupWithViewPager(viewPager)
        for (i in 0 until titles.size) {
            val tab = tabLayout.getTabAt(i)
            tab?.setCustomView(R.layout.freshman_tab_custom_view)
            val textView = tab?.customView?.findViewById<TextView>(R.id.tv_tab_select)
            if (i == 0) {
                textView?.textColor = Color.parseColor("#bf000000")
                textView?.typeface = Typeface.DEFAULT_BOLD
            }
            textView?.text = titles[i]
        }
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
                resetTabView(p0!!.position, titles, tabLayout)
            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                setTabView(p0!!.position, titles, tabLayout)
            }

        })
    }

    private fun checkMatch(titles: List<String>, fragments: List<Fragment>) {
        when (titles.size == fragments.size) {
            false -> {
                throw Exception("fragment和title数量不匹配")
            }
        }
    }
}