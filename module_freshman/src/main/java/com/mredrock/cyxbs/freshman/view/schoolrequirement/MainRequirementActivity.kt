package com.mredrock.cyxbs.freshman.view.schoolrequirement

import android.content.Intent
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
import com.mredrock.cyxbs.freshman.view.adapter.BaseRecyclerViewAdapter
import com.mredrock.cyxbs.freshman.view.customui.grouprecyclerview.GroupDecoration
import com.mredrock.cyxbs.freshman.viewmodel.schoolrequirement.MainRequirementViewModel
import kotlinx.android.synthetic.main.freshman_activity_requirement_kind.*
import kotlinx.android.synthetic.main.freshman_linear_layout_top.*

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
        viewModel.initData().observe(this, Observer {
            val adapter = BaseRecyclerViewAdapter<FreshmanRecycleItemRequirementBinding
                    , RequirementData>(R.layout.freshman_recycle_item_requirement, BR.schoolrequire, it)
            rv_school_requirement_kind.adapter = adapter
            adapter.onItemOnClickListener = (object : BaseRecyclerViewAdapter.OnItemOnClickListener {
                override fun onItemClick(itemView: View, position: Int) {
                    Toast.makeText(context, "点击了$position", Toast.LENGTH_SHORT).show()
                    initItemView(itemView, position)
                }
            })
            rv_school_requirement_kind.addItemDecoration(GroupDecoration(it, context))
            rv_school_requirement_kind.layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?
        })
        initView()
    }

    private fun initView() {
        iv_left.setOnClickListener{
            finish()
        }
        iv_right.setOnClickListener {
            startActivity(Intent(MainRequirementActivity@ this, RequirementMemoActivity::class.java))
        }
        fb_edit.setOnClickListener {
            startActivity(Intent(MainRequirementActivity@ this, RequirementEditActivity::class.java))
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.freshman_school_requirement_menu, menu)
        menu?.getItem(0)?.title = "编辑"
        return super.onPrepareOptionsMenu(menu)
    }

    private fun initItemView(itemView: View, position: Int) {
//        Log.d("yyf", position.toString())
//        itemView.findViewById<CheckBox>(R.id.cb_item_requirement).isChecked = false
//        itemView.findViewById<CheckBox>(R.id.cb_item_requirement).setOnCheckedChangeListener { _, isChecked ->
//            when (isChecked) {
//                true -> {
//                    Log.d("yyf", position.toString() + "checked")
//                    itemView.findViewById<TextView>(R.id.tv_require_name).textColor = Color.parseColor("#bfbfbf")
//                    itemView.findViewById<TextView>(R.id.tv_require_detail).textColor = Color.parseColor("#bfbfbf")
//                }
//                false -> {
//                    Log.d("yyf", position.toString() + "unchecked")
//
//                    itemView.findViewById<TextView>(R.id.tv_require_name).textColor = Color.parseColor("#333333")
//                    itemView.findViewById<TextView>(R.id.tv_require_detail).textColor = Color.parseColor("#333333")
//                }
//            }
//        }
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