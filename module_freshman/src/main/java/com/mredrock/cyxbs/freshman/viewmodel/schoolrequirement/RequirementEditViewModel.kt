package com.mredrock.cyxbs.freshman.viewmodel.schoolrequirement

import android.util.Log
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import com.mredrock.cyxbs.freshman.model.schoolrequirement.RequirementData

/**
 * Created by yyfbe on 2019-08-09
 */
class RequirementEditViewModel : BaseViewModel() {
    var editText: String? = null
    private var requirementData : RequirementData?=null
    fun getEditText(text: String) {
        editText = text
        Log.d("yyf", editText)
//        requirementData= RequirementData("备忘录",editText.toString(),null)
    }
    //传给上面的总的title
}