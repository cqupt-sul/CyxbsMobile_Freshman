package com.mredrock.cyxbs.freshman.view.campusguide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mredrock.cyxbs.common.ui.BaseViewModelFragment
import com.mredrock.cyxbs.freshman.BR
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.databinding.FreshmanRecycleItemExpressDetailBinding
import com.mredrock.cyxbs.freshman.model.campusguide.ExpressDetailData
import com.mredrock.cyxbs.freshman.view.adapter.BaseRecyclerViewAdapter
import com.mredrock.cyxbs.freshman.viewmodel.campusguide.DormitoryDetailViewModel
import com.mredrock.cyxbs.freshman.viewmodel.campusguide.ExpressDetailViewModel
import kotlinx.android.synthetic.main.freshman_fragment_dormitory_detail.*
import kotlinx.android.synthetic.main.freshman_fragment_express_detail.*

/**
 * Created by yyfbe on 2019-08-07
 */
class DormitoryDetailFragment : BaseViewModelFragment<DormitoryDetailViewModel>() {
    override val viewModelClass: Class<DormitoryDetailViewModel>
    get() = DormitoryDetailViewModel::class.java

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.freshman_fragment_dormitory_detail, container, false)
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
            viewModel.getDormitoryDetails(fragmentIndex).observe {
                tv_dormitory_detail_name.text=it?.detailData?.name
                tv_dormitory_detail_content.text=it?.detailData?.detail
            }
        }
    }

    companion object {
        fun newInstance(tag: Int): DormitoryDetailFragment {
            val args = Bundle()
            args.putInt("Index", tag)
            val fragment = DormitoryDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }
}