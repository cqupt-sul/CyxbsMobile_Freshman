package com.mredrock.cyxbs.freshman.view.guide

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
import com.mredrock.cyxbs.freshman.model.bean.ExpressDetailData
import com.mredrock.cyxbs.freshman.view.adapter.BaseRecyclerViewAdapter
import com.mredrock.cyxbs.freshman.viewmodel.guide.ExpressDetailViewModel
import kotlinx.android.synthetic.main.freshman_fragment_express_detail.*

/**
 * Created by yyfbe on 2019-08-05
 */
class ExpressDetailFragment : BaseViewModelFragment<ExpressDetailViewModel>() {
    override val viewModelClass: Class<ExpressDetailViewModel>
        get() = ExpressDetailViewModel::class.java

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.freshman_fragment_express_detail, container, false)
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
            viewModel.getExpressDetails(fragmentIndex).observe {
                val adapter = BaseRecyclerViewAdapter<FreshmanRecycleItemExpressDetailBinding, ExpressDetailData>(
                        R.layout.freshman_recycle_item_express_detail, BR.expressDetailData, it)
                rv_express_detail.adapter = adapter
                rv_express_detail.layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?
            }
        }
    }

    companion object {
        fun newInstance(tag: Int): ExpressDetailFragment {
            val args = Bundle()
            args.putInt("Index", tag)
            val fragment = ExpressDetailFragment()
            fragment.arguments = args
            return fragment

        }
    }
}