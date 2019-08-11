package com.mredrock.cyxbs.freshman.view

import android.os.Bundle
import android.view.View
import com.mredrock.cyxbs.common.ui.BaseActivity
import com.mredrock.cyxbs.common.utils.LogUtils
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.model.InBackgroundEvent
import com.mredrock.cyxbs.freshman.model.ShowOrHindToolsBarEvent
import com.mredrock.cyxbs.freshman.model.db.FreshmanDataBase
import com.umeng.commonsdk.UMConfigure
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
* @date 2019-08-02
* @author Override0330
* @description
*/

class MainActivity : BaseActivity(){
    override val isFragmentActivity: Boolean
        get() = true

    override fun onStart() {
        super.onStart()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UMConfigure.setLogEnabled(true)
        setContentView(R.layout.freshman_activity_main)
    }


    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    fun changeToolsBarTitle(title:String){
        common_toolbar.title = title
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    fun hindToolsBar(toolsBarEvent: ShowOrHindToolsBarEvent){
        if (!toolsBarEvent.isShow){
            common_toolbar.visibility = View.GONE
        }else {
            common_toolbar.visibility = View.VISIBLE
        }
    }

    @Subscribe(threadMode = ThreadMode.ASYNC,sticky = true)
    fun runInBackground(inBackgroundEvent: InBackgroundEvent){
        run(inBackgroundEvent.event)
        LogUtils.d("debug","${inBackgroundEvent.event} EventBus IO线程结束")
    }
}