package com.mredrock.cyxbs.freshman.viewmodel.guide

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import com.mredrock.cyxbs.freshman.model.bean.ChartData

class SubjectViewModel : BaseViewModel() {
    private val subjectDataList= MutableLiveData<List<ChartData>>()
    fun initData():LiveData<List<ChartData>>{
        loadData()
        return subjectDataList
    }

    private fun loadData() {

        val data= listOf<ChartData>(
                ChartData("高等数学", 0.60f),
                ChartData("大学物理", 0.40f),
                ChartData("线性代数", 0.30f)
        )
        subjectDataList.value=data
    }
}