package com.mredrock.cyxbs.freshman.viewmodel.guide

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import com.mredrock.cyxbs.freshman.model.db.Canteen
import com.mredrock.cyxbs.freshman.repository.CampusGuideRepository

/**
 * Created by yyfbe on 2019-08-07
 */
class CanteenDetailViewModel : BaseViewModel() {

    private val campusGuideRepository = CampusGuideRepository.getInstant()
    fun getCanteenLiveData(lifecycleOwner: LifecycleOwner, name :String):MutableLiveData<Canteen> = campusGuideRepository.getCanteenLiveData(lifecycleOwner,name)
}