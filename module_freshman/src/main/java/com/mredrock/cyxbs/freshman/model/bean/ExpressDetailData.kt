package com.mredrock.cyxbs.freshman.model.bean

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.mredrock.cyxbs.freshman.BR

/**
 * Created by yyfbe on 2019-08-05
 */
class ExpressDetailData( detailData: ExpressBean.TextBean.MessageBean): BaseObservable() {
    var detailData=detailData
    @Bindable
    get() = field
    set(value) {
        field = value
        notifyPropertyChanged(BR.expressDetailData)
    }
}