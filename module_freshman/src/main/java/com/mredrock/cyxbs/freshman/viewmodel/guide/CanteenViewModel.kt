package com.mredrock.cyxbs.freshman.viewmodel.guide

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import com.mredrock.cyxbs.freshman.model.bean.DormitoryCanteenData
import com.mredrock.cyxbs.freshman.repository.CampusGuideRepository

/**
 * Created by yyfbe on 2019-08-06
 */
class CanteenViewModel: BaseViewModel() {
    private val expressList= MutableLiveData<List<DormitoryCanteenData>>()
    fun initData(): LiveData<List<DormitoryCanteenData>> {
        loadData()
        return expressList
    }
    //网络请求
    private fun loadData() {
        val data= listOf<DormitoryCanteenData>(
                DormitoryCanteenData("千禧鹤"),
                DormitoryCanteenData("中心食堂")
        )
        expressList.value=data
    }

    val campusGuideRepository = CampusGuideRepository.getInstant()
    fun getCanteenList(lifecycleOwner: LifecycleOwner):MutableLiveData<List<String>> = campusGuideRepository.getCanteenListLiveData(lifecycleOwner)
}