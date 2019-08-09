package com.mredrock.cyxbs.freshman.view.talk

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.DialogFragment
import com.mredrock.cyxbs.freshman.R
import kotlinx.android.synthetic.main.freshman_dialog_talk.*

/**
 * @date 2019-08-09
 * @author Override0330
 * @description
 */
@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class TalkDialogFragment : DialogFragment(){
    var scale = 0F
    var letterAlpha = 0F
    var buttonAlpha = 0F
    private val displayMetrics = DisplayMetrics()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog.window?.setDimAmount(0.3F)
        return inflater.inflate(R.layout.freshman_dialog_talk,container)
    }


    override fun onStart() {
        super.onStart()
        if (activity!=null){
            activity!!.windowManager!!.defaultDisplay!!.getMetrics(displayMetrics)
            dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
            dialog.window?.setBackgroundDrawableResource(R.color.mtrl_btn_transparent_bg_color)
            sv_letter.alpha = 0F
            iv_i_know.alpha = 0F
            tv_i_know.alpha = 0F
        }
    }
    fun unFold(){
        if (cl_content!=null){
            val constraintSet = ConstraintSet()
            constraintSet.constrainWidth(R.id.cl_content,(displayMetrics.widthPixels*0.9*scale).toInt())
            constraintSet.constrainHeight(R.id.cl_content,(displayMetrics.heightPixels*0.9*scale).toInt())
            constraintSet.applyTo(cl_talk_main)
        }
    }
    fun showLetter(){
        sv_letter.alpha = letterAlpha
    }
    fun showButton(){
        iv_i_know.alpha = buttonAlpha
        tv_i_know.alpha = buttonAlpha
    }
}