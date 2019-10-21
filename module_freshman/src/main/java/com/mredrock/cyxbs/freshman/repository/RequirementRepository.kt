package com.mredrock.cyxbs.freshman.repository

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.mredrock.cyxbs.common.utils.LogUtils
import com.mredrock.cyxbs.common.utils.extensions.safeSubscribeBy
import com.mredrock.cyxbs.common.utils.extensions.setSchedulers
import com.mredrock.cyxbs.freshman.model.InBackgroundEvent
import com.mredrock.cyxbs.freshman.model.db.FreshmanDataBase
import com.mredrock.cyxbs.freshman.model.db.RequireItem
import com.mredrock.cyxbs.freshman.model.remote.api.request
import io.reactivex.schedulers.Schedulers
import org.greenrobot.eventbus.EventBus

/**
 * Created by yyfbe on 2019-08-11
 */
class RequirementRepository private constructor() {
    init {
        upDate()
    }


    //单例
    companion object {
        private var instant: RequirementRepository? = null
        @Synchronized
        fun getInstant(): RequirementRepository {
            if (instant == null) {
                instant = RequirementRepository()
            }
            return instant!!
        }
    }

    //指定数据库为唯一数据来源

    //拿所有入学必备需求
    fun getAllRequire(lifecycleOwner: LifecycleOwner): MutableLiveData<List<RequireItem>> {
        LogUtils.d("数据库", "拿入学必备需求")
        val liveData = MutableLiveData<List<RequireItem>>()
        FreshmanDataBase.getInstant().freshmanDao().getAllRequireItem().observe(lifecycleOwner, Observer {
            liveData.postValue(it)
        })
        return liveData
    }

    //根据title拿不同的需求
    fun getRequireByTitle(lifecycleOwner: LifecycleOwner, title: String): MutableLiveData<List<RequireItem>> {
        LogUtils.d("数据库", "根据title拿不同的需求")
        val liveData = MutableLiveData<List<RequireItem>>()
        FreshmanDataBase.getInstant().freshmanDao().getRequireItemByTitle(title).observe(lifecycleOwner, Observer {
            liveData.postValue(it)
        })
        return liveData
    }

    //更新数据库
    private fun upDate() {
        LogUtils.d("数据库", "网络请求更新")
        val requirementObservable = request.getRequireCall()
        requirementObservable.setSchedulers(
                subscribeOn = Schedulers.io(),
                unsubscribeOn = Schedulers.io(),
                observeOn = Schedulers.io())
                .safeSubscribeBy { requirementResult ->
                    LogUtils.d("网络请求数据", Gson().toJson(requirementResult))
                    with(FreshmanDataBase.getInstant().freshmanDao()) {
//                        val list: List<RequireItem> = listOf<RequireItem>()
                        for (i in requirementResult.text) {
//                            LogUtils.d("debugInfo",list.toString())
                           val groupList2= i.data.map { RequireItem(it.name, i.title, it.detail) }
                            LogUtils.d("debugInfo",groupList2[0].title+"/"+groupList2[0].name+"/"+groupList2[0].detail)
                            this.insertRequireItem(groupList2)
                        }
//                        LogUtils.d("debugInfo",list[0].detail)
                        LogUtils.d("数据库", "写入/更新数据")
                    }
                }
    }

    //用户增加
     fun updateByUser(requireItem: RequireItem) {
        EventBus.getDefault().post(InBackgroundEvent {
            FreshmanDataBase.getInstant().freshmanDao().insertRequireItem(requireItem)
        })
    }

    //用户删除
     fun deleteByUser(requireItem: RequireItem) {
        EventBus.getDefault().post(InBackgroundEvent {
            FreshmanDataBase.getInstant().freshmanDao().deleteRequireItem(requireItem)
        })
    }
}