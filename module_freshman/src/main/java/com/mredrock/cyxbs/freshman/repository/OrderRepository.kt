package com.mredrock.cyxbs.freshman.repository

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.mredrock.cyxbs.common.utils.LogUtils
import com.mredrock.cyxbs.common.utils.extensions.safeSubscribeBy
import com.mredrock.cyxbs.common.utils.extensions.setSchedulers
import com.mredrock.cyxbs.freshman.model.db.FreshmanDataBase
import com.mredrock.cyxbs.freshman.model.db.OrderItem
import com.mredrock.cyxbs.freshman.model.remote.api.request
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

/**
 * Created by yyfbe on 2019-08-12
 */
class OrderRepository private constructor() {
    init {
        upDate()
    }


    //单例
    companion object {
        private var instant: OrderRepository? = null
        @Synchronized
        fun getInstant(): OrderRepository {
            if (instant == null) {
                instant = OrderRepository()
            }
            return instant!!
        }
    }

    //指定数据库为唯一来源
    //得到所有流程
    fun getAllOrder(lifecycleOwner: LifecycleOwner): MutableLiveData<List<OrderItem>> {
        LogUtils.d("数据库", "拿入学流程")
        val liveData = MutableLiveData<List<OrderItem>>()
        FreshmanDataBase.getInstant().freshmanDao().getOrderItem().observe(lifecycleOwner, Observer {
            liveData.postValue(it)
        })
        return liveData
    }

    //更新数据库
    private fun upDate() {
        LogUtils.d("数据库", "网络请求更新")
        val orderResultObservable = request.getOrderCall()
        orderResultObservable.setSchedulers(
                subscribeOn = Schedulers.io(),
                unsubscribeOn = Schedulers.io(),
                observeOn = Schedulers.io())
                .safeSubscribeBy { orderResult ->
                    LogUtils.d("网络请求数据", Gson().toJson(orderResult))
                    with(FreshmanDataBase.getInstant().freshmanDao()) {
                        for (i in orderResult.text) {
                            this.insertOrderItem(OrderItem(i.title, i.message, i.photo, i.detail))
                        }
                        LogUtils.d("数据库", "写入/更新数据")

                    }

                }
    }


}