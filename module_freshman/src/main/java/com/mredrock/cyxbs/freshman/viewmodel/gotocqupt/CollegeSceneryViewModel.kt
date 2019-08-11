package com.mredrock.cyxbs.freshman.viewmodel.gotocqupt

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import com.mredrock.cyxbs.freshman.model.db.Map
import com.mredrock.cyxbs.freshman.model.db.Scenery
import com.mredrock.cyxbs.freshman.repositories.GoToCquptRepository

/**
 * @date 2019-08-05
 * @author Override0330
 * @description
 */
class CollegeSceneryViewModel : BaseViewModel() {
    private val goToCquptRepository = GoToCquptRepository.getInstant()
    fun getMapLiveData(lifecycleOwner: LifecycleOwner):MutableLiveData<Map> = goToCquptRepository.getMap(lifecycleOwner)
    fun getSceneryListLiveData(lifecycleOwner: LifecycleOwner):MutableLiveData<List<Scenery>> = goToCquptRepository.getScenery(lifecycleOwner)
}