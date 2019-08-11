package com.mredrock.cyxbs.freshman.model.item

import androidx.databinding.ObservableField

/**
 * @date 2019-08-08
 * @author Override0330
 * @description
 */
class GroupItem (name: String,groupId: String){
    val name = ObservableField<String>()
    val groupId = ObservableField<String>()
    init {
        this.name.set(name)
        this.groupId.set(groupId)
    }
}