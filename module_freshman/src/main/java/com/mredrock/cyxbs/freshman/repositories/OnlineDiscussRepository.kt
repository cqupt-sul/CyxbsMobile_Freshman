package com.mredrock.cyxbs.freshman.repositories

import androidx.lifecycle.MutableLiveData
import com.mredrock.cyxbs.freshman.model.item.GroupItem

/**
 * @date 2019-08-11
 * @author Override0330
 * @description
 */
class OnlineDiscussRepository {
    //单例
    companion object{
        private var instant:OnlineDiscussRepository? = null
        @Synchronized
        fun getInstant():OnlineDiscussRepository{
            if (instant==null){
                instant = OnlineDiscussRepository()
            }
            return instant!!
        }
    }

    //拿到学员群的列表
    fun getSchoolList():MutableLiveData<List<GroupItem>>{
        val list = MutableLiveData<List<GroupItem>>()
        
        return list
    }
}