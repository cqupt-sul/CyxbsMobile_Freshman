package com.mredrock.cyxbs.freshman.repositories

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mredrock.cyxbs.common.utils.extensions.safeSubscribeBy
import com.mredrock.cyxbs.common.utils.extensions.setSchedulers
import com.mredrock.cyxbs.freshman.model.ShowActivityDialog
import com.mredrock.cyxbs.freshman.model.db.FreshmanDataBase
import com.mredrock.cyxbs.freshman.model.db.SchoolGroup
import com.mredrock.cyxbs.freshman.model.item.ActivityItem
import com.mredrock.cyxbs.freshman.model.item.GroupItem
import com.mredrock.cyxbs.freshman.model.remote.api.request
import io.reactivex.schedulers.Schedulers
import org.greenrobot.eventbus.EventBus

/**
 * @date 2019-08-11
 * @author Override0330
 * @description
 */
class OnlineDiscussRepository {
    init {
        updata()
    }
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
    fun getSchoolList(lifecycleOwner: LifecycleOwner):MutableLiveData<List<GroupItem>>{
        val list = MutableLiveData<List<GroupItem>>()
        FreshmanDataBase.getInstant().freshmanDao().getAllSchoolGroup().observe(lifecycleOwner, Observer { schoolGroupList ->
            list.postValue(schoolGroupList.map { GroupItem(it.name,it.groupId) })
        })
        return list
    }

    //拿到老乡群的列表
    fun getFriendList(lifecycleOwner: LifecycleOwner):MutableLiveData<List<GroupItem>>{
        val list = MutableLiveData<List<GroupItem>>()
        FreshmanDataBase.getInstant().freshmanDao().getAllFellowTownsMan().observe(lifecycleOwner, Observer { OldFriendList ->
            list.postValue(OldFriendList.map { GroupItem(it.name,it.groupId) })
        })
        return list
    }

    //拿到线上互动的列表
    fun getActivity(lifecycleOwner: LifecycleOwner):MutableLiveData<List<ActivityItem>>{
        val list = MutableLiveData<List<ActivityItem>>()
        FreshmanDataBase.getInstant().freshmanDao().getAllOnlineActivity().observe(lifecycleOwner, Observer { onlineActivity ->
            list.postValue(onlineActivity.map {
                ActivityItem(it.photoUrl,it.name){activity->
                    EventBus.getDefault().postSticky(ShowActivityDialog(it.qrUrl,it.message))} })
        })
        return list
    }

    //更新数据
    fun updata(){
        //更新学院群
        val observableSchoolGroup = request.getSchoolGroupCall()
        observableSchoolGroup.setSchedulers(
                subscribeOn = Schedulers.io(),
                unsubscribeOn = Schedulers.io(),
                observeOn = Schedulers.io()).safeSubscribeBy { groupResult ->
            FreshmanDataBase.getInstant().freshmanDao().insertSchoolGroup(groupResult.text.map { SchoolGroup(it.name,it.data) })
        }
    }

}