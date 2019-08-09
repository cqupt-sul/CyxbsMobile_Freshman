package com.mredrock.cyxbs.freshman.view.campusguide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.view.BaseTabLayoutFragment
import com.mredrock.cyxbs.freshman.viewmodel.campusguide.CanteenViewModel
import kotlinx.android.synthetic.main.freshman_fragment_canteen.*

class CanteenFragment : BaseTabLayoutFragment<CanteenViewModel>(CanteenViewModel::class.java) {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewModelFactory = getViewModelFactory()
        viewModel = if (viewModelFactory != null) {
            ViewModelProviders.of(this, viewModelFactory).get(viewModelClass)
        } else {
            ViewModelProviders.of(this).get(viewModelClass)
        }
        viewModel.initData().observe {
            val titles = ArrayList<String>()
            for (i in 0 until it?.size!!) {
                titles.add(it[i].name)
            }
            initTabLayout(vp_canteen, tl_canteen, titles, listOf(CanteenDetailFragment.newInstance(1), CanteenDetailFragment.newInstance(0)))
        }
        return inflater.inflate(R.layout.freshman_fragment_canteen, container, false)
    }
}