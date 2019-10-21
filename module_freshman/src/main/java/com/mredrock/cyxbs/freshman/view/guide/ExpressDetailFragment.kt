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

    val adapter = BaseRecyclerViewAdapter<FreshmanRecycleItemExpressDetailBinding, ExpressDetailData>(
            R.layout.freshman_recycle_item_express_detail, BR.expressDetailData, null)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.freshman_fragment_express_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_express_detail.adapter = adapter
        rv_express_detail.layoutManager = LinearLayoutManager(context)
        val company = arguments?.getString("tag")
        if (company!=null){
            viewModel.getExpressDatail(viewLifecycleOwner,company).observe{
                if (it!=null){
                    adapter.submitShowList(it)
                }
            }
        }
    }


    companion object {
        fun newInstance(tag: String): ExpressDetailFragment {
            val args = Bundle()
            args.putString("tag", tag)
            val fragment = ExpressDetailFragment()
            fragment.arguments = args
            return fragment

        }
    }
}