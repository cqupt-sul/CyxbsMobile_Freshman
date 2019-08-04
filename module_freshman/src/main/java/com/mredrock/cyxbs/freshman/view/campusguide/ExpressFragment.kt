package com.mredrock.cyxbs.freshman.view.campusguide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mredrock.cyxbs.common.ui.BaseViewModelFragment
import com.mredrock.cyxbs.freshman.viewmodel.campusguide.ExpressViewModel

/**
 * Created by yyfbe on 2019-08-04
 */
class ExpressFragment : BaseViewModelFragment<ExpressViewModel>() {
    override val viewModelClass: Class<ExpressViewModel>
        get() = ExpressViewModel::class.java

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}