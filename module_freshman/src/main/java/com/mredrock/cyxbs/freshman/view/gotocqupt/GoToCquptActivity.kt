package com.mredrock.cyxbs.freshman.view.gotocqupt

import android.os.Bundle
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.view.BaseTabLayoutActivity
import com.mredrock.cyxbs.freshman.viewmodel.gotocqupt.GoToCquptViewModel
import kotlinx.android.synthetic.main.freshman_activity_gotocqupt.*

/**
 * @date 2019-08-04
 * @author Override0330
 * @description
 */

class GoToCquptActivity : BaseTabLayoutActivity<GoToCquptViewModel>(){
    override val viewModelClass: Class<GoToCquptViewModel>
        get() = GoToCquptViewModel::class.java
    override val isFragmentActivity: Boolean
        get() = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.freshman_activity_gotocqupt)
        common_toolbar.init(title = "指路重邮")
        initTabLayout(vp_goto_cqupt,tb_goto_cqupt,listOf("公交线路","校园风光"),listOf(BusLineFragment(), CollegeSceneryFragment()))
    }
}