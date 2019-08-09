package com.mredrock.cyxbs.freshman.view.gotocqupt

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.mredrock.cyxbs.freshman.R
import kotlinx.android.synthetic.main.freshman_activity_main.*
import java.util.*

/**
 * @date 2019-08-07
 * @author Override0330
 * @description
 */
class CopySuccessDialogFragment :DialogFragment(){
    val timer = Timer()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog.window?.setDimAmount(0.1F)
        return View.inflate(this.context, R.layout.freshman_dialog_copy,container)
    }

    override fun onStart() {
        super.onStart()
        val displayMetrics = DisplayMetrics()
        if (activity!=null){
            activity!!.windowManager!!.defaultDisplay!!.getMetrics(displayMetrics)
            dialog.window?.setLayout((displayMetrics.widthPixels*0.85).toInt(),(displayMetrics.heightPixels*0.3).toInt())
        }
        timer.schedule(object :TimerTask(){
            override fun run() {
                dialog.dismiss()
            }
        },1500)
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }
}