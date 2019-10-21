package com.mredrock.cyxbs.freshman.viewmodel.online

import androidx.databinding.ObservableField
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import com.mredrock.cyxbs.freshman.model.item.GroupItem
import com.mredrock.cyxbs.freshman.repository.OnlineDiscussRepository

/**
 * @date 2019-08-08
 * @author Override0330
 * @description
 */
class GroupFragmentViewModel : BaseViewModel(){
    private val onlineDiscussRepository = OnlineDiscussRepository.getInstant()
    fun getSchoolGroup(lifecycleOwner: LifecycleOwner):MutableLiveData<List<GroupItem>> = onlineDiscussRepository.getSchoolList(lifecycleOwner)
    fun getOldFriendGroup(lifecycleOwner: LifecycleOwner):MutableLiveData<List<GroupItem>> = onlineDiscussRepository.getFriendList(lifecycleOwner)
    fun getSearchList(text:String):MutableLiveData<List<GroupItem>> = onlineDiscussRepository.getSearchList(text)

}