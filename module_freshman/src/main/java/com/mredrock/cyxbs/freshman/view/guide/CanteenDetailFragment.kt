package com.mredrock.cyxbs.freshman.view.guide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mredrock.cyxbs.common.ui.BaseViewModelFragment
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.view.adapter.BannerImageLoader
import com.mredrock.cyxbs.freshman.viewmodel.guide.CanteenDetailViewModel
import kotlinx.android.synthetic.main.freshman_fragment_canteen_detail.*

/**
 * Created by yyfbe on 2019-08-07
 */
class CanteenDetailFragment: BaseViewModelFragment<CanteenDetailViewModel>() {
    override val viewModelClass: Class<CanteenDetailViewModel>
        get() = CanteenDetailViewModel::class.java

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val tag = arguments?.getString("tag")
        if (tag!=null){
            viewModel.getCanteenLiveData(viewLifecycleOwner,tag).observe{
                if (it!=null){
                    tv_canteen_detail_name.text = it.name
                    tv_canteen_detail_content.text = it.detail
                    //轮播图初始化
                    val list=it.url.split(",")
                    freshman_canteen_banner.setImages(list).setImageLoader(BannerImageLoader()).start()
                }
            }
        }
        return inflater.inflate(R.layout.freshman_fragment_canteen_detail, container, false)
    }

    companion object {
        fun newInstance(tag: String): CanteenDetailFragment {
            val args = Bundle()
            args.putString("tag", tag)
            val fragment = CanteenDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }
}