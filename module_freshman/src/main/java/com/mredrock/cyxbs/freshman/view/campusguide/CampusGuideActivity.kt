package com.mredrock.cyxbs.freshman.view.campusguide

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.view.BaseTabLayoutActivity
import com.mredrock.cyxbs.freshman.viewmodel.campusguide.CampusGuideViewModel
import kotlinx.android.synthetic.main.freshman_activity_campus_guiide.*


class CampusGuideActivity : BaseTabLayoutActivity<CampusGuideViewModel>() {
    override val viewModelClass: Class<CampusGuideViewModel>
        get() = CampusGuideViewModel::class.java

    override val isFragmentActivity: Boolean
        get() = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.freshman_activity_campus_guiide)
        common_toolbar.init(title = "校园指引")
        initTabLayout(vp_campus_guide, tb_campus_guide, listOf("宿舍", "食堂", "快递", "数据揭秘"),
                listOf(DormitoryFragment(), CanteenFragment(), ExpressFragment(),
                        DataDisclosureFragment()))
        vp_campus_guide.offscreenPageLimit = 4
    }
}