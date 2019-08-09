package com.mredrock.cyxbs.freshman.viewmodel.online

import androidx.lifecycle.MutableLiveData
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.model.item.ActivityItem

/**
 * @date 2019-08-09
 * @author Override0330
 * @description
 */
class OnlineActivityViewModel :BaseViewModel(){
    val showDialog = MutableLiveData<ActivityItem>()
    var showList = ArrayList<ActivityItem>()
    init {
        val activity1 = ActivityItem("","学长学姐帮帮忙") {
            showDialog.value = it
        toastEvent.value = R.string.freshman_online_test}
//        val activity2 = ActivityItem("","学长学姐帮帮") {showDialog.value = it}
        showList.add(activity1)
//        showList.add(activity2)
    }
}