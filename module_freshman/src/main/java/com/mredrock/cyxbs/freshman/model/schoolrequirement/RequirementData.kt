package com.mredrock.cyxbs.freshman.model.schoolrequirement

import android.graphics.Color
import android.view.View
import androidx.databinding.Bindable

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import com.mredrock.cyxbs.freshman.BR
import com.mredrock.cyxbs.freshman.R

/**
 * Created by yyfbe on 2019-08-08
 */

class RequirementData(title: String, name: String, detail: String?,
                      val onClick: (( item: RequirementData) -> Unit)?) : BaseObservable() {

    var isChecked:Boolean=false
    @Bindable
    get() = field
    set(value) {
        field=value
        notifyPropertyChanged(BR.checked)
    }
    var requirementTitle = title
        @Bindable
        get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.requirementTitle)
        }
    var requirementName = name
        @Bindable
        get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.requirementName)
        }
    var requirementDetail = detail
        @Bindable
        get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.requirementDetail)
        }
    var textColor = Color.parseColor("#333333")
        @Bindable
        get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.textColor)
        }

    var tag :String= "备忘录"
    @Bindable
    get() = field
//    @Bindable
//    get() = field
//    fun onClick() {
//        val textColors = listOf("#333333", "#bfbfbf")
//        textColor = if (tag == 0) {
//            tag = 1
//            Color.parseColor(textColors[tag])
//        } else {
//            tag = 0
//            Color.parseColor(textColors[tag])
//        }
//    }

}