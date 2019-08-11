package com.mredrock.cyxbs.freshman.repositories

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.mredrock.cyxbs.common.utils.LogUtils
import com.mredrock.cyxbs.common.utils.extensions.safeSubscribeBy
import com.mredrock.cyxbs.common.utils.extensions.setSchedulers
import com.mredrock.cyxbs.freshman.model.InBackgroundEvent
import com.mredrock.cyxbs.freshman.model.db.Address
import com.mredrock.cyxbs.freshman.model.db.BusLine
import com.mredrock.cyxbs.freshman.model.db.FreshmanDataBase
import com.mredrock.cyxbs.freshman.model.item.BusLineItem
import com.mredrock.cyxbs.freshman.model.remote.api.GetRequestInterface
import io.reactivex.schedulers.Schedulers
import org.greenrobot.eventbus.EventBus
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @date 2019-08-11
 * @author Override0330
 * @description
 */
class GoToCquptRepository private constructor() {
    private val retrofit = Retrofit.Builder().baseUrl("http://129.28.185.138:9025/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    private val request = retrofit.create(GetRequestInterface::class.java)

    //单例
    companion object{
        private var instant:GoToCquptRepository? = null
        @Synchronized
        fun getInstant():GoToCquptRepository{
            if (instant==null){
                instant = GoToCquptRepository()
            }
            return instant!!
        }
    }

    //指定数据库作为唯一数据来源
    fun getAddress(lifecycleOwner: LifecycleOwner):MutableLiveData<Address>{
        LogUtils.d("数据库","拿地址")
        val liveData = MutableLiveData<Address>()
        FreshmanDataBase.getInstant().freshmanDao().getAddress("重庆邮电大学地址").observe(lifecycleOwner, Observer {
            LogUtils.d("数据库","数据更新回调 ${it.address} ${it.title}")
            liveData.value = it
        })
        upDate()
        return liveData
    }
    fun getBusLine(lifecycleOwner: LifecycleOwner):MutableLiveData<List<BusLineItem>>{
        LogUtils.d("数据库","拿路线")
        val liveData = MutableLiveData<List<BusLineItem>>()
        FreshmanDataBase.getInstant().freshmanDao().getAllBusLine().observe(lifecycleOwner, Observer { it ->
            liveData.postValue(it.map { BusLineItem(it.name,it.route) })
        })
        return liveData
    }

    //更新数据
    private fun upDate(){
        val observable = request.getCall()
        observable.setSchedulers(
                subscribeOn = Schedulers.io(),
                unsubscribeOn = Schedulers.io(),
                observeOn = Schedulers.io())
                .safeSubscribeBy{ busLineResult ->
                    LogUtils.d("debug",Gson().toJson(busLineResult))
                    with(FreshmanDataBase.getInstant().freshmanDao()){
                        val list = busLineResult.text_2.message.map { BusLine(it.name,it.route.toString()) }
                        LogUtils.d("debug",list[0].name+" "+list[0].route)
                        val address = busLineResult.text_1
                        LogUtils.d("debug","${this.getAllBusLine().value?.size}")
                        EventBus.getDefault().post(InBackgroundEvent{
                            this.insertAddress(Address(address.title,address.message))
                            this.insertBusline(list)
                            LogUtils.d("数据库","写入/更新数据")
                        })
                    }
                }
    }
}