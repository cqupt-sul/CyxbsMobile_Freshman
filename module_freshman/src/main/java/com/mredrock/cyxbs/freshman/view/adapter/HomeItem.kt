package com.mredrock.cyxbs.freshman.view.adapter

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.mredrock.cyxbs.freshman.BR

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