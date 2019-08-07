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
import com.mredrock.cyxbs.freshman.databinding.FreshmanRecycleItemDataDisclosureBinding
import com.mredrock.cyxbs.freshman.databinding.FreshmanRecycleItemHomeBinding
import com.mredrock.cyxbs.freshman.model.campusguide.InstituteData
import com.mredrock.cyxbs.freshman.view.adapter.BaseRecyclerViewAdapter
import com.mredrock.cyxbs.freshman.viewmodel.campusguide.DataDisclosureFragmentViewModel
import kotlinx.android.synthetic.main.freshman_fragment_data_disclosure.*

/**
 * Created by yyfbe on 2019-08-05
 */
class DataDisclosureFragment : BaseViewModelFragment<DataDisclosureFragmentViewModel>() {
    override val viewModelClass: Class<DataDisclosureFragmentViewModel>
        get() = DataDisclosureFragmentViewModel::class.java

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.freshman_fragment_data_disclosure, container, false)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelFactory = getViewModelFactory()
        viewModel = if (viewModelFactory != null) {
            ViewModelProviders.of(this, viewModelFactory).get(viewModelClass)
        } else {
            ViewModelProviders.of(this).get(viewModelClass)
        }
        viewModel.initData().observe {
            val adapter = BaseRecyclerViewAdapter<FreshmanRecycleItemDataDisclosureBinding, InstituteData>(
                    R.layout.freshman_recycle_item_data_disclosure, BR.instituteItem, it)
            rv_data_disclosure.adapter = adapter
            rv_data_disclosure.layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?
        }
    }


}