package com.mredrock.cyxbs.freshman.view.goto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mredrock.cyxbs.common.utils.LogUtils
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.model.SetToolsBarTitle
import com.mredrock.cyxbs.freshman.view.BaseTabLayoutFragment
import com.mredrock.cyxbs.freshman.viewmodel.goto.GoToCquptViewModel
import kotlinx.android.synthetic.main.freshman_fragmnet_main_gotocqupt.*
import org.greenrobot.eventbus.EventBus

/**
 * @date 2019-08-04
 * @author Override0330
 * @description
 */

class GoToCquptFragment : BaseTabLayoutFragment<GoToCquptViewModel>(GoToCquptViewModel::class.java){
    override val viewModelClass: Class<GoToCquptViewModel>
        get() = GoToCquptViewModel::class.java

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        LogUtils.d("生命周期监听","${this} onCreateView")
        return inflater.inflate(R.layout.freshman_fragmnet_main_gotocqupt,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LogUtils.d("生命周期监听","${this} onViewCreate")
        EventBus.getDefault().post(SetToolsBarTitle("指路重邮"))
        initTabLayout(vp_goto_cqupt,tb_goto_cqupt,listOf("公交线路","校园风光"),listOf(BusLineFragment(), CollegeSceneryFragment()))
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtils.d("生命周期监听","${this} onDestroy")
    }
}