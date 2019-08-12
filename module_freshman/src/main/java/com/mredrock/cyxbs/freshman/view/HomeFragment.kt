package com.mredrock.cyxbs.freshman.view

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mredrock.cyxbs.common.ui.BaseViewModelFragment
import com.mredrock.cyxbs.common.utils.LogUtils
import com.mredrock.cyxbs.freshman.BR
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.databinding.FreshmanRecycleItemHomeBinding
import com.mredrock.cyxbs.freshman.model.InitDBEvent
import com.mredrock.cyxbs.freshman.model.SetToolsBarTitle
import com.mredrock.cyxbs.freshman.model.item.HomeItem
import com.mredrock.cyxbs.freshman.view.adapter.BaseRecyclerViewAdapter
import com.mredrock.cyxbs.freshman.view.talk.TalkDialogFragment
import com.mredrock.cyxbs.freshman.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.freshman_acitvity_home.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * @date 2019-08-03
 * @author Override0330
 * @description
 */

class HomeFragment : BaseViewModelFragment<HomeViewModel>() {
    override val viewModelClass: Class<HomeViewModel>
        get() = HomeViewModel::class.java

    val adapter = BaseRecyclerViewAdapter<FreshmanRecycleItemHomeBinding,HomeItem >(R.layout.freshman_recycle_item_home, BR.item, null)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.freshman_acitvity_home,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        EventBus.getDefault().post(SetToolsBarTitle(""))
        val animation1 = AnimationUtils.loadAnimation(this.context, R.anim.freshman_rotate_clockwise_001)
        animation1.interpolator = LinearInterpolator()
        iv_home_screw_left.startAnimation(animation1)

        val animation2 = AnimationUtils.loadAnimation(this.context, R.anim.freshman_rotate_clockwise_001)
        animation2.interpolator = LinearInterpolator()
        iv_home_shadow_left.startAnimation(animation2)

        val animation3 = AnimationUtils.loadAnimation(this.context, R.anim.freshman_rotate_clockwise_001)
        animation3.interpolator = LinearInterpolator()
        iv_home_screw_right.startAnimation(animation3)

        val animation4 = AnimationUtils.loadAnimation(this.context, R.anim.freshman_rotate_clockwise_001)
        animation4.interpolator = LinearInterpolator()
        iv_home_shadow_right.startAnimation(animation4)

        //设置监听
        adapter.onItemOnClickListener = object : BaseRecyclerViewAdapter.OnItemOnClickListener {
            override fun onItemClick(itemView: View, position: Int) {
                //这里设定点击事件
                // position为-1时为非法
                when (position) {
                    0 -> {
                        showTalk()
                    }
                    1 -> {
                        findNavController().navigate(R.id.freshman_action_freshman_home_fragment_to_freshman_gotocquptfragment)
                    }
                    4 -> {
                        findNavController().navigate(R.id.freshman_action_freshman_home_fragment_to_freshman_onlinediscussfragment)
                    }
                    3 -> {
                        findNavController().navigate(R.id.freshman_action_freshman_home_fragment_to_freshman_campusguidefragment)
                    }
                    5 -> {
                        findNavController().navigate(R.id.freshman_action_freshman_home_fragment_to_freshman_more_function_fragment)
                    }
                }
            }
        }
        rv_home.layoutManager = LinearLayoutManager(this.context)
        rv_home.adapter = adapter
        viewModel.getShowList(viewLifecycleOwner).observe {
            if (adapter.itemCount==0){
                LogUtils.d("LiveData","Recyclerview初始化回调"+ it.toString())
                it?.let {
                    it1 -> adapter.submitShowList(it1)
                }
            }
        }
    }



    private fun showTalk(){
        val talkDialogFragment = TalkDialogFragment()
        talkDialogFragment.show(fragmentManager,"first")
        val backgroundAnimation1 = ObjectAnimator.ofFloat(talkDialogFragment,"scaleX",0F,1F)
        backgroundAnimation1.duration = 1250
        backgroundAnimation1.addUpdateListener {
            //记得判空
            talkDialogFragment.unFold()
        }
        backgroundAnimation1.interpolator = AccelerateDecelerateInterpolator()
//        val backgroundAnimation2 = ObjectAnimator.ofFloat(talkDialogFragment,"scaleY",0.1F,1F)
//        backgroundAnimation2.duration = 1500
//        backgroundAnimation2.addUpdateListener {
//            //记得判空
//            talkDialogFragment.unFold2()
//        }
//        backgroundAnimation2.interpolator = AccelerateDecelerateInterpolator()
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
        animatorSet.playSequentially(backgroundAnimation1,letterAnimation,buttonAnimation)
        animatorSet.start()
    }

    override fun onResume() {
        super.onResume()
        EventBus.getDefault().post("")
    }
}
