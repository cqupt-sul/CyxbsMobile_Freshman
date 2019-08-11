package com.mredrock.cyxbs.freshman.viewmodel.schoolrequirement

import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.CheckBox
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import com.mredrock.cyxbs.freshman.model.schoolrequirement.RequirementData

/**
 * Created by yyfbe on 2019-08-08
 */
class MainRequirementViewModel : BaseViewModel() {
    private val showList = MutableLiveData<ArrayList<RequirementData>>()
    private val showMemoList = MutableLiveData<ArrayList<RequirementData>>()
    fun initData(): LiveData<ArrayList<RequirementData>> {
        loadData()
        return showList
    }

    fun initMemoData(): MutableLiveData<ArrayList<RequirementData>> {
        loadData()
        return showMemoList
    }

    private fun loadData() {
        val f = { view: View, item: RequirementData ->
            {
                run {
                    if ((view as CheckBox).isChecked) {
                        item.textColor = Color.parseColor("#bfbfbf")
                    } else {
                        item.textColor = Color.parseColor("#333333")
                    }
                }
            }
        }
        val list: ArrayList<RequirementData>? = ArrayList<RequirementData>()
        list?.add(RequirementData("备忘录", "多喝热水", null) {  item ->
            run {
                if (item.isChecked) {
                    item.textColor = Color.parseColor("#bfbfbf")
                } else {
                    item.textColor = Color.parseColor("#333333")
                }
            }
        })

        list?.add(RequirementData("备忘录", "多喝热水", null) {  item ->
            run {
                if (item.isChecked) {
                    item.textColor = Color.parseColor("#bfbfbf")
                } else {
                    item.textColor = Color.parseColor("#333333")
                }
            }
        })
        list?.add(RequirementData("报道必备", "多喝热水", "真的难") {  item ->
            run {
                if (item.isChecked) {
                    item.textColor = Color.parseColor("#bfbfbf")
                } else {
                    item.textColor = Color.parseColor("#333333")
                }
            }
        })
        if (list != null) {
            selectData(list)
        }
        showList.value = list
    }

    private fun selectData(list: ArrayList<RequirementData>) {
        showMemoList.value = list.filter { it.requirementTitle == "备忘录" } as ArrayList<RequirementData>

    }
}