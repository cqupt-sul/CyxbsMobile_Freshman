package com.mredrock.cyxbs.freshman.model.item

import androidx.databinding.ObservableField
import com.mredrock.cyxbs.freshman.model.CopyStringToClipboard
import com.mredrock.cyxbs.freshman.view.goto.CopySuccessDialogFragment
import org.greenrobot.eventbus.EventBus

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

    fun copyGroupId(){
        if (this.groupId.get()!=null){
            EventBus.getDefault().post(CopyStringToClipboard(this.groupId.get()!!))
        }
    }
}