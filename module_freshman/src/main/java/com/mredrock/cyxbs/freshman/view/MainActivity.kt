package com.mredrock.cyxbs.freshman.view

import android.content.ClipData
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import com.mredrock.cyxbs.common.ui.BaseActivity
import com.mredrock.cyxbs.common.utils.LogUtils
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.model.CopyStringToClipboard
import com.mredrock.cyxbs.freshman.model.InBackgroundEvent
import com.mredrock.cyxbs.freshman.model.SetToolsBarTitle
import com.mredrock.cyxbs.freshman.model.ShowOrHindToolsBarEvent
import com.mredrock.cyxbs.freshman.view.goto.CopySuccessDialogFragment
import com.umeng.commonsdk.UMConfigure
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.clipboardManager

/**
* @date 2019-08-02
* @author Override0330
* @description
*/

class MainActivity : BaseActivity(){
    override val isFragmentActivity: Boolean
        get() = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UMConfigure.setLogEnabled(true)
        setContentView(R.layout.freshman_activity_main)
        common_toolbar.init(title = "",listener = View.OnClickListener { findNavController(R.id.fragment_main).popBackStack() })
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    fun changeToolsBarTitle(setToolsBarTitle: SetToolsBarTitle){
        common_toolbar.title = setToolsBarTitle.title
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun copyToClipboard(clipboard: CopyStringToClipboard){
        val clipData = ClipData.newPlainText("text",clipboard.message)
        clipboardManager.primaryClip = clipData
        val copySuccessDialogFragment = CopySuccessDialogFragment()
        copySuccessDialogFragment.show(supportFragmentManager,"copy success")
    }

}