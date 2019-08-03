package com.mredrock.cyxbs.freshman.model

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData


class DataRevealedSchool{
    val schoolName = ObservableField<String>()
    //男女比例数据
    var showList = MutableLiveData<ArrayList<CircleData>>()
    //成绩的数据⤵️
}