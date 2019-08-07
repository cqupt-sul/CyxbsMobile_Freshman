package com.mredrock.cyxbs.freshman.view.campusguide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.mredrock.cyxbs.common.ui.BaseViewModelFragment
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.view.BaseTabLayoutFragment
import com.mredrock.cyxbs.freshman.viewmodel.campusguide.DataDisclosureFragmentViewModel
import com.mredrock.cyxbs.freshman.viewmodel.campusguide.ExpressViewModel
import kotlinx.android.synthetic.main.freshman_activity_campus_guiide.*
import kotlinx.android.synthetic.main.freshman_fragment_express.*

/**
 * Created by yyfbe on 2019-08-04
 */
class ExpressFragment : BaseTabLayoutFragment<ExpressViewModel>(ExpressViewModel::class.java) {
    override val viewModelClass: Class<ExpressViewModel>
        get() = ExpressViewModel::class.java

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
            initTabLayout(vp_express, tl_express, titles, listOf(ExpressDetailFragment.newInstance(0),
                    ExpressDetailFragment.newInstance(1)))
        }
        return inflater.inflate(R.layout.freshman_fragment_express, container, false)
    }
}