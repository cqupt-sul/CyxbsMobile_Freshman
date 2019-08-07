package com.mredrock.cyxbs.freshman.viewmodel.campusguide

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import com.mredrock.cyxbs.freshman.model.campusguide.ExpressData

/**
 * Created by yyfbe on 2019-08-04
 */
class ExpressViewModel : BaseViewModel() {
    private val expressList= MutableLiveData<List<ExpressData>>()
    fun initData():LiveData<List<ExpressData>>{
        loadData()
        return expressList
    }
    //网络请求
    private fun loadData() {
        val data= listOf<ExpressData>(
                ExpressData("中通"),
                ExpressData("EMS")
        )
        expressList.value=data
    }
}