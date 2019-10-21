package com.mredrock.cyxbs.freshman.view.requirement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.mredrock.cyxbs.common.ui.BaseViewModelActivity
import com.mredrock.cyxbs.common.ui.BaseViewModelFragment
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.model.ShowOrHindToolsBarEvent
import com.mredrock.cyxbs.freshman.viewmodel.requirement.RequirementEditViewModel
import kotlinx.android.synthetic.main.freshman_activity_requirement_edit.*
import kotlinx.android.synthetic.main.freshman_layout_top_toolbar.*
import org.greenrobot.eventbus.EventBus

/**
 * Created by yyfbe on 2019-08-09
 */
class RequirementEditActivity : BaseViewModelFragment<RequirementEditViewModel>() {
    override val viewModelClass: Class<RequirementEditViewModel>
        get() = RequirementEditViewModel::class.java


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        EventBus.getDefault().post(ShowOrHindToolsBarEvent(false))
        return inflater.inflate(R.layout.freshman_activity_requirement_edit,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModelFactory = getViewModelFactory()
        viewModel = if (viewModelFactory != null) {
            ViewModelProviders.of(this, viewModelFactory).get(viewModelClass)
        } else {
            ViewModelProviders.of(this).get(viewModelClass)
        }
        initView(viewModel)
    }

    private fun initView(viewModel: RequirementEditViewModel) {
        tv_center.text = "备忘录"
        tv_left.text = "取消"
        tv_right.text = "保存"
        tv_left.setOnClickListener {
            findNavController().popBackStack()
        }
        tv_right.setOnClickListener {
            viewModel.getEditText(et_requirement_edit.text.toString())
            findNavController().popBackStack()
        }
    }
}