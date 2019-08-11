package com.mredrock.cyxbs.freshman.viewmodel.schoolrequirement

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mredrock.cyxbs.common.ui.BaseActivity
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.model.schoolrequirement.RequirementData
import org.greenrobot.eventbus.EventBus
import org.jetbrains.anko.activityManager

/**
 * Created by yyfbe on 2019-08-09
 */
class RequirementDeleteViewModel : BaseViewModel() {

    private var deleteCount = MutableLiveData<Int>()
    private var countValue = 0
    private val showList = MutableLiveData<ArrayList<RequirementData>>()
    private val removeList = ArrayList<RequirementData>()
    @SuppressLint("SetTextI18n")
    fun initData(): LiveData<ArrayList<RequirementData>> {
        loadData()
        return showList
    }

    fun getDeleteCount(): LiveData<Int> {
//        deleteCount.value=countValue
        return deleteCount
    }

    private fun checkList() {
    }

    private fun loadData() {
        val list: ArrayList<RequirementData>? = ArrayList<RequirementData>()
        list?.add(RequirementData("备忘录", "多喝热水", null) {  item ->
            run {
                if (item.isChecked) {
                    removeList.add(item)
                    countValue++
                    deleteCount.value=countValue
                } else {
                    removeList.remove(item)
                    countValue--
                    deleteCount.value=countValue
                }
            }
        })
        list?.add(RequirementData("备忘录", "多喝热水", null) {  item ->
            run {
                if (item.isChecked) {
                    countValue++
                    deleteCount.value=countValue
                } else {
                    countValue--
                    deleteCount.value=countValue
                }
            }
        })
        showList.value = list
    }
}