package com.mredrock.cyxbs.freshman.repositories

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.mredrock.cyxbs.common.utils.LogUtils
import com.mredrock.cyxbs.common.utils.extensions.safeSubscribeBy
import com.mredrock.cyxbs.common.utils.extensions.setSchedulers
import com.mredrock.cyxbs.freshman.model.InBackgroundEvent
import com.mredrock.cyxbs.freshman.model.db.*
import com.mredrock.cyxbs.freshman.model.db.Map
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

    //拿地址
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

    //拿路线
    fun getBusLine(lifecycleOwner: LifecycleOwner):MutableLiveData<List<BusLineItem>>{
        LogUtils.d("数据库","拿路线")
        val liveData = MutableLiveData<List<BusLineItem>>()
        FreshmanDataBase.getInstant().freshmanDao().getAllBusLine().observe(lifecycleOwner, Observer { it ->
            liveData.postValue(it.map {
                val routeList = it.route.split(",")
                BusLineItem(it.name,it.route) })
        })
        return liveData
    }

    //拿地图Url
    fun getMap(lifecycleOwner: LifecycleOwner):MutableLiveData<Map>{
        val liveData = MutableLiveData<Map>()
        FreshmanDataBase.getInstant().freshmanDao().getMap("重邮2D地图").observe(lifecycleOwner, Observer { 
            liveData.postValue(it)
        })
        return liveData
    }
    
    //拿风景Url
    fun getScenery(lifecycleOwner: LifecycleOwner):MutableLiveData<List<Scenery>>{
        val liveData = MutableLiveData<List<Scenery>>()
        FreshmanDataBase.getInstant().freshmanDao().getAllScenery().observe(lifecycleOwner, Observer { 
            liveData.postValue(it)
        })
        return liveData
    }

    //更新数据库数据
    private fun upDate(){
        //更新BusLineFragment的数据
        val busLineObservable = request.getBusLineCall()
        busLineObservable.setSchedulers(
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
                        this.insertAddress(Address(address.title,address.message))
                        this.insertBusline(list)
                        LogUtils.d("数据库","写入/更新数据")
                    }
                }
        
        //更新CollegeSceneryFragment的数据
        val collegeSceneryObserver = request.getCollegeSceneryCall()
        collegeSceneryObserver.setSchedulers(
                subscribeOn = Schedulers.io(),
                unsubscribeOn = Schedulers.io(),
                observeOn = Schedulers.io())
                .safeSubscribeBy {
                    with(FreshmanDataBase.getInstant().freshmanDao()){
                        val map = Map(it.text.title,it.text.photo)
                        val list = it.text.message.map { Scenery(it.name,it.photo) }
                        this.insertMap(map)
                        this.insertScenery(list)
                        LogUtils.d("数据库","写入/更新数据")
                    }
                }
    }
}