package com.mredrock.cyxbs.freshman.viewmodel.guide

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import com.mredrock.cyxbs.freshman.model.bean.ExpressData
import com.mredrock.cyxbs.freshman.repository.CampusGuideRepository

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
    val campusGuideRepository = CampusGuideRepository.getInstant()
    fun getExpressList(lifecycleOwner: LifecycleOwner):MutableLiveData<List<String>> = campusGuideRepository.getExpressListLiveData(lifecycleOwner)

}