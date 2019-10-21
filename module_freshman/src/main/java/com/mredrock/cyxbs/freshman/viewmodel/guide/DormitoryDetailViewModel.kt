package com.mredrock.cyxbs.freshman.viewmodel.guide

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import com.mredrock.cyxbs.freshman.model.bean.DormitoryDetailData
import com.mredrock.cyxbs.freshman.model.db.Dormitory
import com.mredrock.cyxbs.freshman.repository.CampusGuideRepository

/**
 * Created by yyfbe on 2019-08-06
 */
class DormitoryDetailViewModel : BaseViewModel() {

    private val dormitoryDetailList = MutableLiveData<DormitoryDetailData>()
    val campusGuideRepository = CampusGuideRepository.getInstant()

    fun getDormitoryData(lifecycleOwner: LifecycleOwner,name:String):MutableLiveData<Dormitory> = campusGuideRepository.getDormitoryLiveData(lifecycleOwner,name)
}