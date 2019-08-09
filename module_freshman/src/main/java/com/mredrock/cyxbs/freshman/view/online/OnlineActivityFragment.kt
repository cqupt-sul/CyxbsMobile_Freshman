package com.mredrock.cyxbs.freshman.view.online

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mredrock.cyxbs.common.ui.BaseViewModelFragment
import com.mredrock.cyxbs.common.utils.LogUtils
import com.mredrock.cyxbs.freshman.BR
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.databinding.FreshmanRecycleItemOnlineActivityBinding
import com.mredrock.cyxbs.freshman.model.item.ActivityItem
import com.mredrock.cyxbs.freshman.view.adapter.BaseRecyclerViewAdapter
import com.mredrock.cyxbs.freshman.view.gotocqupt.CopySuccessDialogFragment
import com.mredrock.cyxbs.freshman.viewmodel.online.OnlineActivityViewModel
import kotlinx.android.synthetic.main.freshman_freshamn_online_activity.*

/**
 * @date 2019-08-09
 * @author Override0330
 * @description
 */
class OnlineActivityFragment :BaseViewModelFragment<OnlineActivityViewModel>(){
    override val viewModelClass: Class<OnlineActivityViewModel>
        get() = OnlineActivityViewModel::class.java

    val showDialog:(it:ActivityItem?)->Unit = {
        if (it!=null){
            LogUtils.d("回调监听","触发了立刻参与的点击回调 ${viewModel.showDialog}")
//            Toast.makeText(this.context,it?.activityName?.get(),Toast.LENGTH_SHORT).show()
            val joinActivityDialogFragment = JoinActivityDialogFragment()
            joinActivityDialogFragment.show(fragmentManager,"join now")
            viewModel.showDialog.value = null
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        viewModel.showDialog.observe(showDialog)
        LogUtils.d("生命周期监听","$this onCreateView ${viewModel.showDialog}")
        return inflater.inflate(R.layout.freshman_freshamn_online_activity,container,false)
    }

    override fun onStart() {
        super.onStart()
        val adapter  = BaseRecyclerViewAdapter<FreshmanRecycleItemOnlineActivityBinding,ActivityItem>(
                R.layout.freshman_recycle_item_online_activity,
                BR.activityItem,
                viewModel.showList)
        rv_online.layoutManager = LinearLayoutManager(this.context)
        rv_online.adapter = adapter
    }

    override fun onPause() {
        super.onPause()
        LogUtils.d("生命周期监听","$this onPause ${viewModel.showDialog}")
//        viewModel.showDialog.removeObserver(showDialog)
    }

    override fun onStop() {
        super.onStop()
        LogUtils.d("生命周期监听","$this onStop ${viewModel.showDialog}")
    }
}