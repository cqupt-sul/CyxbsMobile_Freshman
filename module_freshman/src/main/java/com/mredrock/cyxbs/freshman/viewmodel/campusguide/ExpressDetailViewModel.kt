package com.mredrock.cyxbs.freshman.viewmodel.campusguide

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import com.mredrock.cyxbs.freshman.model.campusguide.ExpressBean
import com.mredrock.cyxbs.freshman.model.campusguide.ExpressDetailData

/**
 * Created by yyfbe on 2019-08-05
 */
class ExpressDetailViewModel : BaseViewModel() {
    private val expressDetailList = MutableLiveData<ArrayList<ExpressDetailData>>()
    fun getExpressDetails(fragmentIndex:Int): MutableLiveData<ArrayList<ExpressDetailData>> {
        loadData(fragmentIndex)
        return expressDetailList
    }

    private fun loadData(fragmentIndex:Int) {
       val showList = ArrayList<ExpressDetailData>()
       val showList1 = ArrayList<ExpressDetailData>()
        val messageBean= ExpressBean.TextBean.MessageBean()
        messageBean.detail="..sa................" +
                "..啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊.."
        messageBean.title="樱花园"
        messageBean.photo="https://p.ssl.qhimg.com/dmfd/400_300_/t0120b2f23b554b8402.jpg"
        val messageBean1= ExpressBean.TextBean.MessageBean()
        messageBean1.detail="..sa................" +
                "..啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊.."
        messageBean1.title="不是樱花园"
        messageBean1.photo="https://p.ssl.qhimg.com/dmfd/400_300_/t0120b2f23b554b8402.jpg"
        showList.add(ExpressDetailData(messageBean))
        showList.add(ExpressDetailData(messageBean))
        showList.add(ExpressDetailData(messageBean))
        showList1.add(ExpressDetailData(messageBean1))
        showList1.add(ExpressDetailData(messageBean1))
        showList1.add(ExpressDetailData(messageBean1))
        when (fragmentIndex){
            0->expressDetailList.value=showList
            1->expressDetailList.value=showList1
        }
    }

}