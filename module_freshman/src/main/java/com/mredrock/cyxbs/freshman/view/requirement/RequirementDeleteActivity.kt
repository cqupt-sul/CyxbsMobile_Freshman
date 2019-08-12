package com.mredrock.cyxbs.freshman.view.requirement

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mredrock.cyxbs.common.BaseApp.Companion.context
import com.mredrock.cyxbs.common.ui.BaseViewModelActivity
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
class RequirementDeleteActivity : BaseViewModelActivity<RequirementDeleteViewModel>() {
    override val viewModelClass: Class<RequirementDeleteViewModel>
        get() = RequirementDeleteViewModel::class.java
    override val isFragmentActivity: Boolean
        get() = false

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.freshman_activity_requirement_memo)
        val adapter = BaseRecyclerViewAdapter<FreshmanRecycleItemRequirementBinding
                , RequirementData>(R.layout.freshman_recycle_delete_item_requirement, BR.schoolrequire, null)
        initView()
        freshman_top_toolbar.init("",
                listener = null)
        val viewModelFactory = getViewModelFactory()
        viewModel = if (viewModelFactory != null) {
            ViewModelProviders.of(this, viewModelFactory).get(viewModelClass)
        } else {
            ViewModelProviders.of(this).get(viewModelClass)
        }
        viewModel.initData(this).observe(this, Observer {
            if (it.size != 0) tv_no_data_notice.gone()
            adapter.submitShowList(it)
            rv_school_requirement_edit.adapter = adapter
            adapter.onItemOnClickListener = (object : BaseRecyclerViewAdapter.OnItemOnClickListener {
                override fun onItemClick(itemView: View, position: Int) {
                    Toast.makeText(context, "点击了$position", Toast.LENGTH_SHORT).show()
                    initItemView(itemView, position)
                }
            })
            rv_school_requirement_edit.addItemDecoration(GroupDecoration(it, context))
            rv_school_requirement_edit.layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?

        })
        viewModel.getDeleteCount().observe(this, Observer {
            if (it != 0)
                (tv_right as TextView).text = "删除($it)"
            else (tv_right as TextView).text = "删除"
        })
    }

    private fun initView() {
        tv_left.text = "取消"
        tv_center.text = "编辑"
        tv_right.text = "删除"
        tv_right.setOnClickListener {
            this.finish()
        }
//        tv_right.setOnClickListener {
//            startActivity(Intent(RequirementDeleteActivity@ this, RequirementEditActivity::class.java))
//        }
        tv_no_data_notice.setOnClickListener {
            startActivity(Intent(this@RequirementDeleteActivity
                    , RequirementEditActivity::class.java))
            finish()
        }
        tv_left.setOnClickListener {
            finish()
        }
    }

    private fun initItemView(itemView: View, position: Int) {
        if (itemView.findViewById<ImageView>(R.id.iv_require_arrow).isActivated) {

            if (itemView.findViewById<TextView>(R.id.tv_require_detail).text != null) {
//                Log.d("yyf", "iv activated")
                itemView.findViewById<ImageView>(R.id.iv_require_arrow).setImageResource(R.drawable.freshman_arrow_down)
                itemView.findViewById<TextView>(R.id.tv_require_detail).visibility = View.GONE
                itemView.findViewById<ImageView>(R.id.iv_require_arrow).isActivated = false
            }
        } else {
//            Log.d("yyf", "iv unactivated")
            itemView.findViewById<ImageView>(R.id.iv_require_arrow).setImageResource(R.drawable.freshman_arrow_up)
            itemView.findViewById<TextView>(R.id.tv_require_detail).visibility = View.VISIBLE
            itemView.findViewById<ImageView>(R.id.iv_require_arrow).isActivated = true

        }
    }
}