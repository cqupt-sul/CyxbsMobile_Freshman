package com.mredrock.cyxbs.freshman.view.nannvbili

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mredrock.cyxbs.common.ui.BaseViewModelFragment
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.databinding.FreshmanFragmentNannvbiliBinding
import com.mredrock.cyxbs.freshman.model.CircleData
import com.mredrock.cyxbs.freshman.viewmodel.NannvbiliViewModel
import kotlinx.android.synthetic.main.freshman_fragment_nannvbili.*

class NannvbiliFragment : BaseViewModelFragment<NannvbiliViewModel>(){
    private lateinit var dataBinding: FreshmanFragmentNannvbiliBinding
    override val viewModelClass = NannvbiliViewModel::class.java
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewModelFactory = getViewModelFactory()
        viewModel = if (viewModelFactory != null) {
            ViewModelProviders.of(this, viewModelFactory).get(viewModelClass)
        } else {
            ViewModelProviders.of(this).get(viewModelClass)
        }
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.freshman_fragment_nannvbili,container,false)
        //fragment需要通过dataBinding对象来获得view，否则会绑定失败
        return dataBinding.root
    }
    override fun onStart() {
        super.onStart()
        viewModel.showList.observe(this, Observer<ArrayList<CircleData>> {
            circle_view_test.init(viewModel.showList.value)
            if (viewModel.showList.value!=null){
                val animation = ObjectAnimator.ofFloat(circle_view_test,"nowAngle",
                        viewModel.showList.value!![0].startAngle,
                        viewModel.showList.value!![0].startAngle+360)
                animation.duration = 2000
                animation.addUpdateListener {
                    circle_view_test.invalidate()
                }
                animation.interpolator = AccelerateDecelerateInterpolator()
                animation.start()
            }
        })
        viewModel.init(dataBinding)
    }
}

