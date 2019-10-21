package com.mredrock.cyxbs.freshman.model.bean

import androidx.databinding.ObservableField
import java.util.*

/**
 * Created by yyfbe on 2019-08-09
 */
class OrderData(orderTitle: String, orderMessage: String,
                orderPhoto: String, orderDetail: String) {
    var title = ObservableField<String>()
    var message = ObservableField<String>()
    var photo = ObservableField<String>()
    var detail = ObservableField<String>()

    init {
        this.title.set(orderTitle)
        this.message.set(orderMessage)
        this.photo.set(orderPhoto)
        this.detail.set(orderDetail)
    }
}