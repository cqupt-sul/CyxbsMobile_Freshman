package com.mredrock.cyxbs.freshman.viewmodel.order

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import com.mredrock.cyxbs.freshman.model.bean.OrderData
import com.mredrock.cyxbs.freshman.model.db.OrderItem
import com.mredrock.cyxbs.freshman.repository.OrderRepository

/**
 * Created by yyfbe on 2019-08-10
 */
class OrderViewModel : BaseViewModel() {
    private val orderRepository=OrderRepository.getInstant()
    fun initData(lifecycleOwner: LifecycleOwner): MutableLiveData<List<OrderItem>> = orderRepository.getAllOrder(lifecycleOwner)


}