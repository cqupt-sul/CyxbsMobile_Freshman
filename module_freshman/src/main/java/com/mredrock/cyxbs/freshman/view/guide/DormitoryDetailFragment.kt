package com.mredrock.cyxbs.freshman.view.guide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mredrock.cyxbs.common.ui.BaseViewModelFragment
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.viewmodel.guide.DormitoryDetailViewModel
import kotlinx.android.synthetic.main.freshman_fragment_dormitory_detail.*

/**
 * Created by yyfbe on 2019-08-07
 */
class DormitoryDetailFragment : BaseViewModelFragment<DormitoryDetailViewModel>() {
    override val viewModelClass: Class<DormitoryDetailViewModel>
    get() = DormitoryDetailViewModel::class.java

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.freshman_fragment_dormitory_detail, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tag = arguments?.getString("tag")
        if (tag != null){
            viewModel.getDormitoryData(viewLifecycleOwner,tag).observe{
                if (it != null){
                    tv_dormitory_detail_name.text = it.name
                    tv_dormitory_detail_content.text = it.detail
                    //轮播图初始化
                }
            }
        }
    }

    companion object {
        fun newInstance(tag: String): DormitoryDetailFragment {
            val args = Bundle()
            args.putString("tag", tag)
            val fragment = DormitoryDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }
}