package com.mredrock.cyxbs.freshman.view.campusguide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.mredrock.cyxbs.common.ui.BaseViewModelFragment
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.viewmodel.campusguide.CanteenDetailViewModel
import com.mredrock.cyxbs.freshman.viewmodel.campusguide.DormitoryDetailViewModel
import kotlinx.android.synthetic.main.freshman_fragment_canteen_detail.*
import kotlinx.android.synthetic.main.freshman_fragment_dormitory_detail.*

/**
 * Created by yyfbe on 2019-08-07
 */
class CanteenDetailFragment: BaseViewModelFragment<CanteenDetailViewModel>() {
    override val viewModelClass: Class<CanteenDetailViewModel>
        get() = CanteenDetailViewModel::class.java

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.freshman_fragment_canteen_detail, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fragmentIndex = arguments?.getInt("Index")
        val viewModelFactory = getViewModelFactory()
        viewModel = if (viewModelFactory != null) {
            ViewModelProviders.of(this, viewModelFactory).get(viewModelClass)
        } else {
            ViewModelProviders.of(this).get(viewModelClass)
        }
        if (fragmentIndex != null) {
            viewModel.getCanteenDetails(fragmentIndex).observe {
                tv_canteen_detail_name.text=it?.detailData?.name
                tv_canteen_detail_content.text=it?.detailData?.detail
            }
        }
    }

    companion object {
        fun newInstance(tag: Int): CanteenDetailFragment {
            val args = Bundle()
            args.putInt("Index", tag)
            val fragment = CanteenDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }
}