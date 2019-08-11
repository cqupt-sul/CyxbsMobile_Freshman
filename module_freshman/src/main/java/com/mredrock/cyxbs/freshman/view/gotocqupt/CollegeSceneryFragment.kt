package com.mredrock.cyxbs.freshman.view.gotocqupt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.mredrock.cyxbs.common.BaseApp
import com.mredrock.cyxbs.common.ui.BaseViewModelFragment
import com.mredrock.cyxbs.common.utils.LogUtils
import com.mredrock.cyxbs.common.utils.extensions.setImageFromUrl
import com.mredrock.cyxbs.freshman.BR
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.databinding.FreshmanFragmentCollegeSceneryBinding
import com.mredrock.cyxbs.freshman.model.item.CollegePicItem
import com.mredrock.cyxbs.freshman.view.adapter.BaseRecyclerViewAdapter
import com.mredrock.cyxbs.freshman.viewmodel.gotocqupt.CollegeSceneryViewModel
import kotlinx.android.synthetic.main.freshman_fragment_college_scenery.*

/**
 * @date 2019-08-05
 * @author Override0330
 * @description
 */

class CollegeSceneryFragment : BaseViewModelFragment<CollegeSceneryViewModel>() {
    override val viewModelClass: Class<CollegeSceneryViewModel>
        get() = CollegeSceneryViewModel::class.java

    val adapter = BaseRecyclerViewAdapter<FreshmanFragmentCollegeSceneryBinding,CollegePicItem>(R.layout.freshman_recycler_item_college_pic,
            BR.collegePicItem,
            null)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.freshman_fragment_college_scenery, container, false)
    }

    override fun onStart() {
        super.onStart()
        LogUtils.d("生命周期监听", "${this} onStart")
        rv_goto_college_pic.layoutManager = LinearLayoutManager(this.context)
        rv_goto_college_pic.adapter

        /**
         * 1. 拿到地图的URL
         * 2. 拿到下面Recyclerview的List<Scenery>
         */
        viewModel.getMapLiveData(viewLifecycleOwner).observe {
            if (it != null){
                tv_goto_map.text = it.title
                iv_goto_map.setImageFromUrl(it.photoUrl)
            }
        }

        viewModel.getSceneryListLiveData(viewLifecycleOwner).observe{
            if (it != null){
                adapter.submitShowList(it)
            }
        }
    }
}