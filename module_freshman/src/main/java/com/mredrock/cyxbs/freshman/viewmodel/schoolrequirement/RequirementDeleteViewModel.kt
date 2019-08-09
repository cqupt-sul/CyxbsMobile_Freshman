package com.mredrock.cyxbs.freshman.viewmodel.schoolrequirement

import android.annotation.SuppressLint
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.model.schoolrequirement.RequirementData

/**
 * Created by yyfbe on 2019-08-09
 */
class RequirementDeleteViewModel : BaseViewModel() {

    private var deleteCount: Int = 0
    private val showList = MutableLiveData<ArrayList<RequirementData>>()
    private val lastList = ArrayList<RequirementData>()
    @SuppressLint("SetTextI18n")
    fun initData(view: View): LiveData<ArrayList<RequirementData>> {
        view.setOnClickListener {
            checkList()
            (it as TextView).text = "删除($deleteCount)"
        }
        loadData(view as TextView)
        return showList
    }

    private fun checkList() {
    }

    private fun loadData(textView: TextView) {
        val list: ArrayList<RequirementData>? = ArrayList<RequirementData>()
        list?.add(RequirementData("备忘录", "多喝热水", null) { view, item ->
            run {
                view.background=view.resources.getDrawable(R.drawable.freshman_checkbox_color_style)
                if ((view as CheckBox).isChecked) {
                    deleteCount++
                    lastList.remove(item)
                    textView.text = "删除($deleteCount)"
//                item.textColor = Color.parseColor("#bfbfbf")
                } else {
                    deleteCount--
                    lastList.add(item)
                    if (deleteCount != 0) {
                        textView.text = "删除($deleteCount)"
                    }else  textView.text = "删除"

//                item.textColor= Color.parseColor("#333333")
                }
            }
        })

//        list?.add(RequirementData("备忘录", "多喝热水", null) {})
//        list?.add(RequirementData("报道必备", "多喝热水", "真的难", null))
//        list?.add(RequirementData("报道必备", "多喝热水", "真的难"))
//        list?.add(RequirementData("军训用品", "多喝开水", null))
        showList.value = list
    }
}