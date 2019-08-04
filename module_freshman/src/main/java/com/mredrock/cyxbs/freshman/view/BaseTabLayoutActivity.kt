package com.mredrock.cyxbs.freshman.view

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.mredrock.cyxbs.common.ui.BaseActivity
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.view.adapter.BaseFragmentPagerAdapter
import kotlinx.android.synthetic.main.freshman_activity_data_disclosure.*
import org.jetbrains.anko.textColor
import java.io.IOException

@SuppressLint("Registered")
abstract class BaseTabLayoutActivity : BaseActivity() {
    override val isFragmentActivity: Boolean
        get() = true

    abstract val titles: List<String>
    abstract val fragments: List<Fragment>
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
            customView?.textColor = Color.parseColor("#4b72ff")
            customView?.typeface = Typeface.DEFAULT_BOLD
        } else {
            customView?.textColor = Color.parseColor("#cc000000")
            customView?.typeface = Typeface.DEFAULT
        }
    }

    open fun initTabLayout(viewPager: ViewPager, tabLayout: TabLayout) {
        checkMatch(titles, fragments)
        val fm = supportFragmentManager
        val dataFragmentPagerAdapter = BaseFragmentPagerAdapter(titles, fragments, fm)
        viewPager.adapter = dataFragmentPagerAdapter

        tabLayout.setupWithViewPager(viewPager)
        for (i in 0 until titles.size) {
            val tab = tabLayout.getTabAt(i)
            tab?.setCustomView(R.layout.freshman_tab_custom_view)
            val textView = tab?.customView?.findViewById<TextView>(R.id.tv_tab_select)
            if (i == 0) {
                textView?.textColor = Color.parseColor("#4b72ff")
                textView?.typeface = Typeface.DEFAULT_BOLD
            }
            textView?.text = titles[i]
        }
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
                resetTabView(p0!!.position, titles, tabLayout)
                println("${p0.position}没被点击")
            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                setTabView(p0!!.position, titles, tabLayout)
                println("${p0.position}被点击")
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