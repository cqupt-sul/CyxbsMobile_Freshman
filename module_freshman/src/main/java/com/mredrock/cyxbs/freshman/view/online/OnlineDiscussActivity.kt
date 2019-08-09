package com.mredrock.cyxbs.freshman.view.online

import android.os.Bundle
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.view.BaseTabLayoutActivity
import com.mredrock.cyxbs.freshman.view.nannvbili.NannvbiliFragment
import com.mredrock.cyxbs.freshman.viewmodel.online.OnlineDiscussViewModel
import com.umeng.commonsdk.UMConfigure
import kotlinx.android.synthetic.main.freshman_activity_online_discuss.*

/**
 * @date 2019-08-08
 * @author Override0330
 * @description
 */
class OnlineDiscussActivity :BaseTabLayoutActivity<OnlineDiscussViewModel>(){
    override val viewModelClass: Class<OnlineDiscussViewModel>
        get() = OnlineDiscussViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.freshman_activity_online_discuss)
        UMConfigure.setLogEnabled(true)
        initTabLayout(vp_online_discuss,tb_online_discuss, listOf("学员群","老乡群","线上活动"), listOf(GroupFragment.getInstantWithTag("学院群"),GroupFragment.getInstantWithTag("老乡群"),OnlineActivityFragment()))
    }
}