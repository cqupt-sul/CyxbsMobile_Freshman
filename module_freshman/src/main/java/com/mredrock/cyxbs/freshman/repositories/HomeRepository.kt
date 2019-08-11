package com.mredrock.cyxbs.freshman.repositories

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mredrock.cyxbs.freshman.model.db.FreshmanDataBase
import com.mredrock.cyxbs.freshman.model.item.HomeItem


/**
 * @date 2019-08-10
 * @author Override0330
 * @description
 */
class HomeRepository {
    fun getHomeItem(lifecycleOwner: LifecycleOwner): MutableLiveData<List<HomeItem>> {
        val homeItemList = MutableLiveData<List<HomeItem>>()
        FreshmanDataBase.getInstant().freshmanDao().getAllHomeItem().observe(lifecycleOwner,
                Observer<List<com.mredrock.cyxbs.freshman.model.db.HomeItem>> { it -> homeItemList.postValue(it.map { HomeItem(it.title, it.content) }) })
        return homeItemList
    }
}