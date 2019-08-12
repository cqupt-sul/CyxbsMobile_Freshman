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
class DormitoryViewModel : BaseViewModel() {
    private val expressList= MutableLiveData<List<DormitoryCanteenData>>()
    fun initData(): LiveData<List<DormitoryCanteenData>> {
        loadData()
        return expressList
    }
    //网络请求
    private fun loadData() {
        val data= listOf<DormitoryCanteenData>(
                DormitoryCanteenData("皇家5栋"),
                DormitoryCanteenData("皇家6栋")
        )
        expressList.value=data
    }

    val campusGuideRepository = CampusGuideRepository.getInstant()
    fun getDorimtoryList(lifecycleOwner: LifecycleOwner):MutableLiveData<List<String>> = campusGuideRepository.getDormitoryListLiveData(lifecycleOwner)
}