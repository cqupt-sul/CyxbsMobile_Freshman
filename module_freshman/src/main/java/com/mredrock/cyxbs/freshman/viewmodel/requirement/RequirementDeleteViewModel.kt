package com.mredrock.cyxbs.freshman.viewmodel.requirement

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mredrock.cyxbs.common.utils.LogUtils
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import com.mredrock.cyxbs.freshman.model.AddRequire
import com.mredrock.cyxbs.freshman.model.CurrentAddRequire
import com.mredrock.cyxbs.freshman.model.CurrentDeleteRequire
import com.mredrock.cyxbs.freshman.model.bean.RequirementData
import com.mredrock.cyxbs.freshman.model.db.RequireItem
import com.mredrock.cyxbs.freshman.repository.RequirementRepository
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by yyfbe on 2019-08-09
 */
class RequirementDeleteViewModel : BaseViewModel() {
    init {
        EventBus.getDefault().register(this)
    }

    private val requireRepository = RequirementRepository.getInstant()
    private var deleteCount = MutableLiveData<Int>()
    private var countValue = 0
    private val showList = MutableLiveData<ArrayList<RequirementData>>()
    private val removeList = ArrayList<RequirementData>()
    val list: ArrayList<RequirementData>? = ArrayList<RequirementData>()
    @SuppressLint("SetTextI18n")
    fun initData(lifecycleOwner: LifecycleOwner): LiveData<ArrayList<RequirementData>> {
        loadData(lifecycleOwner)
        return showList
    }

    fun getDeleteCount(): LiveData<Int> {
//        deleteCount.value=countValue
        return deleteCount
    }


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    fun addItem(addRequire: AddRequire) {
        LogUtils.d("事件", addRequire.requireInfo)
        add(addRequire.requireInfo)
    }

    private fun add(info: String) {
        list?.add(0, RequirementData("备忘录", info, null) {
            run {
                if (it.isChecked) {
                    it.textColor = Color.parseColor("#bfbfbf")
                } else {
                    it.textColor = Color.parseColor("#333333")
                }
            }
        })
        showList.value = list
    }

    private fun loadData(lifecycleOwner: LifecycleOwner) {
        //从数据库获得信息
        val repositoryList: LiveData<List<RequireItem>> = requireRepository.getRequireByTitle(lifecycleOwner, "备忘录")
        repositoryList.observe(lifecycleOwner, Observer {
            if (repositoryList.value != null)
                for (i in repositoryList.value!!) {
                    val requirementData = (RequirementData(i.title, i.name, checkNull(i.detail)) { item ->
                        if (item.isChecked) {
                            removeList.add(item)
                            //发消息删除
//                            EventBus.getDefault().post(DeleteRequire(item.requirementName))
                            countValue++
                            deleteCount.value = countValue
                            EventBus.getDefault().post(CurrentDeleteRequire(item.requirementName))

                        } else {
                            removeList.remove(item)
                            countValue--
                            //发消息增加
//                            EventBus.getDefault().post(AddRequire(item.requirementName))
                            deleteCount.value = countValue
                            EventBus.getDefault().post(CurrentAddRequire(item.requirementName))


                        }
                    })
//            requirementData.isChecked=i.isChecked
                    list?.add(requirementData)
//                    list?.forEach {
//                        LogUtils.d("showListData", it.requirementTitle + " " + it.requirementDetail + " " + it.requirementName)
//                    }
                }

            showList.value = list
        })
    }

    override fun onCleared() {
        super.onCleared()
        for (i in removeList) {
            requireRepository.deleteByUser(RequireItem(i.requirementName, i.requirementTitle, ""))
        }
        EventBus.getDefault().unregister(this)
    }

    private fun checkNull(string: String?): String? {
        if (string == "") {
            return null
        }
        return string

    }

}