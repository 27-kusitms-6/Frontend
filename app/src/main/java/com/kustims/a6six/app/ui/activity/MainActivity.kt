package com.kustims.a6six.app.ui.activity

import android.os.Bundle
import com.kustims.a6six.app.viewmodel.MainViewModel
import com.kustims.a6six.databinding.ActivityMainBinding

class MainActivity: BaseActivity<MainViewModel, ActivityMainBinding>() {
    override val viewModel: MainViewModel
        get() = MainViewModel()

    override fun getViewBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }


}