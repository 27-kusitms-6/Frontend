package com.kustims.a6six.app.ui.activity

import android.content.Intent
import android.os.Bundle
import com.kustims.a6six.app.Base.BaseActivity
import com.kustims.a6six.app.ui.viewmodel.MainViewModel
import com.kustims.a6six.databinding.ActivityMainBinding

class MainActivity: BaseActivity<MainViewModel, ActivityMainBinding>() {
    override val viewModel: MainViewModel
        get() = MainViewModel()

    override fun getViewBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.icSearchMain.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
    }


}