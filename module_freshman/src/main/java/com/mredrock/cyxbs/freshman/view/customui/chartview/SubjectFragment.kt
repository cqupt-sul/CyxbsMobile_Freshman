package com.mredrock.cyxbs.freshman.view.customui.chartview

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mredrock.cyxbs.common.ui.BaseViewModelFragment
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.viewmodel.SubjectViewModel
import kotlinx.android.synthetic.main.freshman_fragment_subject.*


class SubjectFragment : BaseViewModelFragment<SubjectViewModel>() {
    override val viewModelClass: Class<SubjectViewModel>
        get() = SubjectViewModel::class.java


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.freshman_fragment_subject, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val subjectViewModel = ViewModelProviders.of(this).get(SubjectViewModel::class.java)
        subjectViewModel.initData().observe(this,
                Observer {
                    for (i in 0 until it.size) {
                        when (i) {
                            0 -> {
                                cv_subject.subjectName1 = it[i].subjectName
                                cv_subject.subjectRatio1 = it[i].subjectRatio
                            }
                            1 -> {
                                cv_subject.subjectName2 = it[i].subjectName
                                cv_subject.subjectRatio2 = it[i].subjectRatio
                            }
                            2 -> {
                                cv_subject.subjectName3 = it[i].subjectName
                                cv_subject.subjectRatio3 = it[i].subjectRatio
                            }
                        }
                    }
                    val animator: ValueAnimator = ValueAnimator.ofFloat(0f, 1f)
                    animator.duration = 4000
                    cv_subject.doAnim(animator)
                    animator.start()

                })
    }
}