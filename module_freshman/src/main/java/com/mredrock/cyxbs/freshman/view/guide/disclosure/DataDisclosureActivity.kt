package com.mredrock.cyxbs.freshman.view.guide.disclosure

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.model.SetToolsBarTitle
import com.mredrock.cyxbs.freshman.view.BaseTabLayoutFragment
import com.mredrock.cyxbs.freshman.view.guide.CampusGuideFragmentArgs

import com.mredrock.cyxbs.freshman.viewmodel.disclosure.DataDisclosureActivityViewModel
import kotlinx.android.synthetic.main.freshman_activity_data_disclosure.*
import org.greenrobot.eventbus.EventBus


class DataDisclosureActivity : BaseTabLayoutFragment<DataDisclosureActivityViewModel>(DataDisclosureActivityViewModel::class.java) {
    override val viewModelClass: Class<DataDisclosureActivityViewModel>
        get() = DataDisclosureActivityViewModel::class.java

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.freshman_activity_data_disclosure,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments?.let { CampusGuideFragmentArgs.fromBundle(it) }
        if (args!=null){
            val school = args.school
            EventBus.getDefault().post(SetToolsBarTitle("数据揭秘"))
            initTabLayout(vp_data_disclosure, tb_data_disclosure,
                    listOf("学科数据", "男女比例"),
                    listOf(SubjectFragment.getSubjectFragment(school), BoyAndGirlFragment.getBoyAndGirlFragment(school)))
        }
    }

}