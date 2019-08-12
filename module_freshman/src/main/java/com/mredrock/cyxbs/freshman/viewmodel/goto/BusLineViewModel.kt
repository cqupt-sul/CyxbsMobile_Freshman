package com.mredrock.cyxbs.freshman.viewmodel.goto

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import com.mredrock.cyxbs.freshman.model.db.Address
import com.mredrock.cyxbs.freshman.model.item.BusLineItem
import com.mredrock.cyxbs.freshman.repository.GoToCquptRepository

/**
 * @date 2019-08-05
 * @author Override0330
 * @description
 */
class BusLineViewModel :BaseViewModel(){
    private val goToCquptRepository = GoToCquptRepository.getInstant()
    fun getAddress(lifecycleOwner: LifecycleOwner): MutableLiveData<Address> = goToCquptRepository.getAddress(lifecycleOwner)
    fun getBusLine(lifecycleOwner: LifecycleOwner): MutableLiveData<List<BusLineItem>> = goToCquptRepository.getBusLine(lifecycleOwner)
}