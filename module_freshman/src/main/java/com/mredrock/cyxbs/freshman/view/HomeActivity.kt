package com.mredrock.cyxbs.freshman.view

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mredrock.cyxbs.common.BaseApp
import com.mredrock.cyxbs.common.ui.BaseViewModelActivity
import com.mredrock.cyxbs.common.utils.LogUtils
import com.mredrock.cyxbs.freshman.BR
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.databinding.FreshmanRecycleItemHomeBinding
import com.mredrock.cyxbs.freshman.view.adapter.BaseRecyclerViewAdapter
import com.mredrock.cyxbs.freshman.model.item.HomeItem
import com.mredrock.cyxbs.freshman.view.talk.TalkDialogFragment
import com.mredrock.cyxbs.freshman.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.freshman_acitvity_home.*

/**
 * @date 2019-08-03
 * @author Override0330
 * @description
 */

class HomeActivity : BaseViewModelActivity<HomeViewModel>() {
    override val isFragmentActivity: Boolean
        get() = false
    override val viewModelClass: Class<HomeViewModel>
        get() = HomeViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.freshman_acitvity_home)

        val animation1 = AnimationUtils.loadAnimation(this, R.anim.freshman_rotate_clockwise_001)
        animation1.interpolator = LinearInterpolator()
        iv_home_screw_left.startAnimation(animation1)

        val animation2 = AnimationUtils.loadAnimation(this, R.anim.freshman_rotate_clockwise_001)
        animation2.interpolator = LinearInterpolator()
        iv_home_shadow_left.startAnimation(animation2)

        val animation3 = AnimationUtils.loadAnimation(this, R.anim.freshman_rotate_clockwise_001)
        animation3.interpolator = LinearInterpolator()
        iv_home_screw_right.startAnimation(animation3)

        val animation4 = AnimationUtils.loadAnimation(this, R.anim.freshman_rotate_clockwise_001)
        animation4.interpolator = LinearInterpolator()
        iv_home_shadow_right.startAnimation(animation4)

        viewModel.showList.observe {
            val adapter = BaseRecyclerViewAdapter<FreshmanRecycleItemHomeBinding, HomeItem>(R.layout.freshman_recycle_item_home, BR.item, it)

            //设置监听
            adapter.onItemOnClickListener = object : BaseRecyclerViewAdapter.OnItemOnClickListener {
                override fun onItemClick(itemView: View, position: Int) {
                    //这里设定点击事件
                    // position为-1时为非法
                    LogUtils.d("主页点击", "点击了 $position 号标签")
                    Toast.makeText(BaseApp.context, "点击了 $position 号标签", Toast.LENGTH_SHORT).show()
                    when (position) {
                        0 -> {
                            //跳转
                            showTalk()
                        }
                    }
                }
            }
            rv_home.layoutManager = LinearLayoutManager(this)
            rv_home.adapter = adapter
        }
//        viewModel.init()
    }
    private fun showTalk(){
        val talkDialogFragment = TalkDialogFragment()
        talkDialogFragment.show(supportFragmentManager,"first")
        val backgroundAnimation = ObjectAnimator.ofFloat(talkDialogFragment,"scale",0F,1F)
        backgroundAnimation.duration = 2000
        backgroundAnimation.addUpdateListener {
            //记得判空
            talkDialogFragment.unFold()
        }
        backgroundAnimation.interpolator = AccelerateDecelerateInterpolator()
        val letterAnimation = ObjectAnimator.ofFloat(talkDialogFragment,"letterAlpha",0F,1F)
        letterAnimation.duration = 1000
        letterAnimation.addUpdateListener {
            talkDialogFragment.showLetter()
        }
        letterAnimation.interpolator = AccelerateDecelerateInterpolator()

        val buttonAnimation = ObjectAnimator.ofFloat(talkDialogFragment,"buttonAlpha",0F,1F)
        buttonAnimation.duration = 1000
        buttonAnimation.addUpdateListener {
            talkDialogFragment.showButton()
        }
        buttonAnimation.interpolator = AccelerateDecelerateInterpolator()
        val animatorSet = AnimatorSet()
        animatorSet.playSequentially(backgroundAnimation,letterAnimation,buttonAnimation)
        animatorSet.start()
    }
}
