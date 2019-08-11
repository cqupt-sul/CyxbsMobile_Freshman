package com.mredrock.cyxbs.freshman.view.schoolorder

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mredrock.cyxbs.common.BaseApp.Companion.context
import com.mredrock.cyxbs.common.ui.BaseViewModelActivity
import com.mredrock.cyxbs.freshman.BR
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.databinding.FreshmanActivityOrderSchoolItemBinding
import com.mredrock.cyxbs.freshman.model.schoolorder.OrderData
import com.mredrock.cyxbs.freshman.view.adapter.BaseRecyclerViewAdapter
import com.mredrock.cyxbs.freshman.viewmodel.schoolorder.OrderViewModel
import kotlinx.android.synthetic.main.freshman_activity_order_school.*

/**
 * Created by yyfbe on 2019-08-10
 */
class OrderActivity : BaseViewModelActivity<OrderViewModel>() {
    override val viewModelClass: Class<OrderViewModel>
        get() = OrderViewModel::class.java
    override val isFragmentActivity: Boolean
        get() = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.freshman_activity_order_school)
        common_toolbar.init("入学流程")
        val viewModelFactory = getViewModelFactory()
        viewModel = if (viewModelFactory != null) {
            ViewModelProviders.of(this, viewModelFactory).get(viewModelClass)
        } else {
            ViewModelProviders.of(this).get(viewModelClass)
        }
        viewModel.initData().observe {
            val adapter = BaseRecyclerViewAdapter<FreshmanActivityOrderSchoolItemBinding
                    , OrderData>(R.layout.freshman_activity_order_school_item, BR.orderData, it)
            rv_order.layoutManager = LinearLayoutManager(context)
            rv_order.adapter = adapter
            adapter.onItemOnClickListener = (object : BaseRecyclerViewAdapter.OnItemOnClickListener {
                override fun onItemClick(itemView: View, position: Int) {
                    Toast.makeText(context, "点击了$position", Toast.LENGTH_SHORT).show()
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
//                Log.d("yyf", "iv activated")
                itemView.findViewById<ImageView>(R.id.iv_order_arrow).setImageResource(R.drawable.freshman_arrow_down)
                itemView.findViewById<TextView>(R.id.tv_order_detail).visibility = View.GONE
                itemView.findViewById<ImageView>(R.id.iv_order_pic).visibility = View.GONE
                itemView.findViewById<ImageView>(R.id.iv_order_arrow).isActivated = false
            }
        }
    }

    fun initItemView(itemView: View, positon: Int) {
        if (itemView.findViewById<ImageView>(R.id.iv_order_arrow).isActivated) {

            if (itemView.findViewById<TextView>(R.id.tv_order_detail).text != null) {
//                Log.d("yyf", "iv activated")
                itemView.findViewById<ImageView>(R.id.iv_order_arrow).setImageResource(R.drawable.freshman_arrow_down)
                itemView.findViewById<TextView>(R.id.tv_order_detail).visibility = View.GONE
                itemView.findViewById<ImageView>(R.id.iv_order_pic).visibility = View.GONE
                itemView.findViewById<ImageView>(R.id.iv_order_arrow).isActivated = false
            }
        } else {
//            Log.d("yyf", "iv unactivated")
            itemView.findViewById<ImageView>(R.id.iv_order_arrow).setImageResource(R.drawable.freshman_arrow_up)
            itemView.findViewById<TextView>(R.id.tv_order_detail).visibility = View.VISIBLE
            itemView.findViewById<ImageView>(R.id.iv_order_pic).visibility = View.VISIBLE
            itemView.findViewById<ImageView>(R.id.iv_order_arrow).isActivated = true

        }

    }
}

