package com.mredrock.cyxbs.freshman.view

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.mredrock.cyxbs.common.ui.BaseViewModelActivity
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.view.adapter.HomeRecyclerViewAdapter
import com.mredrock.cyxbs.freshman.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.freshman_acitvity_home.*

class HomeActivity : BaseViewModelActivity<HomeViewModel>() {
    override val isFragmentActivity: Boolean
        get() = false
    override val viewModelClass: Class<HomeViewModel>
        get() = HomeViewModel::class.java
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.freshman_acitvity_home)
        viewModel.showList.observe{
            val homeRecyclerViewAdapter = HomeRecyclerViewAdapter(it)
            rv_home.layoutManager = LinearLayoutManager(this)
            rv_home.adapter = homeRecyclerViewAdapter
        }
        viewModel.init()
    }

}
