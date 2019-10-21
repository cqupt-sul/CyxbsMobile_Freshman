package com.mredrock.cyxbs.freshman.view.online

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.model.SetToolsBarTitle
import com.mredrock.cyxbs.freshman.view.BaseTabLayoutFragment
import com.mredrock.cyxbs.freshman.viewmodel.online.OnlineDiscussViewModel
import kotlinx.android.synthetic.main.freshman_activity_online_discuss.*
import org.greenrobot.eventbus.EventBus

/**
 * @date 2019-08-08
 * @author Override0330
 * @description
 */
class OnlineDiscussFragment :BaseTabLayoutFragment<OnlineDiscussViewModel>(OnlineDiscussViewModel::class.java) {
    override val viewModelClass: Class<OnlineDiscussViewModel>
        get() = OnlineDiscussViewModel::class.java

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.freshman_activity_online_discuss, container, false)
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().post(SetToolsBarTitle("线上活动"))
        initTabLayout(vp_online_discuss,tb_online_discuss, listOf("学员群","老乡群","线上活动"), listOf(GroupFragment.getInstantWithTag("学院群"),GroupFragment.getInstantWithTag("老乡群"),OnlineActivityFragment()))
    }
}