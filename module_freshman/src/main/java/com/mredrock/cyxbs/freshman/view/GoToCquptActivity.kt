package com.mredrock.cyxbs.freshman.view

import android.os.Bundle
import android.widget.ViewSwitcher
import androidx.fragment.app.Fragment
import com.mredrock.cyxbs.common.ui.BaseViewModelActivity
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.viewmodel.GoToCquptViewModel
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
    override val fragments: List<Fragment>
        get() = listOf(BusLineFragment(),CollegeSceneryFragment())
    override val titles: List<String>
        get() = listOf("公交线路","校园风光")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.freshman_activity_gotocqupt)
        common_toolbar.init(title = "指路重邮")
        initTabLayout(vp_goto_cqupt,tb_goto_cqupt)
    }
}