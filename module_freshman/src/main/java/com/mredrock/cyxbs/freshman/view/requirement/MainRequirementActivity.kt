package com.mredrock.cyxbs.freshman.view.requirement

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mredrock.cyxbs.common.BaseApp.Companion.context
import com.mredrock.cyxbs.common.ui.BaseViewModelActivity
import com.mredrock.cyxbs.common.ui.BaseViewModelFragment
import com.mredrock.cyxbs.common.utils.LogUtils
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.databinding.FreshmanRecycleItemRequirementBinding
import com.mredrock.cyxbs.freshman.model.InBackgroundEvent
import com.mredrock.cyxbs.freshman.model.SetToolsBarTitle
import com.mredrock.cyxbs.freshman.model.ShowOrHindToolsBarEvent
import com.mredrock.cyxbs.freshman.model.bean.RequirementData
import com.mredrock.cyxbs.freshman.utils.FastClickCheck
import com.mredrock.cyxbs.freshman.view.adapter.BaseRecyclerViewAdapter
import com.mredrock.cyxbs.freshman.view.custom.grouprecyclerview.GroupDecoration
import com.mredrock.cyxbs.freshman.viewmodel.requirement.MainRequirementViewModel
import kotlinx.android.synthetic.main.freshman_activity_requirement_kind.*
import kotlinx.android.synthetic.main.freshman_layout_top_toolbar.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.time.chrono.HijrahChronology

/**
 * Created by yyfbe on 2019-08-08
 */
class MainRequirementActivity : BaseViewModelFragment<MainRequirementViewModel>() {
    override val viewModelClass: Class<MainRequirementViewModel>
        get() = MainRequirementViewModel::class.java

    companion object {
        var checkReset = false
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.freshman_activity_requirement_kind,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        EventBus.getDefault().post(ShowOrHindToolsBarEvent(false))
        //通过ViewModel获得数据
        viewModel.initData(this).observe(this, Observer {
            val adapter = BaseRecyclerViewAdapter<FreshmanRecycleItemRequirementBinding
                    , RequirementData>(R.layout.freshman_recycle_item_requirement, BR.schoolrequire, it)
            rv_school_requirement_kind.adapter = adapter
            adapter.onItemOnClickListener = (object : BaseRecyclerViewAdapter.OnItemOnClickListener {
                override fun onItemClick(itemView: View, position: Int) {
                    initItemView(itemView, position)
                }
            })

            //防止多次设置rv的ItemDecoration
            if (checkReset&&rv_school_requirement_kind.itemDecorationCount!=0) {
                rv_school_requirement_kind.removeItemDecorationAt(0)
            }
            context?.let { it1 -> GroupDecoration(it, it1) }?.let { it2 -> rv_school_requirement_kind.addItemDecoration(it2) }
            checkReset = true
            rv_school_requirement_kind.layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?
        })
        initView()
    }

    private fun initView() {
        //配置标题和一些的点击事件
        tv_right.text = "编辑"
        tv_left.setOnClickListener {
            findNavController().popBackStack()
        }
        tv_right.setOnClickListener {
            if (!FastClickCheck.isFastClick)
                findNavController().navigate(R.id.freshman_action_freshman_mainrequirementactivity_to_freshman_requirementdeleteactivity)
        }
        fb_edit.setOnClickListener {
            if (!FastClickCheck.isFastClick)
                findNavController().navigate(R.id.freshman_action_freshman_mainrequirementactivity_to_freshman_requirementeditactivity)
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
//处在最低层的activity作为发布者
    @Subscribe(threadMode = ThreadMode.ASYNC, sticky = true)
    fun runInBackground(inBackgroundEvent: InBackgroundEvent) {
        run(inBackgroundEvent.event)
        LogUtils.d("debug", "${inBackgroundEvent.event} EventBus IO线程结束")
    }

}