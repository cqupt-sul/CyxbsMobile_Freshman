package com.mredrock.cyxbs.freshman.viewmodel.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import com.mredrock.cyxbs.freshman.model.bean.OrderData

/**
 * Created by yyfbe on 2019-08-10
 */
class OrderViewModel : BaseViewModel() {
    private val showList = MutableLiveData<ArrayList<OrderData>>()
    fun initData(): LiveData<ArrayList<OrderData>> {
        loadData()
        return showList
    }

    fun loadData() {
        val list = ArrayList<OrderData>()
        list.add(OrderData("持相关证件至风雨操场各学院报道"
                , "太极操场购买军训物资太极操场购买军训物\n" +
                "资啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦\n" +
                "啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦", "https://p.ssl.qhimg.com/dmfd/400_300_/t0120b2f23b554b8402.jpg", "太极操场购买军训物资太极操场购买军训物\n" +
                "资啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦\n" +
                "啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦"))
        list.add(OrderData("持相关证件至风雨操场各学院报道"
                , "太极操场购买军训物资太极操场购买军训物\n" +
                "资啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦\n" +
                "啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦", "https://p.ssl.qhimg.com/dmfd/400_300_/t0120b2f23b554b8402.jpg", "太极操场购买军训物资太极操场购买军训物\n" +
                "资啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦\n" +
                "啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦"))
        list.add(OrderData("持相关证件至风雨操场各学院报道"
                , "太极操场购买军训物资太极操场购买军训物\n" +
                "资啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦\n" +
                "啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦", "https://p.ssl.qhimg.com/dmfd/400_300_/t0120b2f23b554b8402.jpg", "太极操场购买军训物资太极操场购买军训物\n" +
                "资啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦\n" +
                "啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦"))
        list.add(OrderData("持相关证件至风雨操场各学院报道"
                , "太极操场购买军训物资太极操场购买军训物\n" +
                "资啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦\n" +
                "啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦", "https://p.ssl.qhimg.com/dmfd/400_300_/t0120b2f23b554b8402.jpg", "太极操场购买军训物资太极操场购买军训物\n" +
                "资啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦\n" +
                "啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦"))
        showList.value = list
    }

}