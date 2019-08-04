package com.mredrock.cyxbs.freshman.view.adapter

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.mredrock.cyxbs.freshman.BR

/**
 * @date 2019-08-03
 * @author Override0330
 * @description
 */

class HomeItem(title: String, smallTitle: String) : BaseObservable() {
    var title: String = title
        @Bindable
        get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.title)
        }
    var smallTitle: String = smallTitle
        @Bindable
        get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.smallTitle)
        }
}