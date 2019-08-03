package com.mredrock.cyxbs.freshman.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.view.nannvbili.NannvbiliFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.freshman_activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.fragment, NannvbiliFragment()).commit()
    }
}
