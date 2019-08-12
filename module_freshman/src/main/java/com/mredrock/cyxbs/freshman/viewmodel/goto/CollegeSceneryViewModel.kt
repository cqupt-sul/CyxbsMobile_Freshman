package com.mredrock.cyxbs.freshman.viewmodel.goto

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import com.mredrock.cyxbs.freshman.model.db.Map
import com.mredrock.cyxbs.freshman.model.item.CollegePicItem
import com.mredrock.cyxbs.freshman.repository.GoToCquptRepository

/**
 * @date 2019-08-05
 * @author Override0330
 * @description
 */
class CollegeSceneryViewModel : BaseViewModel() {
    private val goToCquptRepository = GoToCquptRepository.getInstant()
    fun getMapLiveData(lifecycleOwner: LifecycleOwner):MutableLiveData<Map> = goToCquptRepository.getMap(lifecycleOwner)
    fun getSceneryListLiveData(lifecycleOwner: LifecycleOwner):MutableLiveData<List<CollegePicItem>> = goToCquptRepository.getScenery(lifecycleOwner)
    fun getSceneryUrlList(lifecycleOwner: LifecycleOwner):LiveData<List<String>> =goToCquptRepository.getSceneryUrlList(lifecycleOwner)
}