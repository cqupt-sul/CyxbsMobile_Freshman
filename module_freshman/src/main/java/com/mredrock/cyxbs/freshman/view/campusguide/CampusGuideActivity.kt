package com.mredrock.cyxbs.freshman.view.campusguide

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.view.BaseTabLayoutActivity
import kotlinx.android.synthetic.main.freshman_activity_campus_guiide.*


class CampusGuideActivity: BaseTabLayoutActivity() {
    override val titles: List<String>
        get() = listOf("宿舍","食堂","快递","数据揭秘")
    override val fragments: List<Fragment>
        get() = listOf(DormitoryFragment(), DormitoryFragment(), DormitoryFragment(),
                DormitoryFragment())
    override val isFragmentActivity: Boolean
        get() = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.freshman_activity_campus_guiide)
        common_toolbar.init(title="校园指引")
        initTabLayout(vp_campus_guide,tb_campus_guide)
    }
}