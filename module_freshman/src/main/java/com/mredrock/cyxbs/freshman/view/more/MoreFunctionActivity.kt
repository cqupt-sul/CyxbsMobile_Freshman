package com.mredrock.cyxbs.freshman.view.more

import android.os.Bundle
import android.widget.Toast
import com.mredrock.cyxbs.common.ui.BaseActivity
import com.mredrock.cyxbs.freshman.R
import kotlinx.android.synthetic.main.freshman_activity_more_function.*

/**
 * Created by yyfbe on 2019-08-09
 */
class MoreFunctionActivity : BaseActivity(){
    override val isFragmentActivity: Boolean
        get() = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.freshman_activity_more_function)
        common_toolbar.init("更多功能")
        ll_third.setOnClickListener{
            Toast.makeText(this,"重邮小帮手",Toast.LENGTH_SHORT).show()
        }
    }
}