package com.mredrock.cyxbs.freshman.view.online

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mredrock.cyxbs.common.ui.BaseViewModelFragment
import com.mredrock.cyxbs.common.utils.LogUtils
import com.mredrock.cyxbs.freshman.BR
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.databinding.FreshmanRecycleItemOnlineActivityBinding
import com.mredrock.cyxbs.freshman.model.ShowActivityDialog
import com.mredrock.cyxbs.freshman.model.item.ActivityItem
import com.mredrock.cyxbs.freshman.view.adapter.BaseRecyclerViewAdapter
import com.mredrock.cyxbs.freshman.viewmodel.online.OnlineActivityViewModel
import kotlinx.android.synthetic.main.freshman_freshamn_online_activity.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * @date 2019-08-09
 * @author Override0330
 * @description
 */

class OnlineActivityFragment :BaseViewModelFragment<OnlineActivityViewModel>(){
    override val viewModelClass: Class<OnlineActivityViewModel>
        get() = OnlineActivityViewModel::class.java

    val adapter  = BaseRecyclerViewAdapter<FreshmanRecycleItemOnlineActivityBinding,ActivityItem>(
            R.layout.freshman_recycle_item_online_activity,
            BR.activityItem, null)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.freshman_freshamn_online_activity,container,false)
    }

    override fun onStart() {
        super.onStart()
        rv_online.layoutManager = LinearLayoutManager(this.context)
        rv_online.adapter = adapter
        viewModel.getOnlineActivityLiveData(viewLifecycleOwner).observe{
            if (it != null) {
                LogUtils.d("LiveData","触发线上活动的回调")
                adapter.submitShowList(it)
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun showActivityDialog(showActivityDialog: ShowActivityDialog){
        val joinActivityDialogFragment = JoinActivityDialogFragment()
        joinActivityDialogFragment.init(showActivityDialog.photoUrl,showActivityDialog.message)
        joinActivityDialogFragment.show(fragmentManager,"join now")
    }
}