package com.mredrock.cyxbs.freshman.model.item

import androidx.databinding.ObservableField

/**
 * @date 2019-08-05
 * @author Override0330
 * @description
 */
class BusLineItem(name: String, route: String) {
    val name = ObservableField<String>()
    val route = ObservableField<String>()
    init {
        this.name.set(name)
        this.route.set(route)
    }
}