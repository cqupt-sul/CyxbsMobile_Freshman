package com.mredrock.cyxbs.freshman.view.goto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.LinearLayoutManager
import com.mredrock.cyxbs.common.BaseApp
import com.mredrock.cyxbs.common.ui.BaseViewModelFragment
import com.mredrock.cyxbs.common.viewmodel.event.ProgressDialogEvent
import com.mredrock.cyxbs.freshman.BR
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.databinding.FreshmanRecycleItemBusLineBinding
import com.mredrock.cyxbs.freshman.model.item.BusLineItem
import com.mredrock.cyxbs.freshman.view.adapter.BaseRecyclerViewAdapter
import com.mredrock.cyxbs.freshman.viewmodel.goto.BusLineViewModel
import kotlinx.android.synthetic.main.freshman_fragment_busline.*

/**
 * @date 2019-08-05
 * @author Override0330
 * @description
 */
class BusLineFragment : BaseViewModelFragment<BusLineViewModel>(){
    override val viewModelClass: Class<BusLineViewModel>
        get() = BusLineViewModel::class.java

    val adapter = BaseRecyclerViewAdapter<FreshmanRecycleItemBusLineBinding,BusLineItem>(R.layout.freshman_recycle_item_bus_line,
            BR.busLine,null)

    val animToOpen = AnimationUtils.loadAnimation(BaseApp.context,R.anim.freshman_rotate_clockwise_002)
    val animToClose = AnimationUtils.loadAnimation(BaseApp.context,R.anim.freshman_rotate_clockwise_003)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate( R.layout.freshman_fragment_busline,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.progressDialogEvent.value = ProgressDialogEvent.SHOW_CANCELABLE_DIALOG_EVENT
        viewModel.getBusLine(viewLifecycleOwner).observe{
            if (adapter.itemCount==0&&it != null) {
                adapter.submitShowList(it)
                if (viewModel.progressDialogEvent.value==ProgressDialogEvent.SHOW_CANCELABLE_DIALOG_EVENT){
                    viewModel.progressDialogEvent.value = ProgressDialogEvent.DISMISS_DIALOG_EVENT
                }
            }else{
                if (viewModel.progressDialogEvent.value==ProgressDialogEvent.SHOW_CANCELABLE_DIALOG_EVENT){
                    viewModel.progressDialogEvent.value = ProgressDialogEvent.DISMISS_DIALOG_EVENT
                }
            }
        }
        viewModel.getAddress(viewLifecycleOwner).observe{
            if (it != null){
                tv_bus_line_school_name.text = it.title
                tv_bus_line_school_address.text = it.address
                if (viewModel.progressDialogEvent.value==ProgressDialogEvent.SHOW_CANCELABLE_DIALOG_EVENT){
                    viewModel.progressDialogEvent.value = ProgressDialogEvent.DISMISS_DIALOG_EVENT
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        rv_goto_bus_line.layoutManager = LinearLayoutManager(this.context)
        rv_goto_bus_line.adapter = adapter
        adapter.onItemOnClickListener = object :BaseRecyclerViewAdapter.OnItemOnClickListener{
            override fun onItemClick(itemView: View, position: Int) {
                with(itemView.findViewById<View>(R.id.tv_route)){
                    if (this.visibility==View.VISIBLE){
                        itemView.findViewById<View>(R.id.iv_bus_line_arrow).startAnimation(animToClose)
                        this.visibility = View.GONE
                    }else{
                        itemView.findViewById<View>(R.id.iv_bus_line_arrow).startAnimation(animToOpen)
                        this.visibility = View.VISIBLE
                    }
                }
            }
        }
        tv_copy_address.setOnClickListener {
            //打开一个复制的dialog
            viewModel.getAddress(viewLifecycleOwner)
            viewModel.getBusLine(viewLifecycleOwner)
            val copySuccessDialogFragment = CopySuccessDialogFragment()
            copySuccessDialogFragment.show(fragmentManager,"copy success")
        }
    }
}