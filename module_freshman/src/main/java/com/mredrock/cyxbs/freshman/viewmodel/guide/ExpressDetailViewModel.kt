package com.mredrock.cyxbs.freshman.viewmodel.guide

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import com.mredrock.cyxbs.freshman.model.bean.ExpressBean
import com.mredrock.cyxbs.freshman.model.bean.ExpressDetailData
import com.mredrock.cyxbs.freshman.repository.CampusGuideRepository

/**
 * Created by yyfbe on 2019-08-05
 */
class ExpressDetailViewModel : BaseViewModel() {
    private val campusGuideRepository = CampusGuideRepository.getInstant()
    fun getExpressDatail(lifecycleOwner: LifecycleOwner,name: String):MutableLiveData<List<ExpressDetailData>> = campusGuideRepository.getExpressLiveData(lifecycleOwner,name)
}