package com.mredrock.cyxbs.freshman.viewmodel

import androidx.lifecycle.MutableLiveData
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import com.mredrock.cyxbs.freshman.model.item.HomeItem

/**
* @date 2019-08-03
* @author Override0330
* @description
*/

class HomeViewModel : BaseViewModel(){
    var showList = MutableLiveData<ArrayList<HomeItem>>()
    fun init(){
        val dataList = ArrayList<HomeItem>()
        val titleList = arrayListOf("入学必备","指路重邮","入学流程","校园指导","线上活动","更多功能","关于我们")
        val smallList = arrayListOf("报到必备 宿舍用品 学习用品",
                "重邮路线 重邮地图",
                "入学步骤 入学地点",
                "宿舍 快递指引",
                "老乡群 专业群",
                "迎新网 新生课表",
                "红岩网校")
        for(i in 0 until titleList.size){
            val homeItem = HomeItem(titleList[i], smallList[i])
            dataList.add(homeItem)
        }

        showList.value = dataList
    }
}