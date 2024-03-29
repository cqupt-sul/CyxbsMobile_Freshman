package com.mredrock.cyxbs.freshman.view.guide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.view.BaseTabLayoutFragment
import com.mredrock.cyxbs.freshman.viewmodel.guide.ExpressViewModel
import kotlinx.android.synthetic.main.freshman_activity_campus_guiide.*
import kotlinx.android.synthetic.main.freshman_fragment_express.*

/**
 * Created by yyfbe on 2019-08-04
 */
class ExpressFragment : BaseTabLayoutFragment<ExpressViewModel>(ExpressViewModel::class.java) {
    override val viewModelClass: Class<ExpressViewModel>
        get() = ExpressViewModel::class.java

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.freshman_fragment_express, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.campusGuideRepository.getExpressListLiveData(viewLifecycleOwner).observe{
            if (it != null){
                val fragmentList = it.map { ExpressDetailFragment.newInstance(it) }
                initTabLayout(vp_express,tl_express,it,fragmentList)
            }
        }
    }
}