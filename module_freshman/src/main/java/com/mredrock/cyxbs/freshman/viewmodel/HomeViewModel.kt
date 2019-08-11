package com.mredrock.cyxbs.freshman.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import com.mredrock.cyxbs.freshman.model.item.HomeItem
import com.mredrock.cyxbs.freshman.repositories.HomeRepository

/**
* @date 2019-08-03
* @author Override0330
* @description
*/

class HomeViewModel : BaseViewModel(){
    private val homeRepository = HomeRepository()
    fun getShowList(lifecycleOwner: LifecycleOwner):MutableLiveData<List<HomeItem>> = homeRepository.getHomeItem(lifecycleOwner)
}