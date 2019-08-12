package com.mredrock.cyxbs.freshman.view.guide.disclosure

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mredrock.cyxbs.common.ui.BaseViewModelFragment
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.viewmodel.disclosure.SubjectViewModel

import kotlinx.android.synthetic.main.freshman_fragment_subject.*


class SubjectFragment : BaseViewModelFragment<SubjectViewModel>() {
    override val viewModelClass: Class<SubjectViewModel>
        get() = SubjectViewModel::class.java

    val animator: ValueAnimator = ValueAnimator.ofFloat(0f, 1f)

    companion object{
        fun getSubjectFragment(school:String):Fragment{
            val args = Bundle()
            args.putString("school", school)
            val fragment = SubjectFragment()
            fragment.arguments = args
            return fragment
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.freshman_fragment_subject, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val school = arguments?.getString("school")
        if (school!=null){
            viewModel.getSubject(viewLifecycleOwner,school).observe{
                if (it!= null){
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
                    animator.duration = 3000
                    cv_subject.doAnim(animator)
                    animator.start()
                }
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }
}