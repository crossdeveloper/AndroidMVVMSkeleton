package com.crossdeveloper.mvvmskeleton

import android.os.Bundle
import com.crossdeveloper.mvvmskeleton.data.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}