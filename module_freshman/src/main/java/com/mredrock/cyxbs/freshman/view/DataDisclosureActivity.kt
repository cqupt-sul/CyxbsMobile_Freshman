package com.mredrock.cyxbs.freshman.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mredrock.cyxbs.freshman.BR
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.databinding.FreshmanRecycleItemHomeBinding
import com.mredrock.cyxbs.freshman.model.campusguide.InstituteData
import com.mredrock.cyxbs.freshman.view.adapter.BaseRecyclerViewAdapter
import com.mredrock.cyxbs.freshman.view.customui.chartview.SubjectFragment
import com.mredrock.cyxbs.freshman.view.nannvbili.NannvbiliFragment
import com.mredrock.cyxbs.freshman.viewmodel.DataDisclosureActivityViewModel
import com.mredrock.cyxbs.freshman.viewmodel.campusguide.DataDisclosureFragmentViewModel
import kotlinx.android.synthetic.main.freshman_activity_data_disclosure.*
import kotlinx.android.synthetic.main.freshman_fragment_data_disclosure.*
import kotlin.collections.ArrayList


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