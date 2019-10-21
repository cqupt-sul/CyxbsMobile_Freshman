package com.mredrock.cyxbs.freshman.view.more

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.mredrock.cyxbs.common.ui.BaseActivity
import com.mredrock.cyxbs.common.ui.BaseFragment
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.model.SetToolsBarTitle
import kotlinx.android.synthetic.main.freshman_activity_more_function.*
import org.greenrobot.eventbus.EventBus

/**
 * Created by yyfbe on 2019-08-09
 */
class MoreFunctionFragment : BaseFragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.freshman_activity_more_function,container,false)
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().post(SetToolsBarTitle("更多功能"))
        ll_third.setOnClickListener {
            Toast.makeText(this.context, "重邮小帮手", Toast.LENGTH_SHORT).show()
        }
    }
}