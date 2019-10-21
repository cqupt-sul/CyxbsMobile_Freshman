package com.mredrock.cyxbs.freshman.view.online

import android.app.Dialog
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.mredrock.cyxbs.common.utils.LogUtils
import com.mredrock.cyxbs.common.utils.extensions.setImageFromUrl
import com.mredrock.cyxbs.freshman.R
import kotlinx.android.synthetic.main.freshman_dialog_join.*
import org.jetbrains.anko.imageURI

/**
 * @date 2019-08-09
 * @author Override0330
 * @description
 */
class JoinActivityDialogFragment :DialogFragment(){

    lateinit var qrUrl: String
    lateinit var message: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog.window?.setDimAmount(0.1F)
        return inflater.inflate(R.layout.freshman_dialog_join,container,false)
    }

    override fun onStart() {
        super.onStart()
        val displayMetrics = DisplayMetrics()
        if (activity!=null){
            activity!!.windowManager!!.defaultDisplay!!.getMetrics(displayMetrics)
            dialog.window?.setLayout((displayMetrics.widthPixels*0.85).toInt(),ViewGroup.LayoutParams.WRAP_CONTENT)
            iv_qr_code.setImageFromUrl(qrUrl)
            tv_join_message.text = message
        }
    }

    fun init(qrUrl:String,message:String){
        this.qrUrl = qrUrl
        this.message = message
    }

}