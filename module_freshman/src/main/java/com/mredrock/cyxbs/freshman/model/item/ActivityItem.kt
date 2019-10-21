package com.mredrock.cyxbs.freshman.model.item

import androidx.databinding.ObservableField

/**
 * @date 2019-08-09
 * @author Override0330
 * @description
 */
class ActivityItem(imageUrl: String, activityName: String, val onClick: (it:ActivityItem) -> Unit){
    val imageUrl = ObservableField<String>()
    val activityName = ObservableField<String>()
    init {
        this.imageUrl.set(activityName)
        this.activityName.set(activityName)
    }
}