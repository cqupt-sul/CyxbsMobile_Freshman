package com.mredrock.cyxbs.freshman.model.item

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import com.mredrock.cyxbs.freshman.BR

/**
 * @date 2019-08-03
 * @author Override0330
 * @description
 */

class HomeItem(title: String, smallTitle: String) {

    val title = ObservableField<String>()
    val smallTitle = ObservableField<String>()

    init {
        this.title.set(title)
        this.smallTitle.set(smallTitle)
    }
}