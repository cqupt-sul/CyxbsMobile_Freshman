package com.mredrock.cyxbs.freshman.viewmodel.disclosure

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mredrock.cyxbs.common.BaseApp
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.model.bean.CircleData
import com.mredrock.cyxbs.freshman.repository.CampusGuideRepository

/**
* @date 2019-08-02
* @author Override0330
* @description
*/

class BoyAndGirlViewModel : BaseViewModel() {
    private val campusGuideRepository = CampusGuideRepository.getInstant()

    fun getShowList(lifecycleOwner: LifecycleOwner, name:String):MutableLiveData<ArrayList<CircleData>>{
        val showList = MutableLiveData<ArrayList<CircleData>>()
        campusGuideRepository.getBoyAndGirlLiveData(lifecycleOwner,name).observe(lifecycleOwner, Observer {
            val list = ArrayList<CircleData>()
            val boyAngle = it.boy.split("%")[0].toFloat()/100F*360F
            val girlAngle = it.girl.split("%")[0].toFloat()/100F*360F
            if (boyAngle>girlAngle){
                list.add(CircleData(35F, girlAngle, it.girl, BaseApp.context.resources.getColor(R.color.freshman_activity_data_revealed_woman_color)))
                list.add(CircleData(35F + girlAngle, boyAngle, it.boy, BaseApp.context.resources.getColor(R.color.freshman_activity_data_revealed_man_color)))
            }else{
                list.add(CircleData(35F, boyAngle, it.boy, BaseApp.context.resources.getColor(R.color.freshman_activity_data_revealed_man_color)))
                list.add(CircleData(35F + boyAngle, girlAngle, it.girl, BaseApp.context.resources.getColor(R.color.freshman_activity_data_revealed_woman_color)))
            }
            showList.postValue(list)
        })
        return showList
    }
}