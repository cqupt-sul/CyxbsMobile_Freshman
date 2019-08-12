package com.mredrock.cyxbs.freshman.view.requirement

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.mredrock.cyxbs.common.ui.BaseViewModelActivity
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.viewmodel.requirement.RequirementEditViewModel
import kotlinx.android.synthetic.main.freshman_activity_requirement_edit.*
import kotlinx.android.synthetic.main.freshman_layout_top_toolbar.*

/**
 * Created by yyfbe on 2019-08-09
 */
class RequirementEditActivity : BaseViewModelActivity<RequirementEditViewModel>() {
    override val viewModelClass: Class<RequirementEditViewModel>
        get() = RequirementEditViewModel::class.java
    override val isFragmentActivity: Boolean
        get() = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.freshman_activity_requirement_edit)
        val viewModelFactory = getViewModelFactory()
//        initView()
        freshman_top_toolbar.init("",listener = null)
//        setSupportActionBar(freshman_top_toolbar)
        viewModel = if (viewModelFactory != null) {
            ViewModelProviders.of(this, viewModelFactory).get(viewModelClass)
        } else {
            ViewModelProviders.of(this).get(viewModelClass)
        }
        initView(viewModel)
    }

    private fun initView(viewModel: RequirementEditViewModel) {
        tv_center.text="备忘录"
        tv_left.text = "取消"
        tv_right.text = "保存"
        tv_left.setOnClickListener {
            finish()
        }
        tv_right.setOnClickListener {
            viewModel.getEditText(et_requirement_edit.text.toString())
            finish()
        }
    }
}