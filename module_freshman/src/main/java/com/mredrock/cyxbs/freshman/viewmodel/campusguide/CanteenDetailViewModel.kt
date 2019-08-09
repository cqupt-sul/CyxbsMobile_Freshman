package com.mredrock.cyxbs.freshman.viewmodel.campusguide

import androidx.lifecycle.MutableLiveData
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import com.mredrock.cyxbs.freshman.model.campusguide.DormitoryCanteenBean
import com.mredrock.cyxbs.freshman.model.campusguide.DormitoryDetailData

/**
 * Created by yyfbe on 2019-08-07
 */
class CanteenDetailViewModel : BaseViewModel() {

    private val canteenDetailList = MutableLiveData<DormitoryDetailData>()
    fun getCanteenDetails(fragmentIndex: Int): MutableLiveData<DormitoryDetailData> {
        loadData(fragmentIndex)
        return canteenDetailList
    }

    private fun loadData(fragmentIndex: Int) {
        val showData = DormitoryDetailData(DormitoryCanteenBean.TextBean.MessageBean())
        showData.detailData.name = "千禧鹤贵"
        showData.detailData.detail = "1waafshdihfisahfsja2啊啊啊啊啊啊啊啊啊啊啊啊是" +
                "呃呃呃呃呃呃呃呃呃呃呃"
        val showData1 = DormitoryDetailData(DormitoryCanteenBean.TextBean.MessageBean())
        showData1.detailData.name = "中心便宜"
        showData1.detailData.detail = "1waafshdihfisahfsja2啊啊啊啊啊啊啊啊啊啊啊啊是" +
                "呃呃呃呃呃呃呃呃呃呃呃"
        when (fragmentIndex) {
            0 -> canteenDetailList.value = showData
            1 -> canteenDetailList.value = showData1
        }

    }
}