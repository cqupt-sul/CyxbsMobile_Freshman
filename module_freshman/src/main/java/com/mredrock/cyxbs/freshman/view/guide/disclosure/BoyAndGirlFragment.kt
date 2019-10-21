package com.mredrock.cyxbs.freshman.view.guide.disclosure

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.mredrock.cyxbs.common.ui.BaseViewModelFragment
import com.mredrock.cyxbs.common.utils.LogUtils
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.databinding.FreshmanFragmentNannvbiliBinding
import com.mredrock.cyxbs.freshman.viewmodel.disclosure.BoyAndGirlViewModel
import kotlinx.android.synthetic.main.freshman_fragment_nannvbili.*

class BoyAndGirlFragment : BaseViewModelFragment<BoyAndGirlViewModel>() {

    private lateinit var dataBinding: FreshmanFragmentNannvbiliBinding
    private lateinit var animation: ObjectAnimator

    companion object{
        fun getBoyAndGirlFragment(school:String): Fragment {
            val args = Bundle()
            args.putString("school", school)
            val fragment = BoyAndGirlFragment()
            fragment.arguments = args
            return fragment
        }
    }
    override val viewModelClass = BoyAndGirlViewModel::class.java
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.freshman_fragment_nannvbili, container, false)
        //fragment需要通过dataBinding对象来获得view，否则会绑定失败
        return dataBinding.root
    }
    override fun onStart() {
        super.onStart()
        val school = arguments?.getString("school")
        LogUtils.d("学院参数","$school")
        if (school != null){
            val title = school+"男女比例："
            tv_data_revealed_title.text = title
            viewModel.getShowList(viewLifecycleOwner,school).observe{
                if (it!=null){
                    LogUtils.d("动画初始化","${it[0].startAngle}")
                    circle_view_test.init(it)
                    animation = ObjectAnimator.ofFloat(circle_view_test, "nowAngle",
                            it[0].startAngle,
                            it[0].startAngle + 360)
                    animation.duration = 2000
                    animation.addUpdateListener {
                        //记得判空
                        circle_view_test?.invalidate()
                    }
                    animation.interpolator = AccelerateDecelerateInterpolator()
                    animation.start()
                }
            }
        }
    }
}

