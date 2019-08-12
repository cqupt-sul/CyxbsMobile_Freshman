package com.mredrock.cyxbs.freshman.view.guide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mredrock.cyxbs.common.utils.LogUtils
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.view.BaseTabLayoutFragment
import com.mredrock.cyxbs.freshman.viewmodel.guide.DormitoryViewModel
import kotlinx.android.synthetic.main.freshman_fragment_dormitory.*


class DormitoryFragment : BaseTabLayoutFragment<DormitoryViewModel>(DormitoryViewModel::class.java) {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.freshman_fragment_dormitory, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDorimtoryList(viewLifecycleOwner).observe{ list ->
            if (list!=null){
                val fragmentList = list.map { DormitoryDetailFragment.newInstance(it) }
                LogUtils.d("加载宿舍fragment","${list.toString()}")
                initTabLayout(vp_dormitory,tl_dormitory,list,fragmentList)
            }
        }
    }
}
