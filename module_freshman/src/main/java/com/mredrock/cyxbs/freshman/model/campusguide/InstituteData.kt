package com.mredrock.cyxbs.freshman.model.campusguide

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.mredrock.cyxbs.freshman.BR

/**
 * Created by yyfbe on 2019-08-04
 */
class InstituteData(name:String): BaseObservable() {
    var name: String = name
        @Bindable
        get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.instituteItem)
        }
}