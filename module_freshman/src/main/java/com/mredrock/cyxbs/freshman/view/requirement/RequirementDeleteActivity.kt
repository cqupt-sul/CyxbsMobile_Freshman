package com.mredrock.cyxbs.freshman.view.requirement

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mredrock.cyxbs.common.BaseApp.Companion.context
import com.mredrock.cyxbs.common.ui.BaseViewModelActivity
import com.mredrock.cyxbs.common.ui.BaseViewModelFragment
import com.mredrock.cyxbs.common.utils.extensions.gone
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.databinding.FreshmanRecycleItemRequirementBinding
import com.mredrock.cyxbs.freshman.model.bean.RequirementData
import com.mredrock.cyxbs.freshman.view.adapter.BaseRecyclerViewAdapter
import com.mredrock.cyxbs.freshman.view.custom.grouprecyclerview.GroupDecoration
import com.mredrock.cyxbs.freshman.viewmodel.requirement.RequirementDeleteViewModel
import kotlinx.android.synthetic.main.freshman_activity_requirement_memo.*
import kotlinx.android.synthetic.main.freshman_layout_top_toolbar.*

/**
 * Created by yyfbe on 2019-08-09
 */
class RequirementDeleteActivity : BaseViewModelFragment<RequirementDeleteViewModel>() {
    override val viewModelClass: Class<RequirementDeleteViewModel>
        get() = RequirementDeleteViewModel::class.java

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.freshman_activity_requirement_memo,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = BaseRecyclerViewAdapter<FreshmanRecycleItemRequirementBinding
                , RequirementData>(R.layout.freshman_recycle_delete_item_requirement, BR.schoolrequire, null)
        initView()
        //获得viewmodel
        val viewModelFactory = getViewModelFactory()
        viewModel = if (viewModelFactory != null) {
            ViewModelProviders.of(this, viewModelFactory).get(viewModelClass)
        } else {
            ViewModelProviders.of(this).get(viewModelClass)
        }
        //获得数据，配置rv
        viewModel.initData(this).observe(this, Observer {
            if (it.size != 0) tv_no_data_notice.gone()
            adapter.submitShowList(it)
            rv_school_requirement_edit.adapter = adapter
            adapter.onItemOnClickListener = (object : BaseRecyclerViewAdapter.OnItemOnClickListener {
                override fun onItemClick(itemView: View, position: Int) {
                    initItemView(itemView, position)
                }
            })
            this.context?.let { it1 -> GroupDecoration(it, it1) }?.let { it2 -> rv_school_requirement_edit.addItemDecoration(it2) }
            rv_school_requirement_edit.layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?

        })
        //获得点击删除数量
        viewModel.getDeleteCount().observe(this, Observer {
            if (it != 0)
                (tv_right as TextView).text = "删除($it)"
            else (tv_right as TextView).text = "删除"
        })
    }

    //配置view和点击事件
    private fun initView() {
        tv_left.text = "取消"
        tv_center.text = "编辑"
        tv_right.text = "删除"
        tv_right.setOnClickListener {
            findNavController().popBackStack()
        }
        tv_no_data_notice.setOnClickListener {
            findNavController().navigate(R.id.freshman_action_freshman_requirementdeleteactivity_to_freshman_requirementeditactivity)
            findNavController().popBackStack()
        }
        tv_left.setOnClickListener {
            findNavController().popBackStack()
        }
    }
    private fun initItemView(itemView: View, position: Int) {
        if (itemView.findViewById<ImageView>(R.id.iv_require_arrow).isActivated) {
            if (itemView.findViewById<TextView>(R.id.tv_require_detail).text != null) {
                itemView.findViewById<ImageView>(R.id.iv_require_arrow).setImageResource(R.drawable.freshman_arrow_down)
                itemView.findViewById<TextView>(R.id.tv_require_detail).visibility = View.GONE
                itemView.findViewById<ImageView>(R.id.iv_require_arrow).isActivated = false
            }
        } else {
            itemView.findViewById<ImageView>(R.id.iv_require_arrow).setImageResource(R.drawable.freshman_arrow_up)
            itemView.findViewById<TextView>(R.id.tv_require_detail).visibility = View.VISIBLE
            itemView.findViewById<ImageView>(R.id.iv_require_arrow).isActivated = true

        }
    }
}