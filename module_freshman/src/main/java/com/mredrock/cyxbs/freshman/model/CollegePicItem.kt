package com.mredrock.cyxbs.freshman.model

import androidx.databinding.ObservableField

/**
 * @date 2019-08-05
 * @author Override0330
 * @description
 */
class CollegePicItem(name1: String, photoUrl1: String, name2: String, photoUrl2: String) {
    val name1 = ObservableField<String>()
    val photoUrl1 = ObservableField<String>()
    val name2 = ObservableField<String>()
    val photoUrl2 = ObservableField<String>()

    init {
        this.name1.set(name1)
        this.photoUrl1.set(photoUrl1)
        this.name2.set(name2)
        this.photoUrl2.set(photoUrl2)
    }
}