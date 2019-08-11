package com.mredrock.cyxbs.freshman.view.gotocqupt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.mredrock.cyxbs.common.BaseApp
import com.mredrock.cyxbs.common.ui.BaseViewModelFragment
import com.mredrock.cyxbs.common.utils.LogUtils
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.viewmodel.gotocqupt.CollegeSceneryViewModel
import kotlinx.android.synthetic.main.freshman_fragment_college_scenery.*

/**
 * @date 2019-08-05
 * @author Override0330
 * @description
 */

class CollegeSceneryFragment : BaseViewModelFragment<CollegeSceneryViewModel>() {
    override val viewModelClass: Class<CollegeSceneryViewModel>
        get() = CollegeSceneryViewModel::class.java

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.freshman_fragment_college_scenery, container, false)
    }

    override fun onStart() {
        super.onStart()
        LogUtils.d("生命周期监听", "${this} onStart")
        /**
         * 1. 拿到地图的URL
         * 2. 拿到下面Recyclerview的List<Scenery>
         */
        viewModel.getMapLiveData(viewLifecycleOwner).observe {
            if (it != null){
                tv_goto_map.text = it.title
                Glide.get(BaseApp.context).
            }
        }
    }
}