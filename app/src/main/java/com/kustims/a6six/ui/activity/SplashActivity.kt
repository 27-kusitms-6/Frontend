package com.kustims.a6six.ui.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import com.kustims.a6six.R
import com.kustims.a6six.base.BaseActivity
import com.kustims.a6six.databinding.ActivitySplashBinding
import com.kustims.a6six.ui.viewmodel.LoginViewModel

class SplashActivity : BaseActivity<LoginViewModel, ActivitySplashBinding>() {

    private lateinit var _window : Window

    override fun getViewBinding(): ActivitySplashBinding =
        ActivitySplashBinding.inflate(layoutInflater)

    override val viewModel: LoginViewModel
        get() = LoginViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        statusBar()
        Handler().postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        },DURATION)
    }

    companion object {
        private const val DURATION : Long = 3000
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }


    private fun statusBar() {
        _window = getWindow();
        _window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        _window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        _window.setStatusBarColor(Color.parseColor("#5B26DD"));

    }

}
