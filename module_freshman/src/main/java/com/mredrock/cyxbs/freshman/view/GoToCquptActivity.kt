package com.mredrock.cyxbs.freshman.view

import android.os.Bundle
import android.widget.ViewSwitcher
import com.mredrock.cyxbs.common.ui.BaseViewModelActivity
import com.mredrock.cyxbs.freshman.viewmodel.GoToCquptViewModel

/**
 * @date 2019-08-04
 * @author Override0330
 * @description
 */

class GoToCquptActivity : BaseViewModelActivity<GoToCquptViewModel>() {
    override val viewModelClass: Class<GoToCquptViewModel>
        get() = GoToCquptViewModel::class.java
    override val isFragmentActivity: Boolean
        get() = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}