package com.mredrock.cyxbs.freshman.viewmodel.online

import androidx.lifecycle.LifecycleOwner
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import com.mredrock.cyxbs.freshman.repository.OnlineDiscussRepository

/**
 * @date 2019-08-09
 * @author Override0330
 * @description
 */
class OnlineActivityViewModel :BaseViewModel(){
    private val onlineDiscussRepository = OnlineDiscussRepository.getInstant()
    fun getOnlineActivityLiveData(lifecycleOwner: LifecycleOwner) = onlineDiscussRepository.getActivity(lifecycleOwner)
}