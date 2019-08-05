package com.mredrock.cyxbs.freshman.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.mredrock.cyxbs.common.ui.BaseViewModelFragment
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.viewmodel.BusLineViewModel

/**
 * @date 2019-08-05
 * @author Override0330
 * @description
 */
class BusLineFragment : BaseViewModelFragment<BusLineViewModel>(){
    override val viewModelClass: Class<BusLineViewModel>
        get() = BusLineViewModel::class.java
    private lateinit var dataBinding: com.mredrock.cyxbs.freshman.databinding.FreshmanFragmentBuslineBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewModelFactory = getViewModelFactory()
        viewModel = if (viewModelFactory != null) {
            ViewModelProviders.of(this, viewModelFactory).get(viewModelClass)
        } else {
            ViewModelProviders.of(this).get(viewModelClass)
        }
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.freshman_fragment_busline,container,true)
        //fragment需要通过dataBinding对象来获得view，否则会绑定失败
        return dataBinding.root
    }
}