package com.mredrock.cyxbs.freshman.viewmodel.requirement

import android.util.Log
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import com.mredrock.cyxbs.freshman.model.AddRequire
import com.mredrock.cyxbs.freshman.model.bean.RequirementData
import org.greenrobot.eventbus.EventBus

/**
 * Created by yyfbe on 2019-08-09
 */
class RequirementEditViewModel : BaseViewModel() {
    var editText: String? = null
    private var requirementData : RequirementData?=null
    fun getEditText(text: String) {
        editText = text
        EventBus.getDefault().post(AddRequire(editText!!))

//        requirementData= RequirementData("备忘录",editText.toString(),null)
    }
    //传给上面的总的title
}