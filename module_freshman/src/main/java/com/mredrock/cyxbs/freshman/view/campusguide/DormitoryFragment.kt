package com.mredrock.cyxbs.freshman.view.campusguide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mredrock.cyxbs.freshman.R
import java.util.zip.Inflater


//example
class DormitoryFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.freshman_fragment_dormitory, container, false)
    }
}