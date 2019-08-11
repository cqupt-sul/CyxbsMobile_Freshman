package com.mredrock.cyxbs.freshman.view.campusguide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.view.BaseTabLayoutActivity
import com.mredrock.cyxbs.freshman.view.BaseTabLayoutFragment
import com.mredrock.cyxbs.freshman.viewmodel.campusguide.CampusGuideViewModel
import kotlinx.android.synthetic.main.freshman_activity_campus_guiide.*
import org.greenrobot.eventbus.EventBus


class CampusGuideFragment : BaseTabLayoutFragment<CampusGuideViewModel>(CampusGuideViewModel::class.java) {
    override val viewModelClass: Class<CampusGuideViewModel>
        get() = CampusGuideViewModel::class.java

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.freshman_activity_campus_guiide,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        EventBus.getDefault().post("校园指引")
        initTabLayout(vp_campus_guide, tb_campus_guide, listOf("宿舍", "食堂", "快递", "数据揭秘"),
                listOf(DormitoryFragment(), CanteenFragment(), ExpressFragment(),
                        DataDisclosureFragment()))
        vp_campus_guide.offscreenPageLimit = 4
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}