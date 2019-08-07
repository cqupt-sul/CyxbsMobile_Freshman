package com.mredrock.cyxbs.freshman.viewmodel.campusguide

import androidx.lifecycle.MutableLiveData
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import com.mredrock.cyxbs.freshman.model.campusguide.DormitoryCanteenBean
import com.mredrock.cyxbs.freshman.model.campusguide.DormitoryDetailData

/**
 * Created by yyfbe on 2019-08-06
 */
class DormitoryDetailViewModel : BaseViewModel() {

    private val expressDetailList = MutableLiveData<DormitoryDetailData>()
    fun getDormitoryDetails(fragmentIndex:Int): MutableLiveData<DormitoryDetailData> {
        loadData(fragmentIndex)
        return expressDetailList
    }

    private fun loadData(fragmentIndex:Int) {
        val showData = DormitoryDetailData(DormitoryCanteenBean.TextBean.MessageBean())
        showData.detailData.name = "5栋其实也挺好的"
        showData.detailData.detail = "1waafshdihfisahfsja2啊啊啊啊啊啊啊啊啊啊啊啊是" +
                "呃呃呃呃呃呃呃呃呃呃呃"
        val showData1 = DormitoryDetailData(DormitoryCanteenBean.TextBean.MessageBean())
        showData1.detailData.name = "5栋其实真的好"
        showData1.detailData.detail = "1waafshdihfisahfsja2啊啊啊啊啊啊啊啊啊啊啊啊是" +
                "呃呃呃呃呃呃呃呃呃呃呃"
        when(fragmentIndex){
            0->expressDetailList.value=showData
            1->expressDetailList.value=showData1
        }

    }
}