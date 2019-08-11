package com.mredrock.cyxbs.freshman.view.schoolrequirement

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.*
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mredrock.cyxbs.common.BaseApp.Companion.context
import com.mredrock.cyxbs.common.ui.BaseViewModelActivity
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.databinding.FreshmanRecycleItemRequirementBinding
import com.mredrock.cyxbs.freshman.model.schoolrequirement.RequirementData
import com.mredrock.cyxbs.freshman.utils.FastClickCheck
import com.mredrock.cyxbs.freshman.view.adapter.BaseRecyclerViewAdapter
import com.mredrock.cyxbs.freshman.view.customui.grouprecyclerview.GroupDecoration
import com.mredrock.cyxbs.freshman.viewmodel.schoolrequirement.MainRequirementViewModel
import kotlinx.android.synthetic.main.freshman_activity_requirement_kind.*
import kotlinx.android.synthetic.main.freshman_layout_top_toolbar.*
import org.jetbrains.anko.leftPadding

/**
 * Created by yyfbe on 2019-08-08
 */
class MainRequirementActivity : BaseViewModelActivity<MainRequirementViewModel>() {
    override val viewModelClass: Class<MainRequirementViewModel>
        get() = MainRequirementViewModel::class.java
    override val isFragmentActivity: Boolean
        get() = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.freshman_activity_requirement_kind)
        freshman_top_toolbar.init("入学流程")
        val viewModelFactory = getViewModelFactory()
        viewModel = if (viewModelFactory != null) {
            ViewModelProviders.of(this, viewModelFactory).get(viewModelClass)
        } else {
            ViewModelProviders.of(this).get(viewModelClass)
        }
        viewModel.initData().observe(this, Observer {
            val adapter = BaseRecyclerViewAdapter<FreshmanRecycleItemRequirementBinding
                    , RequirementData>(R.layout.freshman_recycle_item_requirement, BR.schoolrequire, it)
            rv_school_requirement_kind.adapter = adapter
            adapter.onItemOnClickListener = (object : BaseRecyclerViewAdapter.OnItemOnClickListener {
                override fun onItemClick(itemView: View, position: Int) {
                    initItemView(itemView, position)
                }
            })
            rv_school_requirement_kind.addItemDecoration(GroupDecoration(it, context))
            rv_school_requirement_kind.layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?
        })
        initView()
    }

    private fun initView() {
        tv_right.text="编辑"
        tv_left.setOnClickListener{
            finish()
        }
        tv_right.setOnClickListener {
            if (!FastClickCheck.isFastClick)
            startActivity(Intent(MainRequirementActivity@ this, RequirementMemoActivity::class.java))
        }
        fb_edit.setOnClickListener {
            if (!FastClickCheck.isFastClick)
            startActivity(Intent(MainRequirementActivity@ this, RequirementEditActivity::class.java))
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