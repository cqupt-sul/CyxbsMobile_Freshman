package com.mredrock.cyxbs.freshman.view

import android.os.Bundle
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.view.customui.chartview.SubjectFragment
import com.mredrock.cyxbs.freshman.view.nannvbili.NannvbiliFragment

import com.mredrock.cyxbs.freshman.viewmodel.datadisclosure.DataDisclosureActivityViewModel
import kotlinx.android.synthetic.main.freshman_activity_data_disclosure.*


class DataDisclosureActivity : BaseTabLayoutActivity<DataDisclosureActivityViewModel>() {
    override val viewModelClass: Class<DataDisclosureActivityViewModel>
        get() = DataDisclosureActivityViewModel::class.java
    override val isFragmentActivity: Boolean
        get() = true

    //    private val fragments: List<Fragment> = listOf(SubjectFragment(), NannvbiliFragment())
//    private val titles: List<String> = listOf("学科数据", "男女比例")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.freshman_activity_data_disclosure)
        common_toolbar.init(title = "数据揭秘")
        initTabLayout(vp_data_disclosure, tb_data_disclosure,
                listOf("学科数据", "男女比例"),
                listOf(SubjectFragment(), NannvbiliFragment()))

    }

}