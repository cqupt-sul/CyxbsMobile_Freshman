package com.mredrock.cyxbs.freshman.view.guide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.view.BaseTabLayoutFragment
import com.mredrock.cyxbs.freshman.viewmodel.guide.CanteenViewModel
import kotlinx.android.synthetic.main.freshman_fragment_canteen.*

class CanteenFragment : BaseTabLayoutFragment<CanteenViewModel>(CanteenViewModel::class.java) {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.freshman_fragment_canteen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCanteenList(viewLifecycleOwner).observe{list->
            if (list!=null){
                val fragmentList = list.map { CanteenDetailFragment.newInstance(it) }
                initTabLayout(vp_canteen,tl_canteen,list,fragmentList)
            }
        }
    }
}