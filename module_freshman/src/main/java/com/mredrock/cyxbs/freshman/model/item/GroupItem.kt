package com.mredrock.cyxbs.freshman.model.item

import androidx.databinding.ObservableField

/**
 * @date 2019-08-08
 * @author Override0330
 * @description
 */
class GroupItem (name: String){
    val name = ObservableField<String>()
    val groupId = 1000
    init {
        this.name.set(name)
    }
}