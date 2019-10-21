package com.mredrock.cyxbs.freshman.view.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mredrock.cyxbs.common.BaseApp.Companion.context
import com.mredrock.cyxbs.common.ui.BaseViewModelActivity
import com.mredrock.cyxbs.common.ui.BaseViewModelFragment
import com.mredrock.cyxbs.freshman.BR
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.databinding.FreshmanActivityOrderSchoolItemBinding
import com.mredrock.cyxbs.freshman.model.SetToolsBarTitle
import com.mredrock.cyxbs.freshman.model.bean.OrderData
import com.mredrock.cyxbs.freshman.model.remote.api.basePicUrl
import com.mredrock.cyxbs.freshman.view.adapter.BaseRecyclerViewAdapter
import com.mredrock.cyxbs.freshman.viewmodel.order.OrderViewModel
import kotlinx.android.synthetic.main.freshman_activity_order_school.*
import org.greenrobot.eventbus.EventBus

/**
 * Created by yyfbe on 2019-08-10
 */
class OrderFragment : BaseViewModelFragment<OrderViewModel>() {

    override val viewModelClass: Class<OrderViewModel>
        get() = OrderViewModel::class.java

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.freshman_activity_order_school, container, false)
    }

    private var orderDataList: List<OrderData>? = null
    override fun onStart() {
        super.onStart()
        EventBus.getDefault().post(SetToolsBarTitle("入学流程"))
        //获得数据
        viewModel.initData(this).observe { orderItem ->
            if (orderItem != null) {
                tv_order_register.text = (orderItem[0].title + orderItem[0].message)
                orderDataList = orderItem.map { OrderData(it.title, it.message, basePicUrl + it.photo, it.detail) }
            }
            //应用数据到rv,排除第一个元素
            //配置rv
            val adapter = BaseRecyclerViewAdapter<FreshmanActivityOrderSchoolItemBinding,
                    OrderData>(R.layout.freshman_activity_order_school_item, BR.orderData,
                    orderDataList?.filter { it.title.get().toString() != orderItem?.get(0)?.title })
            rv_order.layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?
            rv_order.adapter = adapter
            adapter.onItemOnClickListener = (object : BaseRecyclerViewAdapter.OnItemOnClickListener {
                override fun onItemClick(itemView: View, position: Int) {
                    initItemView(itemView, position)
                    for (i in 0 until adapter.itemCount) {
                        if (itemView != rv_order.layoutManager?.getChildAt(i)) {
                            rv_order.layoutManager?.getChildAt(i)?.let { it1 -> resetItemView(it1) }
                        }
                    }
                }
            })
        }
    }

    fun resetItemView(itemView: View) {
        if (itemView.findViewById<ImageView>(R.id.iv_order_arrow).isActivated) {

            if (itemView.findViewById<TextView>(R.id.tv_order_detail).text != null) {
                itemView.findViewById<ImageView>(R.id.iv_order_arrow).setImageResource(R.drawable.freshman_arrow_down)
                itemView.findViewById<TextView>(R.id.tv_order_detail).visibility = View.GONE
                itemView.findViewById<ImageView>(R.id.iv_order_pic).visibility = View.GONE
                itemView.findViewById<ImageView>(R.id.iv_order_arrow).isActivated = false
            }
        }
    }

    fun initItemView(itemView: View, position: Int) {
        //箭头被点击
        if (itemView.findViewById<ImageView>(R.id.iv_order_arrow).isActivated) {
            if (itemView.findViewById<TextView>(R.id.tv_order_detail).text != null) {
                itemView.findViewById<ImageView>(R.id.iv_order_arrow).setImageResource(R.drawable.freshman_arrow_down)
                itemView.findViewById<TextView>(R.id.tv_order_detail).visibility = View.GONE
                itemView.findViewById<ImageView>(R.id.iv_order_pic).visibility = View.GONE
                itemView.findViewById<ImageView>(R.id.iv_order_arrow).isActivated = false
            }
        } else {
            itemView.findViewById<ImageView>(R.id.iv_order_arrow).setImageResource(R.drawable.freshman_arrow_up)
            itemView.findViewById<TextView>(R.id.tv_order_detail).visibility = View.VISIBLE
            //排除没有图片的情况
            if (checkPic(position))
                itemView.findViewById<ImageView>(R.id.iv_order_pic).visibility = View.VISIBLE
            itemView.findViewById<ImageView>(R.id.iv_order_arrow).isActivated = true

        }

    }

    //检查没有图片的情况
    private fun checkPic(position: Int) = (orderDataList?.get(position + 1)?.photo?.get() != basePicUrl)
}

