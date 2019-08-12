package com.mredrock.cyxbs.freshman.viewmodel.requirement

import android.graphics.Color
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mredrock.cyxbs.common.utils.LogUtils
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import com.mredrock.cyxbs.freshman.model.bean.RequirementData
import com.mredrock.cyxbs.freshman.model.AddRequire
import com.mredrock.cyxbs.freshman.model.CurrentAddRequire
import com.mredrock.cyxbs.freshman.model.CurrentDeleteRequire
import com.mredrock.cyxbs.freshman.model.DeleteRequire
import com.mredrock.cyxbs.freshman.model.db.RequireItem
import com.mredrock.cyxbs.freshman.repository.RequirementRepository
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by yyfbe on 2019-08-08
 */
class MainRequirementViewModel : BaseViewModel() {
    init {
        EventBus.getDefault().register(this)
    }

    private val requireRepository = RequirementRepository.getInstant()
    private val showList = MutableLiveData<ArrayList<RequirementData>>()
    private val list: ArrayList<RequirementData>? = ArrayList<RequirementData>()
    fun initData(lifecycleOwner: LifecycleOwner): LiveData<ArrayList<RequirementData>> {
        loadData(lifecycleOwner)
        return showList
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    fun deleteItem(deleteRequire: DeleteRequire) {
        delete(deleteRequire.requireInfo)
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    fun addItem(addRequire: AddRequire) {
        add(addRequire.requireInfo)
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    fun currentRemove(currentDeleteRequire: CurrentDeleteRequire) {
        LogUtils.d("debug", currentDeleteRequire.requireInfo)
        deleteCurrent(currentDeleteRequire)
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    fun currentRemove(currentAddRequire: CurrentAddRequire) {
        LogUtils.d("debug", currentAddRequire.requireInfo)
        addCurrent(currentAddRequire)
    }

    private fun addCurrent(currentAddRequire: CurrentAddRequire) {
        list?.add(0, RequirementData("备忘录", currentAddRequire.requireInfo, null) {
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

    private fun deleteCurrent(currentDeleteRequire: CurrentDeleteRequire) {
        val iterator = list?.iterator()
        while (iterator!!.hasNext()) {
            if (iterator.next().requirementName == currentDeleteRequire.requireInfo) {
                iterator.remove()
            }
        }
        showList.value = list
    }

    private fun delete(info: String) {
        val iterator = list?.iterator()
        while (iterator!!.hasNext()) {
            if (iterator.next().requirementName == info) {
                iterator.remove()
                requireRepository.deleteByUser(RequireItem(info, "备忘录", ""))
            }
        }
        showList.value = list
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
        requireRepository.updateByUser(RequireItem(info, "备忘录", ""))
        showList.value = list
    }


    private fun loadData(lifecycleOwner: LifecycleOwner) {
        //从数据库获得信息
        val repositoryList: LiveData<List<RequireItem>> = requireRepository.getAllRequire(lifecycleOwner)
        repositoryList.observe(lifecycleOwner, Observer {
            if (repositoryList.value != null)
                for (i in repositoryList.value!!) {
                    val requirementData = (RequirementData(i.title, i.name, checkNull(i.detail)) { item ->
                        run {
                            if (item.isChecked) {
                                item.textColor = Color.parseColor("#bfbfbf")
                            } else {
                                item.textColor = Color.parseColor("#333333")
                            }
                        }
                    })
//            requirementData.isChecked=i.isChecked
                    list?.add(requirementData)
                    list?.forEach {
                        LogUtils.d("showListData", it.requirementTitle + " " + it.requirementDetail + " " + it.requirementName)
                    }
                }
            showList.value = list
        })
//        val f = { view: View, item: RequirementData ->
//            {
//                run {
//                    if ((view as CheckBox).isChecked) {
//                        item.textColor = Color.parseColor("#bfbfbf")
//                    } else {
//                        item.textColor = Color.parseColor("#333333")
//                    }
//                }
//            }
//        }

//        list?.add(RequirementData("备忘录", "多喝水", null) { item ->
//            run {
//                if (item.isChecked) {
//                    item.textColor = Color.parseColor("#bfbfbf")
//                } else {
//                    item.textColor = Color.parseColor("#333333")
//                }
//            }
//        })
//
//        list?.add(RequirementData("备忘录", "多喝热水", null) { item ->
//            run {
//                if (item.isChecked) {
//                    item.textColor = Color.parseColor("#bfbfbf")
//                } else {
//                    item.textColor = Color.parseColor("#333333")
//                }
//            }
//        })
//        list?.add(RequirementData("报道必备", "喝热水", "真的难") { item ->
//            run {
//                if (item.isChecked) {
//                    item.textColor = Color.parseColor("#bfbfbf")
//                } else {
//                    item.textColor = Color.parseColor("#333333")
//                }
//            }
//        })
//        if (list != null) {
//            selectData(list)
//        }
    }

    //    private fun selectData(list: ArrayList<RequirementData>) {
//        showMemoList.value = list.filter { it.requirementTitle == "备忘录" } as ArrayList<RequirementData>
//
//    }
    private fun checkNull(string: String?): String? {
        if (string == "") {
            return null
        }
        return string

    }

    override fun onCleared() {
        super.onCleared()
        EventBus.getDefault().unregister(this)
    }
}