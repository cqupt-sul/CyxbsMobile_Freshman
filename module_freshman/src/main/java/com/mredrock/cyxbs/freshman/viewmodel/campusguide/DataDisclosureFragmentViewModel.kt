package com.mredrock.cyxbs.freshman.viewmodel.campusguide

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import com.mredrock.cyxbs.freshman.model.campusguide.InstituteData

/**
 * Created by yyfbe on 2019-08-04
 */
class DataDisclosureFragmentViewModel : BaseViewModel() {
    val showList = MutableLiveData<ArrayList<InstituteData>>()
    fun initData():LiveData<ArrayList<InstituteData>>{
        loadData()
        return showList
    }

    private fun loadData() {
        val dataList:ArrayList<InstituteData> =ArrayList()
                dataList.add(InstituteData("传媒艺术学院"))
        dataList.add(InstituteData("生物信息学院"))
//                InstituteData("自动化学院")
//                InstituteData("1学院")
//                InstituteData("2学院")
//                InstituteData("3学院")
//                InstituteData("4学院")
//                InstituteData("5学院")
//                InstituteData("6学院")
        showList.value = dataList
    }
}