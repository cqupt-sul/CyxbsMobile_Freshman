package com.mredrock.cyxbs.freshman.model.bean

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mredrock.cyxbs.freshman.BR

/**
 * @date 2019-08-12
 * @author Override0330
 * @description
 */
class SearchText:BaseObservable(){
    var text:String = ""
    @Bindable
    get() = field
    set(value){
        field = value
        notifyPropertyChanged(BR.text)
        this.liveData.postValue(field)
    }
    val liveData = MutableLiveData<String>()
}