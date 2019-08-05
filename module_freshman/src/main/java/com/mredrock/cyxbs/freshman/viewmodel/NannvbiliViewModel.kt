package com.mredrock.cyxbs.freshman.viewmodel

import androidx.lifecycle.MutableLiveData
import com.mredrock.cyxbs.common.BaseApp
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import com.mredrock.cyxbs.freshman.BR
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.databinding.FreshmanFragmentNannvbiliBinding
import com.mredrock.cyxbs.freshman.model.DataRevealedSchool
import com.mredrock.cyxbs.freshman.model.CircleData

/**
* @date 2019-08-02
* @author Override0330
* @description
*/

class NannvbiliViewModel : BaseViewModel() {
    val showList = MutableLiveData<ArrayList<CircleData>>()
    fun init(dataBinding: FreshmanFragmentNannvbiliBinding){
        val dataList = ArrayList<CircleData>()
        val showData1 = CircleData(33F, 78F, "28.13%", BaseApp.context.resources.getColor(R.color.freshman_activity_data_revealed_man_color))
        val showData2 = CircleData(111F, 282F, "72.13%", BaseApp.context.resources.getColor(R.color.freshman_activity_data_revealed_woman_color))
        dataList.add(showData1)
        dataList.add(showData2)
        showList.value = dataList
        val school = DataRevealedSchool()
        school.schoolName.set("软件工程学院")
        dataBinding.setVariable(BR.showSchool,school)
    }
}