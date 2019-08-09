package com.mredrock.cyxbs.freshman.view.schoolrequirement

import android.content.Intent
import android.database.DatabaseUtils
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
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
import com.mredrock.cyxbs.freshman.model.schoolrequirement.RequirementData
import com.mredrock.cyxbs.freshman.view.adapter.BaseRecyclerViewAdapter
import com.mredrock.cyxbs.freshman.view.customui.grouprecyclerview.GroupDecoration
import com.mredrock.cyxbs.freshman.viewmodel.schoolrequirement.MainRequirementViewModel
import com.mredrock.cyxbs.freshman.viewmodel.schoolrequirement.RequirementDeleteViewModel
import kotlinx.android.synthetic.main.freshman_activity_requirement_kind.*
import kotlinx.android.synthetic.main.freshman_activity_requirement_memo.*
import kotlinx.android.synthetic.main.freshman_linear_layout_top.*
import kotlinx.android.synthetic.main.freshman_linear_layout_top.tv_center
import kotlinx.android.synthetic.main.freshman_linear_layout_top.tv_left
import kotlinx.android.synthetic.main.freshman_linear_layout_top.tv_right
import org.jetbrains.anko.sdk27.coroutines.onClick

/**
 * Created by yyfbe on 2019-08-09
 */
class RequirementMemoActivity : BaseViewModelActivity<RequirementDeleteViewModel>() {
    override val viewModelClass: Class<RequirementDeleteViewModel>
        get() = RequirementDeleteViewModel::class.java
    override val isFragmentActivity: Boolean
        get() = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.freshman_activity_requirement_memo)
        initView()
        val viewModelFactory = getViewModelFactory()
        viewModel = if (viewModelFactory != null) {
            ViewModelProviders.of(this, viewModelFactory).get(viewModelClass)
        } else {
            ViewModelProviders.of(this).get(viewModelClass)
        }
        viewModel.initData(tv_right).observe(this, Observer {
            if (it.size != 0) tv_no_data_notice.gone()
            val adapter = BaseRecyclerViewAdapter<FreshmanRecycleItemRequirementBinding
                    , RequirementData>(R.layout.freshman_recycle_item_requirement, BR.schoolrequire, it)
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
    }

    private fun initView() {
        tv_left.text = "取消"
        tv_center.text = "编辑"
        tv_right.text = "删除"
//        tv_right.setOnClickListener {
//            startActivity(Intent(RequirementMemoActivity@ this, RequirementEditActivity::class.java))
//        }
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