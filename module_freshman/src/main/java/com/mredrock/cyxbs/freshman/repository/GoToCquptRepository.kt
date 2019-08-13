package com.mredrock.cyxbs.freshman.repository

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mredrock.cyxbs.common.utils.LogUtils
import com.mredrock.cyxbs.common.utils.extensions.safeSubscribeBy
import com.mredrock.cyxbs.common.utils.extensions.setSchedulers
import com.mredrock.cyxbs.freshman.model.db.*
import com.mredrock.cyxbs.freshman.model.db.Map
import com.mredrock.cyxbs.freshman.model.item.BusLineItem
import com.mredrock.cyxbs.freshman.model.item.CollegePicItem
import com.mredrock.cyxbs.freshman.model.remote.api.imageBaseUrl
import com.mredrock.cyxbs.freshman.model.remote.api.request
import io.reactivex.schedulers.Schedulers

/**
 * @date 2019-08-11
 * @author Override0330
 * @description
 */
class GoToCquptRepository private constructor() {
    init {
        upDate()
    }

    init {
        upDate()
    }

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
            liveData.postValue(it)
        })
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
    fun getScenery(lifecycleOwner: LifecycleOwner):MutableLiveData<List<CollegePicItem>>{
        val liveData = MutableLiveData<List<CollegePicItem>>()
        FreshmanDataBase.getInstant().freshmanDao().getAllScenery().observe(lifecycleOwner, Observer {
            val list = ArrayList<CollegePicItem>()
            for (i in 0 until it.size step 2){
                val collegePicItem = CollegePicItem(it[i].name,it[i].photoUrl,it[(i+1)].name,it[i+1].photoUrl)
                list.add(collegePicItem)
                LogUtils.d("debug-picget", collegePicItem.photoUrl1.get().toString())
            }
            liveData.postValue(list)
        })
        return liveData
    }

    fun getSceneryUrlList(lifecycleOwner: LifecycleOwner):LiveData<List<String>>{
        val liveData = MutableLiveData<List<String>>()
        FreshmanDataBase.getInstant().freshmanDao().getAllScenery().observe(lifecycleOwner, Observer { list ->
            liveData.postValue(list.map { it.photoUrl })
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
                    with(FreshmanDataBase.getInstant().freshmanDao()){
                        val list = busLineResult.text_2.message.map { BusLine(it.name,it.getRoute()) }
                        val address = busLineResult.text_1
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
                        val map = Map(it.text.title, imageBaseUrl+it.text.photo)
                        val list = it.text.message.map { Scenery(it.name,(imageBaseUrl+it.photo).toString()) }
                        this.insertMap(map)
                        this.insertScenery(list)
                    }
                }
    }
}