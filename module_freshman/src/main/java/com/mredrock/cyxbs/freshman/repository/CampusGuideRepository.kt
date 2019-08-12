package com.mredrock.cyxbs.freshman.repository

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mredrock.cyxbs.common.utils.LogUtils
import com.mredrock.cyxbs.common.utils.extensions.safeSubscribeBy
import com.mredrock.cyxbs.common.utils.extensions.setSchedulers
import com.mredrock.cyxbs.freshman.model.bean.ChartData
import com.mredrock.cyxbs.freshman.model.bean.ExpressBean
import com.mredrock.cyxbs.freshman.model.bean.ExpressDetailData
import com.mredrock.cyxbs.freshman.model.db.*
import com.mredrock.cyxbs.freshman.model.remote.api.imageBaseUrl
import com.mredrock.cyxbs.freshman.model.remote.api.request
import io.reactivex.schedulers.Schedulers

/**
 * @date 2019-08-12
 * @author Override0330
 * @description
 */

class CampusGuideRepository private constructor(){
    companion object{
        private var intstant:CampusGuideRepository? = null

        @Synchronized
        fun getInstant():CampusGuideRepository{
            if (intstant==null){
                intstant = CampusGuideRepository()
            }
            return intstant!!
        }
    }

    fun getDormitoryLiveData(lifecycleOwner: LifecycleOwner,name:String):MutableLiveData<Dormitory>{
        val liveData = MutableLiveData<Dormitory>()
        FreshmanDataBase.getInstant().freshmanDao().getDormitoryByName(name).observe(lifecycleOwner, Observer{
            liveData.postValue(it)
        })
        return liveData
    }

    fun getDormitoryListLiveData(lifecycleOwner: LifecycleOwner):MutableLiveData<List<String>>{
        val list = MutableLiveData<List<String>>()
        updata()
        FreshmanDataBase.getInstant().freshmanDao().getAllDormitory().observe(lifecycleOwner, Observer {
            LogUtils.d("数据库层变化监听","${it.size}")
            list.postValue(it.map { it.name })
        })
        return list
    }

    fun getCanteenLiveData(lifecycleOwner: LifecycleOwner, name: String):MutableLiveData<Canteen>{
        val liveData = MutableLiveData<Canteen>()
        FreshmanDataBase.getInstant().freshmanDao().getCanteenByName(name).observe(lifecycleOwner, Observer {
            liveData.postValue(it)
        })
        return liveData
    }

    fun getCanteenListLiveData(lifecycleOwner: LifecycleOwner):MutableLiveData<List<String>>{
        val list = MutableLiveData<List<String>>()
        FreshmanDataBase.getInstant().freshmanDao().getAllCanteen().observe(lifecycleOwner, Observer {
            list.postValue(it.map { it.name })
        })
        return list
    }

    fun getExpressLiveData(lifecycleOwner: LifecycleOwner,name: String):MutableLiveData<List<ExpressDetailData>>{
        val liveData = MutableLiveData<List<ExpressDetailData>>()
        FreshmanDataBase.getInstant().freshmanDao().getExpressByName(name).observe(lifecycleOwner, Observer {
            liveData.postValue(it.map { ExpressDetailData(ExpressBean.TextBean.MessageBean(it.title,it.detail,it.photoUrl)) })
        })
        return liveData
    }

    fun getExpressListLiveData(lifecycleOwner: LifecycleOwner):MutableLiveData<List<String>>{
        val liveData = MutableLiveData<List<String>>()
        FreshmanDataBase.getInstant().freshmanDao().getAllExpress().observe(lifecycleOwner, Observer {
            val list = (it.map { it.name }).toSet().toList()
            liveData.postValue(list)
        })
        return liveData
    }

    fun getSchoolListLiveData(lifecycleOwner: LifecycleOwner):MutableLiveData<List<String>>{
        val liveData = MutableLiveData<List<String>>()
        FreshmanDataBase.getInstant().freshmanDao().getAllSubject().observe(lifecycleOwner, Observer {
            val list = it.map { it.name }.toSet().toList()
            if (liveData.value.isNullOrEmpty()){
                LogUtils.d("回调","发送跟新的数据")
                liveData.postValue(list)
            }
        })
        return liveData
    }

    fun getSubjectLiveData(lifecycleOwner: LifecycleOwner,name: String):MutableLiveData<List<ChartData>>{
        val liveData = MutableLiveData<List<ChartData>>()
        FreshmanDataBase.getInstant().freshmanDao().getSubjectBySchool(name).observe(lifecycleOwner, Observer {list->
            liveData.postValue(list.map { ChartData(it.subject, it.data.toFloat()) })
        })
        return liveData
    }

    fun getBoyAndGirlLiveData(lifecycleOwner: LifecycleOwner,name: String):MutableLiveData<BoyAndGirl>{
        val liveData = MutableLiveData<BoyAndGirl>()
        FreshmanDataBase.getInstant().freshmanDao().getBoyAndGirlBySchool(name).observe(lifecycleOwner, Observer {
            liveData.postValue(it)
        })
        return liveData
    }



    private fun updata(){
        val dormitoryObservable = request.getDormitoryCall()
        dormitoryObservable.setSchedulers(
                subscribeOn = Schedulers.io(),
                unsubscribeOn = Schedulers.io(),
                observeOn = Schedulers.io())
                .safeSubscribeBy {
                    LogUtils.d("网络请求","${it.text[0]}")
                    with(FreshmanDataBase.getInstant().freshmanDao()){
                        val list = it.text
                        LogUtils.d("网络请求", it.text[0].title)
                        list.forEach{ content ->
                            if (content.title == "学生寝室") {
                                LogUtils.d("数据库写入","宿舍 ${content.title}")
                                this.insertDormitory(content.message.map { Dormitory(it.name, it.detail, it.getPhoto()) })
                            }else if (content.title == "学生食堂") {
                                LogUtils.d("数据库写入","食堂 ${content.title}")
                                this.insertCanteen(content.message.map { Canteen(it.name,it.detail,it.getPhoto()) })
                            }
                            }
                        }
                    }

        val expressObservable = request.getExpressCall()
        expressObservable.setSchedulers(subscribeOn = Schedulers.io(),
                unsubscribeOn = Schedulers.io(),
                observeOn = Schedulers.io()).safeSubscribeBy {
            with(FreshmanDataBase.getInstant().freshmanDao()){
                val list = it.text
                LogUtils.d("网络请求", it.text[0].name)
                list.forEach{company->
                    company.message.forEach {
                        this.insertExpressAddress(ExpressAddress(company.name,it.title,it.detail, imageBaseUrl +it.photo))
                        LogUtils.d("imageUrl",imageBaseUrl +it.photo)
                    }
                }
            }
        }

        val subjectObservable = request.getSubjectCall()
        subjectObservable.setSchedulers(subscribeOn = Schedulers.io(),
                unsubscribeOn = Schedulers.io(),
                observeOn = Schedulers.io()).safeSubscribeBy {
            with(FreshmanDataBase.getInstant().freshmanDao()){
                val list = it.text
                list.forEach {content->
                    this.insertSubject(content.message.map { Subject(content.name,it.subject,it.data) })
                }
            }
        }

        val boyAndGirlObservable = request.getBoyAndGirlCall()
        boyAndGirlObservable.setSchedulers(subscribeOn = Schedulers.io(),
                unsubscribeOn = Schedulers.io(),
                observeOn = Schedulers.io()).safeSubscribeBy {
            with(FreshmanDataBase.getInstant().freshmanDao()){
                val list = it.text
                this.insertBoyAndGirl(list.map { BoyAndGirl(it.name,it.boy,it.girl) })
            }
        }


    }
}